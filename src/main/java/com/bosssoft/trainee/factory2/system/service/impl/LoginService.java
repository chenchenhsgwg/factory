package com.bosssoft.trainee.factory2.system.service.impl;

import com.bosssoft.trainee.factory2.system.service.IAuthorityService;
import com.bosssoft.trainee.factory2.system.service.IRoleService;
import com.bosssoft.trainee.factory2.system.service.IUserService;
import com.bosssoft.trainee.factory2.common.router.RouterMeta;
import com.bosssoft.trainee.factory2.common.router.VueRouter;
import com.bosssoft.trainee.factory2.common.service.CacheService;
import com.bosssoft.trainee.factory2.system.entity.Authority;
import com.bosssoft.trainee.factory2.system.entity.Role;
import com.bosssoft.trainee.factory2.system.entity.User;
import com.bosssoft.trainee.factory2.utils.TreeUtil;
import com.bosssoft.trainee.factory2.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LoginService {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IAuthorityService authService;
    @Autowired
    private IUserService userService;
    @Autowired
    private CacheService cacheService;


    /**
     * 通过用户名获取用户基本信息
     *
     * @param username 用户名
     * @return 用户基本信息
     */
    public User getUser(String username) {
        return WebUtil.selectCacheByTemplate(
                () -> this.cacheService.getUser(username),
                () -> this.userService.getUserByUsername(username));
    }

    /**
     * 通过用户名获取用户角色集合
     *
     * @param username 用户名
     * @return 角色集合
     */
    public Set<String> getUserRoles(String username) {
        List<Role> roleList = WebUtil.selectCacheByTemplate(
                () -> this.cacheService.getRoles(username),
                () -> this.roleService.findUserRole(username));
        return roleList.stream().map(Role::getName).collect(Collectors.toSet());
    }

    /**
     * 通过用户名获取用户权限集合
     *
     * @param username 用户名
     * @return 权限集合
     */
    public Set<String> getUserPermissions(String username) {
        List<Authority> permissionList = WebUtil.selectCacheByTemplate(
                () -> this.cacheService.getAuthorities(username),
                () -> this.authService.findUserAuthorities(username));
        return permissionList.stream().map(Authority::getResource).collect(Collectors.toSet());
    }

    /**
     * 通过用户名构建 Vue路由
     *
     * @param username 用户名
     * @return 路由集合
     */
    public List<VueRouter<Authority>> getUserRouters(String username) {
        List<VueRouter<Authority>> routes = new ArrayList<>();
        List<Authority> authorities = this.authService.findUserAuthorities(username);
        authorities.forEach(authority -> {
            VueRouter<Authority> route = new VueRouter<>();
            route.setId(authority.getId().toString());
            route.setParentId(String.valueOf(authority.getParentId()));
            route.setIcon(authority.getIcon());
            route.setPath(authority.getPath());
            route.setComponent(authority.getComponent());
            route.setName(authority.getName());
            route.setMeta(new RouterMeta(true, null));
            routes.add(route);
        });
        return TreeUtil.buildVueRouter(routes);
    }

    /**
     * 将用户相关信息添加到 Redis缓存中
     *
     * @param user user
     */
    public void loadUserRedisCache(User user) throws Exception {
        // 缓存用户
        cacheService.saveUser(user);
        // 缓存用户角色
        cacheService.saveRoles(user.getUsername());
        // 缓存用户权限
        cacheService.saveAuthorities(user.getUsername());
    }

    /**
     * 将用户角色和权限添加到 Redis缓存中
     *
     * @param userIds userIds
     */
    public void loadUserPermissionRoleRedisCache(List<String> userIds) throws Exception {
        for (String userId : userIds) {
            User user = userService.getById(userId);
            // 缓存用户角色
            cacheService.saveRoles(user.getUsername());
            // 缓存用户权限
            cacheService.saveAuthorities(user.getUsername());
        }
    }

    /**
     * 通过用户 id集合批量删除用户 Redis缓存
     *
     * @param userIds userIds
     */
    public void deleteUserRedisCache(String... userIds) throws Exception {
        for (String userId : userIds) {
            User user = userService.getById(userId);
            if (user != null) {
                cacheService.deleteUser(user.getUsername());
                cacheService.deleteRoles(user.getUsername());
                cacheService.deleteAuthorities(user.getUsername());
            }
        }
    }
}
