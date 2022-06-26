package com.bosssoft.trainee.factory2.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.bosssoft.trainee.factory2.system.service.IFactoryService;
import com.bosssoft.trainee.factory2.system.service.IUserRoleService;
import com.bosssoft.trainee.factory2.system.service.IUserService;
import com.bosssoft.trainee.factory2.common.BaseController;
import com.bosssoft.trainee.factory2.common.PagedRequest;
import com.bosssoft.trainee.factory2.common.Response;
import com.bosssoft.trainee.factory2.system.entity.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;


@Validated
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IFactoryService factoryService;

    @GetMapping("check/{username}")
    public boolean checkUserName(@NotBlank(message = "{required}") @PathVariable String username) {
        return this.userService.findByName(username) == null;
    }

    @GetMapping("/{username}")
    public User detail(@NotBlank(message = "{required}") @PathVariable String username) {
        return this.userService.findByName(username);
    }

//    @GetMapping("/{userId}")
//    public User detail(@NotBlank(message = "{required}") @PathVariable int userId) {
//        return this.userService.findById(userId);
//    }

    @GetMapping
    @RequiresPermissions("user:view")
    public Map<String, Object> userList(PagedRequest request, User user) {
        return getDataTable(this.userService.findUserDetails(user, request));
    }

    @PostMapping
    @RequiresPermissions("user:add")
    public Response addUser(@RequestBody @Valid User user) {
        this.userService.createUser(user);
        return new Response().code("200").message("新增用户成功").status("success");
    }

    @DeleteMapping("/{userIds}")
    @ResponseBody
    @RequiresPermissions("user:delete")
    public Response deleteUser(@NotBlank(message = "{required}") @PathVariable String userIds) {
        String[] ids = userIds.split(StringPool.COMMA);
        this.userRoleService.deleteUserRolesByUserId(ids);
        this.factoryService.deleteByUserIds(ids);
        this.userService.deleteUsers(ids);
        return new Response().code("200").message("删除用户成功").status("success");
    }

    @PutMapping
    @RequiresPermissions("user:update")
    public Response updateUser(User user) {
        this.userService.updateUser(user);
        return new Response().code("200").message("修改用户成功").status("success");
    }

}
