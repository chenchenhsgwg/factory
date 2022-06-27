package com.bosssoft.trainee.factory2.config;

import com.bosssoft.trainee.factory2.system.service.impl.LoginService;
import com.bosssoft.trainee.factory2.common.service.RedisService;
import com.bosssoft.trainee.factory2.system.entity.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private RedisService redisService;
    @Autowired
    @Lazy
    private LoginService loginService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }


    /**
     * `
     * 授权模块，获取用户角色和权限
     *
     * @param token token
     * @return AuthorizationInfo 权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
        String username = JWTUtils.getUsername(token.toString());

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 获取用户角色集
        Set<String> roleSet = loginService.getUserRoles(username);
        simpleAuthorizationInfo.setRoles(roleSet);

        // 获取用户权限集
        Set<String> permissionSet = loginService.getUserPermissions(username);
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }

    /**
     * 用户认证
     *
     * @param authenticationToken 身份认证 token
     * @return AuthenticationInfo 身份认证信息
     * @throws AuthenticationException 认证相关异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 这里的 token是从 JWTFilter 的 executeLogin 方法传递过来的，已经经过了解密
        String token = (String) authenticationToken.getCredentials();

        // 从 redis里获取这个 toke
        String username = JWTUtils.getUsername(token);

        // 通过用户名查询用户信息
        User user = loginService.getUser(username);

        if (user == null)
            throw new AuthenticationException("用户名或密码错误");
        return new SimpleAuthenticationInfo(token, token, "cloud_shiro_realm");
    }
}
