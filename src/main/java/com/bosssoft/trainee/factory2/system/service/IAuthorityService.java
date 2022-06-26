package com.bosssoft.trainee.factory2.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.trainee.factory2.common.PagedRequest;
import com.bosssoft.trainee.factory2.system.entity.Authority;

import java.util.List;

public interface IAuthorityService extends IService<Authority> {
    /**
     * 查询（分页）
     *
     * @param request   QueryRequest
     * @param authority authority
     * @return IPage<Authority>
     */
    IPage<Authority> findAuthorities(PagedRequest request, Authority authority);

    /**
     * 查询（所有）
     *
     * @param authority authority
     * @return List<Authority>
     */
    List<Authority> findAuthorities(Authority authority);

    List<Authority> findUserAuthorities(String username);

    /**
     * 新增
     *
     * @param authority authority
     */
    void createAuthority(Authority authority);

    /**
     * 修改
     *
     * @param authority authority
     */
    void updateAuthority(Authority authority);

    /**
     * 删除
     *
     * @param authority authority
     */
    void deleteAuthority(Authority authority);
}
