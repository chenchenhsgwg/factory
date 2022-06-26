package com.bosssoft.trainee.factory2.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.trainee.factory2.system.entity.Equipment;
import org.apache.ibatis.annotations.Param;

public interface EquipmentMapper extends BaseMapper<Equipment> {

    IPage<Equipment> getEquipmentDetails(Page page, @Param("equipment") Equipment equipment);

    IPage<Equipment> getActiveEquipmentDetails(Page page, @Param("factoryId") String factoryId);

//    Boolean rentEquipment(Equipment equipment);
////
//    Boolean returnEquipment(Equipment equipment);
}
