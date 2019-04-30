package com.zxb.ownmalladmin.controller;

import com.alibaba.fastjson.JSONObject;
import com.zxb.ownmalladmin.invar.ResponseCode;
import com.zxb.ownmalladmin.service.UmsPermissionService;
import com.zxb.ownmallmapper.pojo.UmsPermission;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/admin/permission")
public class PermissionController {

    @Autowired
    private UmsPermissionService umsPermissionServiceImpl;


    @ApiOperation(value = "新增权限")
    @RequestMapping(value = "/addPermsiion",method = RequestMethod.POST)
    public JSONObject insertPermission(@RequestBody  UmsPermission umsPermission){
        JSONObject resultObj = new JSONObject();
        try {
            int i = umsPermissionServiceImpl.insertPermission(umsPermission);
            if(i==1){
                resultObj.put("RetCode", ResponseCode.SUCCESS_CODE);
                resultObj.put("RetMsg",ResponseCode.SUCCESS_DEFAULT_MSG);
            }else {
                resultObj.put("RetCode", ResponseCode.ERROR_DEFAULT_CODE);
                resultObj.put("RetMsg", ResponseCode.ERROE_DEFAULT_MSG);
            }
        }catch (Exception e){
                 e.printStackTrace();
                resultObj.put("RetCode",ResponseCode.EXCEPTION_DEFAULT_CODE);
                resultObj.put("RetMsg",ResponseCode.EXCEPTION_DEFAULT_MSG);
        }
        return  resultObj;
    }
}
