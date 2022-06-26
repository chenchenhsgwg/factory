package com.bosssoft.trainee.factory2.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.trainee.factory2.common.PagedRequest;
import com.bosssoft.trainee.factory2.system.entity.UserRole;

import java.util.List;

public interface IUserRoleService extends IService<UserRole> {

    /**
     * 查询（分页）
     *
     * @param request  QueryRequest
     * @param userRole userRole
     * @return IPage<UserRole>
     */
    IPage<UserRole> findUserRoles(PagedRequest request, UserRole userRole);

    /**
     * 查询（所有）
     *
     * @param userRole userRole
     * @return List<UserRole>
     */
    List<UserRole> findUserRoles(UserRole userRole);

    /**
     * 新增
     *
     * @param userRole userRole
     */
    void createUserRole(UserRole userRole);

    /**
     * 修改
     *
     * @param userRole userRole
     */
    void updateUserRole(UserRole userRole);

    /**
     * 删除
     *
     * @param userRole userRole
     */
    void deleteUserRole(UserRole userRole);

    /**
     * 删除用户角色
     *
     * @param userIds String[]
     */
    void deleteUserRolesByUserId(String[] userIds);

    void deleteUserRolesByRoleId(String[] roleIds);

    /**
     * 用户获取角色
     *
     * @param userId String[]
     */
    List<String> getRoleIdsByUserId(String[] userId);

    /**
     * 角色获取用户
     *
     * @param roleId String[]
     */
    List<String> getUserIdsByRoleId(String[] roleId);
}
