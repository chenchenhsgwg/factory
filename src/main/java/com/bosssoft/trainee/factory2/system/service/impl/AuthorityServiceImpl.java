package com.bosssoft.trainee.factory2.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.trainee.factory2.system.mapper.AuthorityMapper;
import com.bosssoft.trainee.factory2.system.service.IAuthorityService;
import com.bosssoft.trainee.factory2.common.PagedRequest;
import com.bosssoft.trainee.factory2.system.entity.Authority;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority> implements IAuthorityService {

    private final AuthorityMapper authorityMapper;

    @Override
    public IPage<Authority> findAuthorities(PagedRequest request, Authority authority) {
        LambdaQueryWrapper<Authority> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(authority.getName())) {
            queryWrapper.eq(Authority::getName, authority.getName());
        }
        if (StringUtils.isNotBlank(authority.getResource())) {
            queryWrapper.eq(Authority::getResource, authority.getResource());
        }
        Page<Authority> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Authority> findAuthorities(Authority authority) {
        LambdaQueryWrapper<Authority> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Authority> findUserAuthorities(String username) {
        return this.baseMapper.findUserAuthorities(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createAuthority(Authority authority) {
        this.save(authority);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAuthority(Authority authority) {
        this.saveOrUpdate(authority);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAuthority(Authority authority) {
        LambdaQueryWrapper<Authority> wrapper = new LambdaQueryWrapper<>();
        if (authority.getId() != null) {
            wrapper.eq(Authority::getId, authority.getId());
        }
        this.remove(wrapper);
    }
}
