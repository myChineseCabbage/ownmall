package com.zxb.ownmalladmin.controller;

import com.alibaba.fastjson.JSONObject;
import com.zxb.ownmalladmin.invar.ResponseCode;
import com.zxb.ownmalladmin.service.UmsRolePermissionService;
import com.zxb.ownmallmapper.pojo.UmsRolePermission;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/rolePermission")
public class RolePermissionController {

    @Autowired
    private UmsRolePermissionService umsRolePermissionService;

    @ApiOperation(value = "给角色分配权限",notes = "")
    @RequestMapping(value = "/assignPermissions",method = RequestMethod.POST)
    public JSONObject assignPermissions(@RequestBody List<UmsRolePermission> umsRolePermissions){
        JSONObject resultObj = new JSONObject();
        try {
            umsRolePermissionService.insertRolePermission(umsRolePermissions);
            resultObj.put("RetCode", ResponseCode.SUCCESS_CODE);
            resultObj.put("RetMsg", ResponseCode.SUCCESS_DEFAULT_MSG);
        }catch (Exception e){
            e.printStackTrace();
            resultObj.put("RetCode",ResponseCode.EXCEPTION_DEFAULT_CODE);
            resultObj.put("RetMsg",ResponseCode.EXCEPTION_DEFAULT_MSG);
        }
        return  resultObj;
    }

}
