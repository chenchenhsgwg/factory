package com.bosssoft.trainee.factory2.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.trainee.factory2.system.entity.Address;
import org.apache.ibatis.annotations.Param;

public interface AddressMapper extends BaseMapper<Address> {

    Address getAddressDetails(@Param("address") Address address);

}
