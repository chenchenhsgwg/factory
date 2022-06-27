package com.bosssoft.trainee.factory2.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.trainee.factory2.system.mapper.EquipmentMapper;
import com.bosssoft.trainee.factory2.system.service.IEquipmentService;
import com.bosssoft.trainee.factory2.common.Constant;
import com.bosssoft.trainee.factory2.common.PagedRequest;
import com.bosssoft.trainee.factory2.system.entity.Equipment;
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
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment> implements IEquipmentService {

    private final EquipmentMapper equipmentMapper;

    @Override
    public IPage<Equipment> findEquipments(PagedRequest request, Equipment equipment) {
        LambdaQueryWrapper<Equipment> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(equipment.getName())) {
            queryWrapper.eq(Equipment::getName, equipment.getName());
        }
        if (StringUtils.isNotBlank(equipment.getState())) {
            queryWrapper.eq(Equipment::getState, equipment.getState());
        }
        if (equipment.getFactoryId() != null) {
            queryWrapper.in(Equipment::getFactoryId, (Object) new Integer[]{equipment.getFactoryId(), 5});

        }
        Page<Equipment> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, true);
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Equipment> findEquipments(Equipment equipment) {
        LambdaQueryWrapper<Equipment> queryWrapper = new LambdaQueryWrapper<>();
        if (equipment.getFactoryId() != null) {
            queryWrapper.in(Equipment::getFactoryId, (Object) new Integer[]{equipment.getFactoryId(), 5});
        }
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Equipment> findEquipmentDetails(Equipment equipment, PagedRequest request) {
        Page<User> page = new Page<>();
        SortUtil.handlePageSort(request, page, "id", Constant.ORDER_ASC, false);
        return this.baseMapper.getEquipmentDetails(page, equipment);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createEquipment(Equipment equipment) {
        this.save(equipment);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEquipment(Equipment equipment) {
        this.saveOrUpdate(equipment);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteEquipment(Equipment equipment) {
        LambdaQueryWrapper<Equipment> wrapper = new LambdaQueryWrapper<>();
        if (equipment.getId() != null) {
            wrapper.in(Equipment::getId, (Object) new Integer[]{equipment.getId(), 5});
        }
        this.remove(wrapper);
    }

    @Override
    public void deleteEquipments(String[] ids) {
        List<String> list = Arrays.asList(ids);
        this.baseMapper.deleteBatchIds(list);
    }

    @Override
    public void deleteEquipmentsByFactoryId(String factoryId) {
        this.baseMapper.deleteEquipmentsByFactoryId(factoryId);
    }

    @Override
    public void switchEquipments(String[] ids) {
        List<String> list = Arrays.asList(ids);
        list.forEach(id -> {
            Equipment equipment = this.baseMapper.selectById(id);
            equipment.setEnabled(!equipment.getEnabled());
            this.updateEquipment(equipment);
        });
    }

    @Override
    public IPage<Equipment> findActiveEquipmentDetails(String factoryId, PagedRequest request) {
        Page<User> page = new Page<>();
        SortUtil.handlePageSort(request, page, "id", Constant.ORDER_ASC, false);
        return this.baseMapper.getActiveEquipmentDetails(page, factoryId);
    }
}
