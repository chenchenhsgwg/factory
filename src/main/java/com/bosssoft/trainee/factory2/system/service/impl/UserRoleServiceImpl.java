package com.bosssoft.trainee.factory2.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.trainee.factory2.system.mapper.UserRoleMapper;
import com.bosssoft.trainee.factory2.system.service.IUserRoleService;
import com.bosssoft.trainee.factory2.common.PagedRequest;
import com.bosssoft.trainee.factory2.system.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Primary
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    private final UserRoleMapper userRoleMapper;

    @Override
    public IPage<UserRole> findUserRoles(PagedRequest request, UserRole userRole) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<UserRole> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<UserRole> findUserRoles(UserRole userRole) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createUserRole(UserRole userRole) {
        this.save(userRole);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserRole(UserRole userRole) {
        this.saveOrUpdate(userRole);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUserRole(UserRole userRole) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        this.remove(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUserRolesByUserId(String[] userIds) {
        Arrays.stream(userIds).forEach(id -> baseMapper.deleteByUserId(Integer.parseInt(id)));
    }

    @Override
    public void deleteUserRolesByRoleId(String[] roleIds) {
        Arrays.stream(roleIds).forEach(id -> baseMapper.deleteByRoleId(Integer.parseInt(id)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<String> getRoleIdsByUserId(String[] userIds) {
        List<UserRole> list = baseMapper.selectList(new LambdaQueryWrapper<UserRole>().in(UserRole::getUserid, String.join(",", userIds)));
        return list.stream().map(userRole -> String.valueOf(userRole.getRoleid())).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<String> getUserIdsByRoleId(String[] roleIds) {
        List<UserRole> list = baseMapper.selectList(new LambdaQueryWrapper<UserRole>().in(UserRole::getRoleid, String.join(",", roleIds)));
        return list.stream().map(userRole -> String.valueOf(userRole.getUserid())).collect(Collectors.toList());
    }
}
