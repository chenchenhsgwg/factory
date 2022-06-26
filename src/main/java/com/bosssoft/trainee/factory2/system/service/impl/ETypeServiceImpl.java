package com.bosssoft.trainee.factory2.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.trainee.factory2.system.mapper.ETypeMapper;
import com.bosssoft.trainee.factory2.system.service.IETypeService;
import com.bosssoft.trainee.factory2.common.Constant;
import com.bosssoft.trainee.factory2.common.PagedRequest;
import com.bosssoft.trainee.factory2.common.Tree;
import com.bosssoft.trainee.factory2.system.entity.EType;
import com.bosssoft.trainee.factory2.system.entity.User;
import com.bosssoft.trainee.factory2.utils.SortUtil;
import com.bosssoft.trainee.factory2.utils.TreeUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Primary
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ETypeServiceImpl extends ServiceImpl<ETypeMapper, EType> implements IETypeService {

    private final ETypeMapper eTypeMapper;

    @Override
    public IPage<EType> findETypes(PagedRequest request, EType eType) {
        LambdaQueryWrapper<EType> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(eType.getName())) {
            queryWrapper.eq(EType::getName, eType.getName());
        }
        Page<EType> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<EType> findETypes(EType eType) {
        LambdaQueryWrapper<EType> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public Map<String, Object> findEquipmentTypes(PagedRequest request, EType eType) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<EType> types = findEquipmentTypes(eType, request);
            List<Tree<EType>> trees = new ArrayList<>();
            buildTrees(trees, types);
            Tree<EType> typeTree = TreeUtil.build(trees);

            result.put("rows", typeTree);
            result.put("total", types.size());
        } catch (Exception e) {
            log.error("获取类型列表失败", e);
            result.put("rows", null);
            result.put("total", 0);
        }
        return result;
    }

    @Override
    public List<EType> findEquipmentTypes(EType eType, PagedRequest request) {
        QueryWrapper<EType> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(eType.getName()))
            queryWrapper.lambda().eq(EType::getName, eType.getName());
        SortUtil.handleWrapperSort(request, queryWrapper, "name", Constant.ORDER_ASC, true);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public EType findByName(String name) {
        return this.baseMapper.getETypeDetail(name);
    }

    @Override
    public IPage<EType> findETypeDetails(EType eType, PagedRequest request) {
        Page<User> page = new Page<>();
        SortUtil.handlePageSort(request, page, "name", Constant.ORDER_ASC, true);
        return this.baseMapper.getETypeDetails(page, eType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createEType(EType eType) {
        this.save(eType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEType(EType eType) {
        this.saveOrUpdate(eType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteEType(EType eType) {
        LambdaQueryWrapper<EType> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(eType.getName())) {
            wrapper.eq(EType::getName, eType.getName());
        }
        this.remove(wrapper);
    }

    @Override
    public void deleteETypes(String[] typeIds) {
        this.delete(Arrays.asList(typeIds));
    }

    private void delete(List<String> typeIds) {
        removeByIds(typeIds);
        //查询被删除的类型的下级类型
        LambdaQueryWrapper<EType> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(EType::getParentId, typeIds);
        List<EType> eTypes = baseMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(eTypes)) {
            //删除下级类型
            List<String> typeIdList = new ArrayList<>();
            eTypes.forEach(d -> typeIdList.add(String.valueOf(d.getId())));
            this.delete(typeIdList);
        }
    }

    private void buildTrees(List<Tree<EType>> trees, List<EType> eTypes) {
        eTypes.forEach(eType -> {
            Tree<EType> tree = new Tree<>();
            tree.setId(eType.getId().toString());
            tree.setKey(tree.getId());
            if (eType.getParentId() != null)
                tree.setParentId(eType.getParentId().toString());
            tree.setParentName(eType.getParentName());
            tree.setText(eType.getName());
            tree.setTitle(tree.getText());
            tree.setValue(tree.getId());
            trees.add(tree);
        });
    }
}
