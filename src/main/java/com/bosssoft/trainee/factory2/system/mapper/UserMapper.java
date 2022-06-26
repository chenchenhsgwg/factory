package com.bosssoft.trainee.factory2.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.trainee.factory2.system.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {

    IPage<User> getUserDetails(Page page, @Param("user") User user);

    User getUserDetail(@Param("username") String username);

    IPage<User> getFriendDetails(Page page, @Param("user") User user);

    IPage<User> getNotFriendDetails(Page page, @Param("user") User user);

    IPage<User> getNotFriendDetailsByRole(Page page, @Param("user") User user , @Param("roleName") String roleName);

    User getUserDetailById(@Param("userId") int userId);

    IPage<User> getNotFriendByRoleName(Page page, @Param("user") User user , @Param("roleName") String roleName);

    IPage<User> getFriendByRoleName(Page page, @Param("user") User user , @Param("roleName") String roleName);

}

