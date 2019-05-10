package com.zxb.ownmalladmin.controller;

import com.alibaba.fastjson.JSONObject;
import com.zxb.ownmalladmin.invar.ResponseCode;
import com.zxb.ownmalladmin.service.UmsRoleGroupService;
import com.zxb.ownmallmapper.pojo.UmsRoleGroup;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/group")
public class RoleGroupController {

    @Autowired
    private UmsRoleGroupService umsRoleGroupServiceImpl;

    @ApiOperation(value = "添加角色组",notes = "")
    @RequestMapping(value = "/addRoleGroup",method = RequestMethod.POST)
    public JSONObject addRoleGroup(@RequestBody UmsRoleGroup umsRoleGroup){
        JSONObject resultObj = new JSONObject();
        try{
            int i = umsRoleGroupServiceImpl.insertRoleGroup(umsRoleGroup);
            if(i==1) { //添加成功
                resultObj.put("RetCode", ResponseCode.SUCCESS_CODE);
                resultObj.put("RetMsg", ResponseCode.SUCCESS_DEFAULT_MSG);
            }else{ //添加失败
                resultObj.put("RetCode", ResponseCode.ERROR_DEFAULT_CODE);
                resultObj.put("RetMsg", ResponseCode.ERROE_DEFAULT_MSG);
            }
        }catch (Exception e){ //操作异常
            resultObj.put("RetCode",ResponseCode.EXCEPTION_DEFAULT_CODE);
            resultObj.put("RetMsg",ResponseCode.EXCEPTION_DEFAULT_MSG);
        }
        return  resultObj;
    }

    /**
     * 查询当前系统下的角色组
     * @param umsRoleGroup
     * @return
     */
    @ApiOperation(value = "查询当前系统下的角色组",notes = "" )
    @RequestMapping(value = "/selRoleGroups",method = RequestMethod.POST)
    public JSONObject selectRoleGroupBySysId(@RequestBody UmsRoleGroup umsRoleGroup){
        JSONObject resultObj = new JSONObject();
        try{
            List<UmsRoleGroup> list = umsRoleGroupServiceImpl.selectRoleGroups(umsRoleGroup);
            resultObj.put("RoleGroupList",list);
            resultObj.put("RetCode",ResponseCode.SUCCESS_CODE);
            resultObj.put("RetMsg",ResponseCode.SUCCESS_DEFAULT_MSG);
        }catch (Exception e){
            e.printStackTrace();
            resultObj.put("RetCode",ResponseCode.EXCEPTION_DEFAULT_CODE);
            resultObj.put("RetMsg",ResponseCode.EXCEPTION_DEFAULT_MSG);
        }
        return resultObj;
    }
}
