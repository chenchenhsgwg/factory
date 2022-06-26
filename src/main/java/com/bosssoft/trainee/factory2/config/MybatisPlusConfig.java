package com.bosssoft.trainee.factory2.config;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.bosssoft.trainee.factory2.config.interceptor.PaginationInterceptorImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
@MapperScan(value = {"com.neu.factory2.system.mapper"})
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptorImpl();
    }

//    @Bean
//    public SqlSessionFactory newSqlSessionFactoryBean(DataSource dataSource) throws Exception {
//        //需要使用MybatisSqlSessionFactoryBean 而不是 SqlSessionFactoryBean
//        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//
//        //配置完下面这两个在 application.properties 就不需要配置了
////        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/*Mapper.xml"));
////        bean.setTypeAliasesPackage("com.gc.modular.entity.model");
//
//        //configuration 需要使用 MybatisConfiguration 如果使用 org.apache.ibatis.session.Configuration 也有可能会报错
//        MybatisConfiguration configuration = new MybatisConfiguration();
//
//        // 返回类型Map时字段为空也要返回字段名称
//        configuration.setCallSettersOnNulls(true);
//        bean.setConfiguration(configuration);
//        return bean.getObject();
//    }

}
