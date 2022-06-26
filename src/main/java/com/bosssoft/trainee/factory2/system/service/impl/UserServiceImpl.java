package com.bosssoft.trainee.factory2.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.trainee.factory2.system.mapper.UserMapper;
import com.bosssoft.trainee.factory2.system.service.IFactoryService;
import com.bosssoft.trainee.factory2.system.service.IUserRoleService;
import com.bosssoft.trainee.factory2.system.service.IUserService;
import com.bosssoft.trainee.factory2.common.Constant;
import com.bosssoft.trainee.factory2.common.PagedRequest;
import com.bosssoft.trainee.factory2.system.entity.Factory;
import com.bosssoft.trainee.factory2.system.entity.User;
import com.bosssoft.trainee.factory2.system.entity.UserRole;
import com.bosssoft.trainee.factory2.utils.SortUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final UserMapper userMapper;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IFactoryService factoryService;


    @Override
    public IPage<User> findUsers(PagedRequest request, User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(user.getRealname())) {
            queryWrapper.like(User::getRealname, user.getRealname());
        }
        if (StringUtils.isNotBlank(user.getUsername())) {
            queryWrapper.like(User::getUsername, user.getUsername());
        }
        Page<User> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<User> findUsers(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(user.getRealname())) {
            queryWrapper.eq(User::getRealname, user.getRealname());
        }
        if (StringUtils.isNotBlank(user.getUsername())) {
            queryWrapper.eq(User::getUsername, user.getUsername());
        }
        if (StringUtils.isNotBlank(user.getTelephone())) {
            queryWrapper.eq(User::getTelephone, user.getTelephone());
        }
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<User> findUserDetails(User user, PagedRequest request) {
        try {
            Page<User> page = new Page<>();
            SortUtil.handlePageSort(request, page, "id", Constant.ORDER_ASC, false);
            return this.baseMapper.getUserDetails(page, user);
        } catch (Exception e) {
            log.error("查询用户异常", e);
            return null;
        }
    }

    @Override
    public User findByName(String username) {
        System.out.println(username);
        User user = this.userMapper.getUserDetail(username);
        System.out.println(user);
        return user;
    }

    @Override
    public User findById(int userId) {
        System.out.println(userId);
        User user = this.userMapper.getUserDetailById(userId);
        System.out.println(user);
        return user;
    }

    @Override
    public User getUserById(int id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return this.baseMapper.getUserDetail(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createUser(User user) {
        this.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(User user) {
        this.saveOrUpdate(user);
    }

    @Override
    public void deleteUsers(String[] userIds) {
        List<String> list = Arrays.asList(userIds);
        list.remove("7");
        this.baseMapper.deleteBatchIds(list);
    }

    @Override
    public void regist(User user) {
        if (StringUtils.isNotBlank(user.getUsername()) && StringUtils.isNotBlank(user.getPassword()) && StringUtils.isNotBlank(user.getRealname())) {
            createUser(user);
            user = getUserByUsername(user.getUsername());
            UserRole userRole = new UserRole();
            userRole.setUserid(user.getId());
            userRole.setRoleid(user.getRoleId());
            this.userRoleService.createUserRole(userRole);
            if (StringUtils.isNotBlank(user.getFactoryName())) {
                Factory factory = new Factory();
                factory.setUserId(user.getId());
                factory.setName(user.getFactoryName());
                factory.setDescription(user.getFactoryDescription());
                factory.setEnabled(true);
                this.factoryService.createFactory(factory);
            }
        }
    }

    @Override
    public IPage<User> findFriendDetails(User user, PagedRequest request) {
        try {
            Page<User> page = new Page<>();
            SortUtil.handlePageSort(request, page, "id", Constant.ORDER_ASC, false);
            return this.baseMapper.getFriendDetails(page, user);
        } catch (Exception e) {
            log.error("查询用户异常", e);
            return null;
        }
    }

    @Override
    public IPage<User> findNotFriendDetails(User user, PagedRequest request) {
        try {
            Page<User> page = new Page<>();
            SortUtil.handlePageSort(request, page, "id", Constant.ORDER_ASC, false);
            return this.baseMapper.getNotFriendDetails(page, user);
        } catch (Exception e) {
            log.error("查询用户异常", e);
            return null;
        }
    }

    @Override
    public IPage<User> getNotFriendByIdAndRole(User user, String roleName, PagedRequest request) {
        try {
            Page<User> page = new Page<>();
            SortUtil.handlePageSort(request, page, "id", Constant.ORDER_ASC, false);
            return this.baseMapper.getNotFriendByRoleName(page, user,roleName);
        } catch (Exception e) {
            log.error("查询用户异常", e);
            return null;
        }
    }

    @Override
    public IPage<User> getFriendByIdAndRole(User user, String roleName, PagedRequest request) {
        try {
            Page<User> page = new Page<>();
            SortUtil.handlePageSort(request, page, "id", Constant.ORDER_ASC, false);
            return this.baseMapper.getFriendByRoleName(page, user,roleName);
        } catch (Exception e) {
            log.error("查询用户异常", e);
            return null;
        }
    }
}
