package com.bosssoft.trainee.factory2.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.bosssoft.trainee.factory2.system.service.IAddressService;
import com.bosssoft.trainee.factory2.common.BaseController;
import com.bosssoft.trainee.factory2.common.PagedRequest;
import com.bosssoft.trainee.factory2.common.Response;
import com.bosssoft.trainee.factory2.system.entity.Address;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

@Validated
@RestController
@RequestMapping("address")
public class AddressController extends BaseController {

    @Autowired
    private IAddressService addressService;

    @GetMapping
    @RequiresPermissions("address:list")
    public Map<String, Object> addressList(PagedRequest request, Address address) {
        return getDataTable(this.addressService.findAddresses(request, address));
    }


    @PostMapping
    @RequiresPermissions("address:add")
    public Response addAddress(@RequestBody @Valid Address address) {
        this.addressService.createAddress(address);
        return new Response().code("200").message("新增地址成功").status("success");
    }

    @DeleteMapping("/{addrIds}")
    @RequiresPermissions("address:delete")
    public Response deleteAddresses(@NotBlank(message = "{required}") @PathVariable String addrIds) {
        String[] ids = addrIds.split(StringPool.COMMA);
        this.addressService.deleteAddressesById(ids);
        return new Response().code("200").message("删除地址成功").status("success");
    }


    @PutMapping
    @RequiresPermissions("address:update")
    public Response updateAddress(@RequestBody @Valid Address address) {
        this.addressService.updateAddress(address);
        return new Response().code("200").message("修改地址成功").status("success");
    }

}
