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
}
