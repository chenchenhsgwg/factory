package com.bosssoft.trainee.factory2.config.interceptor;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisDefaultParameterHandler;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.SqlInfo;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.DialectFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.DialectModel;
import com.baomidou.mybatisplus.extension.toolkit.JdbcUtils;
import com.baomidou.mybatisplus.extension.toolkit.SqlParserUtils;
import com.bosssoft.trainee.factory2.common.Constant;
import com.bosssoft.trainee.factory2.common.DataFilter;
import com.bosssoft.trainee.factory2.common.FilterType;
import com.bosssoft.trainee.factory2.system.entity.User;
import com.bosssoft.trainee.factory2.utils.WebUtil;
import com.bosssoft.trainee.factory2.system.entity.Role;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.shiro.util.Assert;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;


/**
 * 分页拦截器
 */
@Setter
@Accessors(chain = true)
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PaginationInterceptorImpl extends PaginationInterceptor {
    /**
     * COUNT SQL 解析
     */
    private ISqlParser countSqlParser;
    /**
     * 溢出总页数，设置第一页
     */
    private boolean overflow = false;
    /**
     * 单页限制 500 条，小于 0 如 -1 不受限制
     */
    private long limit = 500L;
    /**
     * 方言类型
     */
    private String dialectType;
    /**
     * 方言实现类<br>
     * 注意！实现 com.baomidou.mybatisplus.extension.plugins.pagination.dialects.IDialect 接口的子类
     */
    private String dialectClazz;

    /**
     * Physical Page Interceptor for all the queries with parameter {@link RowBounds}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        boolean dataFilter = false;
        // SQL 解析
        this.sqlParser(metaObject);

        // 先判断是不是SELECT操作
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        if (SqlCommandType.SELECT != mappedStatement.getSqlCommandType()
                || StatementType.CALLABLE == mappedStatement.getStatementType()) {
            return invocation.proceed();
        }
        //数据权限过滤
        DataFilter anno = null;
        if (SqlCommandType.SELECT == mappedStatement.getSqlCommandType()) {
            String id = mappedStatement.getId();
            String className = id.substring(0, id.lastIndexOf("."));
            String methodName = id.replace(className + ".", "");
            System.out.println(methodName);
            Class clazz = Class.forName(className);
            if (clazz.isAnnotationPresent(DataFilter.class)) {
                anno = (DataFilter) clazz.getAnnotation(DataFilter.class);
                String[] methods = anno.filterMethods();
                String[] ruledOutMethods = anno.ruledOutMethods();
                if (methods.length == 0) {
                    if (ruledOutMethods.length == 0) {
                        dataFilter = true;
                    } else {
                        String str = "," + StringUtils.join(ruledOutMethods, ",") + ",";
                        if (str.indexOf(methodName) == -1) {
                            dataFilter = true;
                        }
                    }
                } else {
                    Optional<String> optional = Arrays.stream(ruledOutMethods).parallel().filter(me -> me.equals(methodName)).findAny();
                    if (!optional.isPresent()) {
                        optional = Arrays.stream(methods).parallel().filter(me -> me.equals(methodName)).findAny();
                        if (optional.isPresent()) {
                            dataFilter = true;
                        }
                    }
                }
            } else {
                try {
                    Method method = clazz.getDeclaredMethod(methodName);
                    if (method != null && method.isAnnotationPresent(DataFilter.class)) {
                        anno = method.getAnnotation(DataFilter.class);
                        dataFilter = true;
                    }
                } catch (Exception ex) {
                }
            }
        }
        // 针对定义了rowBounds，做为mapper接口方法的参数
        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        Object paramObj = boundSql.getParameterObject();

        // 判断参数里是否有page对象
        IPage<?> page = null;
        if (paramObj instanceof IPage) {
            page = (IPage<?>) paramObj;
        } else if (paramObj instanceof Map) {
            for (Object arg : ((Map<?, ?>) paramObj).values()) {
                if (arg instanceof IPage) {
                    page = (IPage<?>) arg;
                    break;
                }
            }
        }

        /*
         * 不需要分页的场合，如果 size 小于 0 返回结果集
         */
        if (null == page || page.getSize() < 0) {
            return invocation.proceed();
        }

        /*
         * 处理单页条数限制
         */
        if (limit > 0 && limit <= page.getSize()) {
            page.setSize(limit);
        }

        String originalSql = boundSql.getSql();
        //数据范围过滤
        if (dataFilter) {
            User me = WebUtil.getCurrentUser();
            String fieldId = anno.filterFieldId(), filterType = anno.filterType().getType(), joinSql = anno.joinSql();
            if (filterType.equals(FilterType.FIELD.getType())) {
                List<Role> roles = WebUtil.getUserRoles();
                boolean busyData = false, managerData = false, adminData = false;
                for (Role role : roles) {
                    if (Constant.DATA_FILTER_BUSY == role.getId()) busyData = true;
                    if (Constant.DATA_FILTER_MANAGER == role.getId()) managerData = true;
                    if (Constant.DATA_FILTER_ADMIN == role.getId()) adminData = true;
                }
//                String subordinates=FebsUtil.getUserSubordinates(me.getDeptId());
                String subordinates = null;
                if (managerData) {
                    if (StringUtils.isNotEmpty(subordinates)) {
                        if (!StringUtils.containsIgnoreCase(originalSql, "where")) {
                            originalSql = originalSql + " where " + fieldId + " in (" + subordinates + ")";
                        } else if (originalSql.contains("where")) {
                            String[] sqlParts = originalSql.split("where");
                            Assert.isTrue(sqlParts.length == 2);
                            originalSql = sqlParts[0] + "where " + fieldId + " in (" + subordinates + ") and " + sqlParts[1];
                        } else if (originalSql.contains("WHERE")) {
                            String[] sqlParts = originalSql.split("WHERE");
                            Assert.isTrue(sqlParts.length == 2);
                            originalSql = sqlParts[0] + "WHERE " + fieldId + " in (" + subordinates + ") and " + sqlParts[1];
                        }
                    }
                } else if (adminData) {
                    if (!StringUtils.containsIgnoreCase(originalSql, "where")) {
                        originalSql = originalSql + " where " + fieldId + " in (" + me.getId() + ")";
                    } else if (originalSql.contains("where")) {
                        String[] sqlParts = originalSql.split("where");
                        Assert.isTrue(sqlParts.length == 2);
                        originalSql = sqlParts[0] + "where " + fieldId + " in (" + me.getId() + ") and " + sqlParts[1];
                    } else if (originalSql.contains("WHERE")) {
                        String[] sqlParts = originalSql.split("WHERE");
                        Assert.isTrue(sqlParts.length == 2);
                        originalSql = sqlParts[0] + "WHERE " + fieldId + " in (" + me.getId() + ") and " + sqlParts[1];
                    }
                } else if (busyData) {
                }
            } else if (filterType.equals(FilterType.JOIN.getType())) {
                if (StringUtils.isNotEmpty(joinSql)) {
                    if (!StringUtils.containsIgnoreCase(originalSql, "where")) {
                        originalSql = originalSql + " " + joinSql + " where " + fieldId + "=" + me.getId();
                    } else if (originalSql.contains("where")) {
                        String[] sqlParts = originalSql.split("WHERE");
                        Assert.isTrue(sqlParts.length == 2);
                        originalSql = sqlParts[0] + joinSql + " where " + fieldId + "=" + me.getId() + " and " + sqlParts[1];
                    } else if (originalSql.contains("WHERE")) {
                        String[] sqlParts = originalSql.split("WHERE");
                        Assert.isTrue(sqlParts.length == 2);
                        originalSql = sqlParts[0] + joinSql + " WHERE " + fieldId + "=" + me.getId() + " and " + sqlParts[1];
                    }
                }
            }
        }
        Connection connection = (Connection) invocation.getArgs()[0];
        DbType dbType = StringUtils.isNotEmpty(dialectType) ? DbType.getDbType(dialectType)
                : JdbcUtils.getDbType(connection.getMetaData().getURL());

        if (page.isSearchCount()) {
            SqlInfo sqlInfo = SqlParserUtils.getOptimizeCountSql(page.optimizeCountSql(), countSqlParser, originalSql);
            this.queryTotal(overflow, sqlInfo.getSql(), mappedStatement, boundSql, page, connection);
            if (page.getTotal() <= 0) {
                return null;
            }
        }

        String buildSql = concatOrderBy(originalSql, page);
        DialectModel model = DialectFactory.buildPaginationSql(page, buildSql, dbType, dialectClazz);
        Configuration configuration = mappedStatement.getConfiguration();
        List<ParameterMapping> mappings = new ArrayList<>(boundSql.getParameterMappings());
        Map<String, Object> additionalParameters = (Map<String, Object>) metaObject.getValue("delegate.boundSql.additionalParameters");
        model.consumers(mappings, configuration, additionalParameters);
        metaObject.setValue("delegate.boundSql.sql", model.getDialectSql());
        metaObject.setValue("delegate.boundSql.parameterMappings", mappings);
        return invocation.proceed();
    }

    /**
     * 查询总记录条数
     *
     * @param sql             count sql
     * @param mappedStatement MappedStatement
     * @param boundSql        BoundSql
     * @param page            IPage
     * @param connection      Connection
     */
    @Override
    protected void queryTotal(boolean overflowCurrent, String sql, MappedStatement mappedStatement, BoundSql boundSql, IPage<?> page, Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            DefaultParameterHandler parameterHandler = new MybatisDefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), boundSql);
            parameterHandler.setParameters(statement);
            long total = 0;
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    total = resultSet.getLong(1);
                }
            }
            page.setTotal(total);
            /*
             * 溢出总页数，设置第一页
             */
            long pages = page.getPages();
            if (overflowCurrent && page.getCurrent() > pages) {
                // 设置为第一条
                page.setCurrent(1);
            }
        } catch (Exception e) {
            throw ExceptionUtils.mpe("Error: Method queryTotal execution error of sql : \n %s \n", e, sql);
        }
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties prop) {
        String dialectType = prop.getProperty("dialectType");
        String dialectClazz = prop.getProperty("dialectClazz");
        if (StringUtils.isNotEmpty(dialectType)) {
            this.dialectType = dialectType;
        }
        if (StringUtils.isNotEmpty(dialectClazz)) {
            this.dialectClazz = dialectClazz;
        }
    }
}
