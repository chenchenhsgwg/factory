package com.bosssoft.trainee.factory2.config;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

@Data
public class JWTToken implements AuthenticationToken {

    private final String token;

    private String exipreAt;

    public JWTToken(String token) {
        this.token = token;
    }

    public JWTToken(String token, String exipreAt) {
        this.token = token;
        this.exipreAt = exipreAt;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
