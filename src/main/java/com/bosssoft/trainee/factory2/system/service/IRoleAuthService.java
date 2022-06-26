package com.bosssoft.trainee.factory2.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.trainee.factory2.common.PagedRequest;
import com.bosssoft.trainee.factory2.system.entity.RoleAuth;

import java.util.List;

public interface IRoleAuthService extends IService<RoleAuth> {
    /**
     * 查询（分页）
     *
     * @param request  QueryRequest
     * @param roleAuth roleAuth
     * @return IPage<RoleAuth>
     */
    IPage<RoleAuth> findRoleAuths(PagedRequest request, RoleAuth roleAuth);

    /**
     * 查询（所有）
     *
     * @param roleAuth roleAuth
     * @return List<RoleAuth>
     */
    List<RoleAuth> findRoleAuths(RoleAuth roleAuth);

    List<RoleAuth> getRoleAuthsByRoleId(String roleId);

    /**
     * 新增
     *
     * @param roleAuth roleAuth
     */
    void createRoleAuth(RoleAuth roleAuth);

    /**
     * 修改
     *
     * @param roleAuth roleAuth
     */
    void updateRoleAuth(RoleAuth roleAuth);

    /**
     * 删除
     *
     * @param roleAuth roleAuth
     */
    void deleteRoleAuth(RoleAuth roleAuth);

    void deleteRoleAuthsByRoleId(String[] roleIds);

    void deleteRoleAuthsByAuthId(String[] authIds);
}
