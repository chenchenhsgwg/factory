package com.bosssoft.trainee.factory2.common;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@Configuration
public class ShiroProperties {
    private String anonUrl = "/login,/regist,/user/check,/factory/check";

    /**
     * token默认有效时间 1天
     */
    private Long jwtTimeOut = 86400L;
}
