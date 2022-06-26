package com.bosssoft.trainee.factory2.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.trainee.factory2.system.entity.UserRole;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 根据用户Id删除该用户的角色关系
     */
    Boolean deleteByUserId(@Param("userId") int userId);

    /**
     * 根据角色Id删除该角色的用户关系
     **/
    Boolean deleteByRoleId(@Param("roleId") int roleId);

}
