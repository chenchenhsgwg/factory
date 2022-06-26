package com.bosssoft.trainee.factory2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@EnableAsync
@MapperScan(basePackages = {"com.bosssoft.trainee.factory2.system.service", "com.bosssoft.trainee.factory2.system.mapper"})
public class Factory2Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Factory2Application.class);
//        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
//        new SpringApplicationBuilder(Factory2Application.class).run(args);
    }

}
