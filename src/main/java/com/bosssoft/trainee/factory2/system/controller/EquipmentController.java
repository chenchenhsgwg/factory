package com.bosssoft.trainee.factory2.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.bosssoft.trainee.factory2.system.service.IEquipmentService;
import com.bosssoft.trainee.factory2.common.BaseController;
import com.bosssoft.trainee.factory2.common.PagedRequest;
import com.bosssoft.trainee.factory2.common.Response;
import com.bosssoft.trainee.factory2.system.entity.Equipment;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

@Validated
@RestController
@RequestMapping("equipment")
public class EquipmentController extends BaseController {

    @Autowired
    private IEquipmentService equipmentService;

    @GetMapping
    @RequiresPermissions("equipment:view")
    public Map<String, Object> equipmentList(PagedRequest request, Equipment equipment,
                                             @RequestParam(required = false,value = "typeName") String typeName,
                                             @RequestParam(required = false,value = "name") String name) {
//        System.out.println(equipment);
        equipment.setTypeName(typeName);equipment.setName(name);
        System.out.println(equipment);
        return getDataTable(this.equipmentService.findEquipmentDetails(equipment, request));
    }

    @GetMapping("/{factoryId}")
    @RequiresPermissions("equipment:view")
    public Map<String, Object> activeEquipmentList(PagedRequest request, @NotBlank(message = "{required}") @PathVariable String factoryId) {
//        if (userId.equals("7"))return equipmentList(request,new Equipment());
        return getDataTable(this.equipmentService.findActiveEquipmentDetails(factoryId, request));
    }

    @PostMapping
    @RequiresPermissions("equipment:add")
    public Response addEquipment(@RequestBody @Valid Equipment equipment) {
        this.equipmentService.createEquipment(equipment);
        return new Response().code("200").message("新增设备成功").status("success");
    }

    @DeleteMapping("/{equipIds}")
    @RequiresPermissions("equipment:delete")
    public Response deleteEquipment(@NotBlank(message = "{required}") @PathVariable String equipIds) {
        String[] ids = equipIds.split(StringPool.COMMA);
        this.equipmentService.deleteEquipments(ids);
        return new Response().code("200").message("删除设备成功").status("success");
    }

    @PutMapping
    @RequiresPermissions("equipment:update")
    public Response updateEquipment(@RequestBody @Valid Equipment equipment) {
        this.equipmentService.updateEquipment(equipment);
        return new Response().code("200").message("修改设备成功").status("success");
    }

    @PutMapping("/switch/{equipIds}")
    @RequiresPermissions("equipment:update")
    public Response switchEquipment(@NotBlank(message = "{required}") @PathVariable String equipIds) {
        String[] ids = equipIds.split(StringPool.COMMA);
        this.equipmentService.switchEquipments(ids);
        return new Response().code("200").message("修改设备成功").status("success");
    }

}
