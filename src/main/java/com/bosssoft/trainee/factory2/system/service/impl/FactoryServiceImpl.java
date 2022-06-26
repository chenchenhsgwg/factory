package com.bosssoft.trainee.factory2.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.trainee.factory2.system.mapper.FactoryMapper;
import com.bosssoft.trainee.factory2.system.service.IFactoryService;
import com.bosssoft.trainee.factory2.common.Constant;
import com.bosssoft.trainee.factory2.common.PagedRequest;
import com.bosssoft.trainee.factory2.system.entity.Factory;
import com.bosssoft.trainee.factory2.system.entity.User;
import com.bosssoft.trainee.factory2.utils.SortUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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
public class FactoryServiceImpl extends ServiceImpl<FactoryMapper, Factory> implements IFactoryService {

    private final FactoryMapper factoryMapper;

    @Override
    public IPage<Factory> findFactories(PagedRequest request, Factory factory) {
        LambdaQueryWrapper<Factory> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(factory.getName())) {
            queryWrapper.eq(Factory::getName, factory.getName());
        }
        if (factory.getUserId() != null) {
            queryWrapper.eq(Factory::getUserId, factory.getUserId());
        }
        Page<Factory> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public IPage<Factory> findFactoryDetails(Factory factory, PagedRequest request) {
        Page<User> page = new Page<>();
        SortUtil.handlePageSort(request, page, "id", Constant.ORDER_ASC, false);
        return this.baseMapper.getFactoryDetails(page, factory);
    }

    @Override
    public List<Factory> findFactories(Factory factory) {
        LambdaQueryWrapper<Factory> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createFactory(Factory factory) {
        this.save(factory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFactory(Factory factory) {
        this.saveOrUpdate(factory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFactory(Factory factory) {
        LambdaQueryWrapper<Factory> wrapper = new LambdaQueryWrapper<>();
        if (!factory.getEnabled()) {
            wrapper.eq(Factory::getEnabled, false);
        }
        this.remove(wrapper);
    }

    @Override
    public void deleteByUserIds(String[] userIds) {
        Arrays.stream(userIds).forEach(id -> baseMapper.deleteByUserId(Integer.parseInt(id)));
    }

    @Override
    public void deleteFactories(String[] ids) {
        List<String> list = Arrays.asList(ids);
        this.baseMapper.deleteBatchIds(list);
    }

    @Override
    public Factory findByName(String factoryName) {
        return this.baseMapper.getFactoryDetail(factoryName);
    }

    @Override
    public void switchFactories(String[] ids) {
        List<String> list = Arrays.asList(ids);
        list.forEach(id -> {
            Factory factory = this.baseMapper.selectById(id);
            factory.setEnabled(!factory.getEnabled());
            this.updateFactory(factory);
        });
    }
}
