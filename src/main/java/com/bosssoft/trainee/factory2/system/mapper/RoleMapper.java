package com.bosssoft.trainee.factory2.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.trainee.factory2.system.entity.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    List<Role> findUserRole(String userName);
}
