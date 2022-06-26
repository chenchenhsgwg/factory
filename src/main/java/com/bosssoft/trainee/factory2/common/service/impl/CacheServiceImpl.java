package com.bosssoft.trainee.factory2.common.service.impl;


import com.bosssoft.trainee.factory2.common.Constant;
import com.bosssoft.trainee.factory2.common.service.CacheService;
import com.bosssoft.trainee.factory2.common.service.RedisService;
import com.bosssoft.trainee.factory2.system.entity.User;
import com.bosssoft.trainee.factory2.system.service.IAuthorityService;
import com.bosssoft.trainee.factory2.system.service.IRoleService;
import com.bosssoft.trainee.factory2.system.service.IUserService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bosssoft.trainee.factory2.system.entity.Authority;
import com.bosssoft.trainee.factory2.system.entity.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cacheService")
public class CacheServiceImpl implements CacheService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IAuthorityService authorityService;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void testConnect() throws Exception {
        this.redisService.exists("test");
    }

    @Override
    public User getUser(String username) throws Exception {
        String userString = this.redisService.get(Constant.USER_CACHE_PREFIX + username);
        if (StringUtils.isBlank(userString))
            throw new Exception();
        else
            return this.mapper.readValue(userString, User.class);
    }

    @Override
    public List<Role> getRoles(String username) throws Exception {
        String roleListString = this.redisService.get(Constant.USER_ROLE_CACHE_PREFIX + username);
        if (StringUtils.isBlank(roleListString)) {
            throw new Exception();
        } else {
            JavaType type = mapper.getTypeFactory().constructParametricType(List.class, Role.class);
            return this.mapper.readValue(roleListString, type);
        }
    }

    @Override
    public List<Authority> getAuthorities(String username) throws Exception {
        String permissionListString = this.redisService.get(Constant.USER_PERMISSION_CACHE_PREFIX + username);
        if (StringUtils.isBlank(permissionListString)) {
            throw new Exception();
        } else {
            JavaType type = mapper.getTypeFactory().constructParametricType(List.class, Authority.class);
            return this.mapper.readValue(permissionListString, type);
        }
    }

    @Override
    public void saveUser(User user) throws Exception {
        String username = user.getUsername();
        this.deleteUser(username);
        redisService.set(Constant.USER_CACHE_PREFIX + username, mapper.writeValueAsString(user));
    }

    @Override
    public void saveUser(String username) throws Exception {
        User user = userService.getUserByUsername(username);
        this.deleteUser(username);
        redisService.set(Constant.USER_CACHE_PREFIX + username, mapper.writeValueAsString(user));
    }

    @Override
    public void saveRoles(String username) throws Exception {
        List<Role> roleList = this.roleService.findUserRole(username);
        if (!roleList.isEmpty()) {
            this.deleteRoles(username);
            redisService.set(Constant.USER_ROLE_CACHE_PREFIX + username, mapper.writeValueAsString(roleList));
        }
    }

    @Override
    public void saveAuthorities(String username) throws Exception {
        List<Authority> permissionList = this.authorityService.findUserAuthorities(username);
        if (!permissionList.isEmpty()) {
            this.deleteAuthorities(username);
            redisService.set(Constant.USER_PERMISSION_CACHE_PREFIX + username, mapper.writeValueAsString(permissionList));
        }
    }

    @Override
    public void deleteUser(String username) throws Exception {
        username = username.toLowerCase();
        redisService.del(Constant.USER_CACHE_PREFIX + username);
    }

    @Override
    public void deleteRoles(String username) throws Exception {
        username = username.toLowerCase();
        redisService.del(Constant.USER_ROLE_CACHE_PREFIX + username);
    }

    @Override
    public void deleteAuthorities(String username) throws Exception {
        username = username.toLowerCase();
        redisService.del(Constant.USER_PERMISSION_CACHE_PREFIX + username);
    }

}
