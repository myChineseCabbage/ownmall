package com.zxb.ownmalladmin.controller;


import com.alibaba.fastjson.JSONObject;
import com.zxb.ownmalladmin.invar.ResponseCode;
import com.zxb.ownmalladmin.service.UmsAdminService;
import com.zxb.ownmallmapper.pojo.UmsAdmin;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户注册和登录，修改密码。完善修改信息信息
 */
@RestController()
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private UmsAdminService umsAdminServiceImpl;

    @ApiOperation(value = "在当前系统添加当前系统的管理员",notes = "当前系统的管理员只能对当前系统的工作")
    @RequestMapping(value = "/addAdmin",method = RequestMethod.POST)
    public JSONObject addAdmin(@RequestBody UmsAdmin umsAdmin){
        JSONObject resultObj = new JSONObject();
        try {
            umsAdminServiceImpl.addAdmin(umsAdmin);
            resultObj.put("RetCode", ResponseCode.SUCCESS_CODE);
            resultObj.put("RetMsg", ResponseCode.SUCCESS_DEFAULT_MSG);
        }catch (Exception e){
            e.printStackTrace();
            resultObj.put("RetCode", ResponseCode.ERROR_DEFAULT_CODE);
            resultObj.put("RetMsg", ResponseCode.ERROE_DEFAULT_MSG);
        }
        return  resultObj;
    }


}
