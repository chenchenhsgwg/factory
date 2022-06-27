package com.bosssoft.trainee.factory2.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.trainee.factory2.common.PagedRequest;
import com.bosssoft.trainee.factory2.system.entity.Equipment;

import java.util.List;

public interface IEquipmentService extends IService<Equipment> {
    /**
     * 查询（分页）
     *
     * @param request   QueryRequest
     * @param equipment equipment
     * @return IPage<Equipment>
     */
    IPage<Equipment> findEquipments(PagedRequest request, Equipment equipment);

    /**
     * 查询（所有）
     *
     * @param equipment equipment
     * @return List<Equipment>
     */
    List<Equipment> findEquipments(Equipment equipment);

    IPage<Equipment> findEquipmentDetails(Equipment equipment, PagedRequest request);

    /**
     * 新增
     *
     * @param equipment equipment
     */
    void createEquipment(Equipment equipment);

    /**
     * 修改
     *
     * @param equipment equipment
     */
    void updateEquipment(Equipment equipment);

    /**
     * 删除
     *
     * @param equipment equipment
     */
    void deleteEquipment(Equipment equipment);

    void deleteEquipments(String[] ids);

    void deleteEquipmentsByFactoryId(String factoryId);

    void switchEquipments(String[] ids);

    IPage<Equipment> findActiveEquipmentDetails(String userId, PagedRequest request);

}
