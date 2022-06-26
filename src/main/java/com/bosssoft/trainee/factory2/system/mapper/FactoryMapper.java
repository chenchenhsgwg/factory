package com.bosssoft.trainee.factory2.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.trainee.factory2.system.entity.Factory;
import org.apache.ibatis.annotations.Param;

public interface FactoryMapper extends BaseMapper<Factory> {
    IPage<Factory> getFactoryDetails(Page page, @Param("factory") Factory factory);

    Factory getFactoryDetail(@Param("factoryName") String factoryName);

    Boolean deleteByUserId(@Param("userId") int userId);
}
