package com.bosssoft.trainee.factory2.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.trainee.factory2.system.mapper.RoleMapper;
import com.bosssoft.trainee.factory2.system.service.IRoleAuthService;
import com.bosssoft.trainee.factory2.system.service.IRoleService;
import com.bosssoft.trainee.factory2.system.service.IUserRoleService;
import com.bosssoft.trainee.factory2.common.PagedRequest;
import com.bosssoft.trainee.factory2.system.entity.Role;
import lombok.RequiredArgsConstructor;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

//    @Autowired(required = false)
    private final RoleMapper roleMapper;

    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IRoleAuthService roleAuthService;

    @Override
    public IPage<Role> findRoles(PagedRequest request, Role role) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        Page<Role> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Role> findRoles(Role role) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Role> findUserRole(String userName) {
        return baseMapper.findUserRole(userName);
    }

    @Override
    public Role findByName(String roleName) {
        return baseMapper.selectOne(new LambdaQueryWrapper<Role>().eq(Role::getName, roleName));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createRole(Role role) {
        this.save(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(Role role) {
        this.saveOrUpdate(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(Role role) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        this.remove(wrapper);
    }

    @Override
    public void deleteRoles(String[] roleIds) throws Exception {

        List<String> list = Arrays.asList(roleIds);

        baseMapper.deleteBatchIds(list);

        this.roleAuthService.deleteRoleAuthsByRoleId(roleIds);
        this.userRoleService.deleteUserRolesByRoleId(roleIds);

    }
}
