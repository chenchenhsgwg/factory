package com.bosssoft.trainee.factory2.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.trainee.factory2.system.entity.Authority;

import java.util.List;

public interface AuthorityMapper extends BaseMapper<Authority> {

    List<Authority> findUserAuthorities(String username);

}
