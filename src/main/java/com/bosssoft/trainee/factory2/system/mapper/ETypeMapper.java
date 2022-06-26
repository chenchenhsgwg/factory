package com.bosssoft.trainee.factory2.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.trainee.factory2.system.entity.EType;
import org.apache.ibatis.annotations.Param;

public interface ETypeMapper extends BaseMapper<EType> {

    IPage<EType> getETypeDetails(Page page, @Param("eType") EType eType);

    EType getETypeDetail(String name);
}
