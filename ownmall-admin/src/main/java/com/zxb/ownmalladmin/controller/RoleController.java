package com.zxb.ownmalladmin.controller;

import com.alibaba.fastjson.JSONObject;
import com.zxb.ownmalladmin.invar.ResponseCode;
import com.zxb.ownmalladmin.service.UmsAdminService;
import com.zxb.ownmalladmin.service.UmsRoleService;
import com.zxb.ownmallmapper.pojo.UmsRole;
import com.zxb.ownmallmapper.pojo.UmsRoleGroupRole;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/role")
public class RoleController {
    private Logger logger = LoggerFactory.getLogger(UmsAdminService.class);
    @Autowired
    private UmsRoleService umsRoleServiceImpl;

    @ApiOperation(value = "新增角色",notes = "")
    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    public JSONObject insertRoles(@RequestBody JSONObject jsonObject){
        JSONObject resultObj = new JSONObject();
        try{
            UmsRole umsRole = new UmsRole();
            umsRole.setRoleName(jsonObject.getString("roleName"));
            umsRole.setAvailable(jsonObject.getString("available"));
            umsRole.setSysId(jsonObject.getString("sysId"));
            if(jsonObject.containsKey("description")){
                umsRole.setDescription(jsonObject.getString("description"));
            }else {
                umsRole.setDescription("");
            }
            UmsRoleGroupRole umsRoleGroupRole = new UmsRoleGroupRole();
            umsRoleGroupRole.setGroupId(jsonObject.getString("groupId"));

            umsRoleServiceImpl.insertRole(umsRole,umsRoleGroupRole);
            resultObj.put("RetCode", ResponseCode.SUCCESS_CODE);
            resultObj.put("RetMsg", ResponseCode.SUCCESS_DEFAULT_MSG);
        }catch (NullPointerException e){
            e.printStackTrace();
            resultObj.put("RetCode", "DDDDDDDD");
            resultObj.put("RetMsg", "参数不完整");
        }catch (Exception e){
            e.printStackTrace();
            resultObj.put("RetCode", ResponseCode.EXCEPTION_DEFAULT_CODE);
            resultObj.put("RetMsg", ResponseCode.EXCEPTION_DEFAULT_MSG);
        }
        return  resultObj;
    }

    @ApiOperation(value = "删除角色",notes = "删除当前系统下的角色")
    @RequestMapping(value = "/deleteRole",method = RequestMethod.POST)
    public JSONObject deleteRole(@RequestBody UmsRole umsRole){
        JSONObject resultObj = new JSONObject();
        try {
            umsRoleServiceImpl.deleteRolesOfThisSys(umsRole);
            resultObj.put("RetCode", ResponseCode.SUCCESS_CODE);
            resultObj.put("RetMsg", ResponseCode.SUCCESS_DEFAULT_MSG);
        }catch (Exception e){
            e.printStackTrace();
            resultObj.put("RetCode", ResponseCode.EXCEPTION_DEFAULT_CODE);
            resultObj.put("RetMsg", ResponseCode.EXCEPTION_DEFAULT_MSG);
        }
        return  resultObj;
    }


    @ApiOperation(value = "更新角色信息",notes = "")
    @RequestMapping(value = "/updateRoleInfo",method = RequestMethod.POST)
    public JSONObject updateRoleInfo(@RequestBody JSONObject jsonObject){
        JSONObject resultObj = new JSONObject();
        try{
            UmsRole umsRole = new UmsRole();
            umsRole.setRoleId(jsonObject.getString("roleId"));
            umsRole.setRoleName(jsonObject.getString("roleName"));
            umsRole.setAvailable(jsonObject.getString("available"));
            umsRole.setSysId(jsonObject.getString("sysId"));
            if(jsonObject.containsKey("description")){
                umsRole.setDescription(jsonObject.getString("description"));
            }else {
                umsRole.setDescription("");
            }
            UmsRoleGroupRole umsRoleGroupRole = new UmsRoleGroupRole();
            umsRoleGroupRole.setGroupId(jsonObject.getString("groupId"));

            umsRoleServiceImpl.updateRole(umsRole,umsRoleGroupRole);
            resultObj.put("RetCode", ResponseCode.SUCCESS_CODE);
            resultObj.put("RetMsg", ResponseCode.SUCCESS_DEFAULT_MSG);
        }catch (NullPointerException e){
            e.printStackTrace();
            resultObj.put("RetCode", "DDDDDDDD");
            resultObj.put("RetMsg", "参数不完整");
        }catch (Exception e){
            e.printStackTrace();
            resultObj.put("RetCode", ResponseCode.EXCEPTION_DEFAULT_CODE);
            resultObj.put("RetMsg", ResponseCode.EXCEPTION_DEFAULT_MSG);
        }
        return  resultObj;
    }

    @ApiOperation(value = "查询角色关联角色组列表",notes = "")
    @RequestMapping(value = "/selectAllRolesOfThisSys",method = RequestMethod.POST)
    public JSONObject selectAllRolesOfThisSys(@RequestBody JSONObject jsonObject){
        Subject subject  = SecurityUtils.getSubject();
        Session session = subject.getSession();
        logger.debug("session:"+session.toString());
        JSONObject resultObj = new JSONObject();
        try{
            resultObj.put("roleGroups",umsRoleServiceImpl.selectAllRolesOfThisSys(jsonObject.getString("sysId")));
            resultObj.put("RetCode", ResponseCode.SUCCESS_CODE);
            resultObj.put("RetMsg", ResponseCode.SUCCESS_DEFAULT_MSG);
        }catch (Exception e){
            e.printStackTrace();
            resultObj.put("RetCode", ResponseCode.EXCEPTION_DEFAULT_CODE);
            resultObj.put("RetMsg", ResponseCode.EXCEPTION_DEFAULT_MSG);
        }
        return  resultObj;
    }

}
