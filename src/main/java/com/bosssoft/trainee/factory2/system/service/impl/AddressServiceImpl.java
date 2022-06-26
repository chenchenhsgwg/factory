package com.bosssoft.trainee.factory2.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.trainee.factory2.system.mapper.AddressMapper;
import com.bosssoft.trainee.factory2.system.service.IAddressService;
import com.bosssoft.trainee.factory2.common.PagedRequest;
import com.bosssoft.trainee.factory2.system.entity.Address;
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
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

    private final AddressMapper addressMapper;

    @Override
    public IPage<Address> findAddresses(PagedRequest request, Address address) {
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(address.getLocation())) {
            queryWrapper.eq(Address::getLocation, address.getLocation());
        }
        if (StringUtils.isNotBlank(address.getReceiver())) {
            queryWrapper.eq(Address::getReceiver, address.getReceiver());
        }
        if (StringUtils.isNotBlank(address.getTelephone())) {
            queryWrapper.eq(Address::getTelephone, address.getTelephone());
        }
        Page<Address> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, true);
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Address> findAddresses(Address address) {
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createAddress(Address address) {
        this.save(address);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAddress(Address address) {
        this.baseMapper.updateById(address);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAddress(Address address) {
        LambdaQueryWrapper<Address> wrapper = new LambdaQueryWrapper<>();
        this.remove(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAddressesById(String[] addrIds) {
        List<String> list = Arrays.asList(addrIds);
        this.baseMapper.deleteBatchIds(list);
    }

    @Override
    public Address getAddressDetails(Address address) {
        return this.baseMapper.getAddressDetails(address);
    }
}
