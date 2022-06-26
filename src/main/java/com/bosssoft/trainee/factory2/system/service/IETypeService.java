package com.bosssoft.trainee.factory2.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.trainee.factory2.common.PagedRequest;
import com.bosssoft.trainee.factory2.system.entity.EType;

import java.util.List;
import java.util.Map;

public interface IETypeService extends IService<EType> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param eType   eType
     * @return IPage<EType>
     */
    IPage<EType> findETypes(PagedRequest request, EType eType);

    /**
     * 查询（所有）
     *
     * @param eType eType
     * @return List<EType>
     */
    List<EType> findETypes(EType eType);

    Map<String, Object> findEquipmentTypes(PagedRequest request, EType eType);

    List<EType> findEquipmentTypes(EType eType, PagedRequest request);

    EType findByName(String name);

    IPage<EType> findETypeDetails(EType eType, PagedRequest request);

    /**
     * 新增
     *
     * @param eType eType
     */
    void createEType(EType eType);

    /**
     * 修改
     *
     * @param eType eType
     */
    void updateEType(EType eType);

    /**
     * 删除
     *
     * @param eType eType
     */
    void deleteEType(EType eType);

    void deleteETypes(String[] typeIds);
}
