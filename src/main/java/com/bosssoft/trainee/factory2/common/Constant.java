package com.bosssoft.trainee.factory2.common;

/**
 * 常量
 */
public interface Constant {

    String UNDER_LINE = "_";
    // user缓存前缀
    String USER_CACHE_PREFIX = "cloud.cache.user.";
    // user角色缓存前缀
    String USER_ROLE_CACHE_PREFIX = "cloud.cache.user.role.";
    // user权限缓存前缀
    String USER_PERMISSION_CACHE_PREFIX = "cloud.cache.user.authority.";
    // token缓存前缀
    String TOKEN_CACHE_PREFIX = "cloud.cache.token.";

    // 存储在线用户的 zset前缀
    String ACTIVE_USERS_ZSET_PREFIX = "cloud.user.active";
    /**
     * 排序规则：降序
     */
    String ORDER_DESC = "descend";

    /**
     * 排序规则：升序
     */
    String ORDER_ASC = "ascend";

    /**
     * 前端页面路径前缀
     */
    String VIEW_PREFIX = "cloud/views/";

    /**
     * cloud-shiro线程池名称
     */
    String FEBS_SHIRO_THREAD_POOL = "cloudShiroThreadPool";

    /**
     * cloud-shiro线程名称前缀
     */
    String FEBS_SHIRO_THREAD_NAME_PREFIX = "cloud-shiro-thread-";

    /**
     * 开发环境
     */
    String DEVELOP = "dev";

    /**
     * Windows 操作系统
     */
    String SYSTEM_WINDOWS = "windows";

    String REQUEST_ALL = "/**";

    String DAY_START_PATTERN_SUFFIX = " 00:00:00";
    String DAY_END_PATTERN_SUFFIX = " 23:59:59";

    /**
     * 验证码ey前缀
     */
    String VALIDATE_CODE_PREFIX = "cloud_captcha_";

    /**
     * 验证码有效期Key前缀
     */
    String VALIDATE_CODE_TIME_PREFIX = "cloud_captcha_time_";

    //数据范围权限
    int DATA_FILTER_BUSY = 0;
    int DATA_FILTER_MANAGER = 1;
    int DATA_FILTER_ADMIN = 2;
}
