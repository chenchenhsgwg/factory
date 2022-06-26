package com.bosssoft.trainee.factory2.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.bosssoft.trainee.factory2.system.service.IFactoryService;
import com.bosssoft.trainee.factory2.common.BaseController;
import com.bosssoft.trainee.factory2.common.PagedRequest;
import com.bosssoft.trainee.factory2.common.Response;
import com.bosssoft.trainee.factory2.system.entity.Factory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

@Validated
@RestController
@RequestMapping("factory")
public class FactoryController extends BaseController {

    @Autowired
    private IFactoryService factoryService;

    @GetMapping("check/{factoryName}")
    public boolean checkUserName(@NotBlank(message = "{required}") @PathVariable String factoryName) {
        return this.factoryService.findByName(factoryName) == null;
    }

    @GetMapping
    @RequiresPermissions("factory:view")
    public Map<String, Object> factoryList(PagedRequest request, Factory factory) {
        return getDataTable(this.factoryService.findFactoryDetails(factory, request));
    }

    @PostMapping
    @RequiresPermissions("factory:add")
    public Response addFactory(@RequestBody @Valid Factory factory) {
        this.factoryService.createFactory(factory);
        return new Response().code("200").message("新增工厂成功").status("success");
    }

    @DeleteMapping("/{factoryIds}")
    @RequiresPermissions("factory:delete")
    public Response deleteFactory(@NotBlank(message = "{required}") @PathVariable String factoryIds) {
        String[] ids = factoryIds.split(StringPool.COMMA);
        this.factoryService.deleteFactories(ids);
        return new Response().code("200").message("删除工厂成功").status("success");
    }

    @PutMapping
    @RequiresPermissions("factory:update")
    public Response updateFactory(@RequestBody @Valid Factory factory) {
        this.factoryService.updateFactory(factory);
        return new Response().code("200").message("修改工厂成功").status("success");
    }

    @PutMapping("/switch/{factoryIds}")
    @RequiresPermissions("factory:update")
    public Response switchFactory(@NotBlank(message = "{required}") @PathVariable String factoryIds) {
        String[] ids = factoryIds.split(StringPool.COMMA);
        this.factoryService.switchFactories(ids);
        return new Response().code("200").message("修改工厂成功").status("success");
    }
}
