package com.bosssoft.trainee.factory2.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.trainee.factory2.system.mapper.RoleAuthMapper;
import com.bosssoft.trainee.factory2.system.service.IRoleAuthService;
import com.bosssoft.trainee.factory2.common.PagedRequest;
import com.bosssoft.trainee.factory2.system.entity.RoleAuth;
import lombok.RequiredArgsConstructor;
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
public class RoleAuthServiceImpl extends ServiceImpl<RoleAuthMapper, RoleAuth> implements IRoleAuthService {

    private final RoleAuthMapper roleAuthMapper;

    @Override
    public IPage<RoleAuth> findRoleAuths(PagedRequest request, RoleAuth roleAuth) {
        LambdaQueryWrapper<RoleAuth> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<RoleAuth> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<RoleAuth> findRoleAuths(RoleAuth roleAuth) {
        LambdaQueryWrapper<RoleAuth> queryWrapper = new LambdaQueryWrapper<>();
        if (roleAuth.getRoleid() != null) {
            queryWrapper.eq(RoleAuth::getRoleid, roleAuth.getRoleid());
        }
        if (roleAuth.getAuthid() != null) {
            queryWrapper.eq(RoleAuth::getAuthid, roleAuth.getAuthid());
        }
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<RoleAuth> getRoleAuthsByRoleId(String roleId) {
        return baseMapper.selectList(new LambdaQueryWrapper<RoleAuth>().eq(RoleAuth::getRoleid, roleId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createRoleAuth(RoleAuth roleAuth) {
        this.save(roleAuth);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRoleAuth(RoleAuth roleAuth) {
        this.saveOrUpdate(roleAuth);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoleAuth(RoleAuth roleAuth) {
        LambdaQueryWrapper<RoleAuth> wrapper = new LambdaQueryWrapper<>();
        // TODO 设置删除条件
        this.remove(wrapper);
    }

    @Override
    public void deleteRoleAuthsByRoleId(String[] roleIds) {
        List<String> list = Arrays.asList(roleIds);
        baseMapper.delete(new LambdaQueryWrapper<RoleAuth>().in(RoleAuth::getRoleid, list));
    }

    @Override
    public void deleteRoleAuthsByAuthId(String[] authIds) {
        List<String> list = Arrays.asList(authIds);
        baseMapper.delete(new LambdaQueryWrapper<RoleAuth>().in(RoleAuth::getAuthid, list));
    }
}
