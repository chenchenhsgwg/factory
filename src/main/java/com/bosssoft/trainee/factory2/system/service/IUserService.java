package com.bosssoft.trainee.factory2.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.trainee.factory2.common.PagedRequest;
import com.bosssoft.trainee.factory2.system.entity.User;

import java.util.List;

public interface IUserService extends IService<User> {

    IPage<User> findUsers(PagedRequest request, User user);

    List<User> findUsers(User user);

    IPage<User> findUserDetails(User user, PagedRequest request);

    User findByName(String username);

    User findById(int userId);

    User getUserById(int id);

    User getUserByUsername(String username);

    void createUser(User user);

    void updateUser(User user);

    void deleteUsers(String[] userIds);

    void regist(User user);
}
