package com.bosssoft.trainee.factory2.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.bosssoft.trainee.factory2.system.service.IETypeService;
import com.bosssoft.trainee.factory2.common.BaseController;
import com.bosssoft.trainee.factory2.common.PagedRequest;
import com.bosssoft.trainee.factory2.common.Response;
import com.bosssoft.trainee.factory2.system.entity.EType;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * 设备类型 Controller
 *
 * @author lty
 * @date 2021-07-12 09:42:39
 */
@Validated
@RestController
@RequestMapping("etype")
public class ETypeController extends BaseController {

    @Autowired
    private IETypeService eTypeService;


    @GetMapping
    @RequiresPermissions("etype:view")
    public Map<String, Object> eTypeList(PagedRequest request, EType eType) {
        return getDataTable(this.eTypeService.findETypeDetails(eType, request));
    }

    @GetMapping("/tree")
    @RequiresPermissions("etype:view")
    public Map<String, Object> eTypeTreeList(PagedRequest request, EType eType) {
        return this.eTypeService.findEquipmentTypes(request, eType);
    }

    @PostMapping
    @RequiresPermissions("etype:add")
    public Response addEType(@RequestBody @Valid EType eType) {
        this.eTypeService.createEType(eType);
        return new Response().code("200").message("新增设备类型成功").status("success");
    }


    @DeleteMapping("/{typeIds}")
    @RequiresPermissions("etype:delete")
    public Response deleteEType(@NotBlank(message = "{required}") @PathVariable String typeIds) {
        String[] ids = typeIds.split(StringPool.COMMA);
        this.eTypeService.deleteETypes(ids);
        return new Response().code("200").message("删除设备类型成功").status("success");
    }


    @PutMapping
    @RequiresPermissions("etype:update")
    public Response updateEType(@RequestBody @Valid EType eType) {
        this.eTypeService.updateEType(eType);
        return new Response().code("200").message("修改设备类型成功").status("success");
    }

}
