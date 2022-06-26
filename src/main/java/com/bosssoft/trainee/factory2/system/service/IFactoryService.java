package com.bosssoft.trainee.factory2.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.trainee.factory2.common.PagedRequest;
import com.bosssoft.trainee.factory2.system.entity.Factory;

import java.util.List;

public interface IFactoryService extends IService<Factory> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param factory factory
     * @return IPage<Factory>
     */
    IPage<Factory> findFactories(PagedRequest request, Factory factory);

    IPage<Factory> findFactoryDetails(Factory factory, PagedRequest request);

    /**
     * 查询（所有）
     *
     * @param factory factory
     * @return List<Factory>
     */
    List<Factory> findFactories(Factory factory);

    /**
     * 新增
     *
     * @param factory factory
     */
    void createFactory(Factory factory);

    /**
     * 修改
     *
     * @param factory factory
     */
    void updateFactory(Factory factory);

    /**
     * 删除
     *
     * @param factory factory
     */
    void deleteFactory(Factory factory);

    void deleteFactories(String[] ids);

    void deleteByUserIds(String[] userIds);

    Factory findByName(String factoryName);

    void switchFactories(String[] ids);

}
