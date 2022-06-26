package com.bosssoft.trainee.factory2.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.trainee.factory2.common.PagedRequest;
import com.bosssoft.trainee.factory2.system.entity.Role;

import java.util.List;

public interface IRoleService extends IService<Role> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param role    role
     * @return IPage<Role>
     */
    IPage<Role> findRoles(PagedRequest request, Role role);

    /**
     * 查询（所有）
     *
     * @param role role
     * @return List<Role>
     */
    List<Role> findRoles(Role role);

    List<Role> findUserRole(String userName);

    Role findByName(String roleName);

    /**
     * 新增
     *
     * @param role role
     */
    void createRole(Role role);

    /**
     * 修改
     *
     * @param role role
     */
    void updateRole(Role role);

    /**
     * 删除
     *
     * @param role role
     */
    void deleteRole(Role role);

    /**
     * 删除
     *
     * @param roleIds String[]
     */
    void deleteRoles(String[] roleIds) throws Exception;
}
