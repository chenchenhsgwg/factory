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

    IPage<User> getNotFriendByIdAndRole(User user, String roleName, PagedRequest request);

    IPage<User> getFriendByIdAndRole(User user, String roleName, PagedRequest request);

    User getUserByUsername(String username);

    IPage<User> findFriendDetails(User user, PagedRequest request);

    IPage<User> findNotFriendDetails(User user, PagedRequest request);

    void createUser(User user);

    void updateUser(User user);

    void deleteUsers(String[] userIds);

    void regist(User user);
}
