package com.bosssoft.trainee.factory2.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.trainee.factory2.common.PagedRequest;
import com.bosssoft.trainee.factory2.system.entity.Address;

import java.util.List;

public interface IAddressService extends IService<Address> {

    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param address address
     * @return IPage<Address>
     */
    IPage<Address> findAddresses(PagedRequest request, Address address);

    /**
     * 查询（所有）
     *
     * @param address address
     * @return List<Address>
     */
    List<Address> findAddresses(Address address);

    /**
     * 新增
     *
     * @param address address
     */
    void createAddress(Address address);

    /**
     * 修改
     *
     * @param address address
     */
    void updateAddress(Address address);

    /**
     * 删除
     *
     * @param address address
     */
    void deleteAddress(Address address);

    /**
     * 删除多个
     *
     * @param addrIds String[]
     */
    void deleteAddressesById(String[] addrIds);

    Address getAddressDetails(Address address);
}

