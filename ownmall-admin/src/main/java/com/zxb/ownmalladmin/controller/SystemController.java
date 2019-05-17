package com.zxb.ownmalladmin.controller;


import com.alibaba.fastjson.JSONObject;
import com.zxb.ownmalladmin.invar.ResponseCode;
import com.zxb.ownmalladmin.service.SysInfoService;
import com.zxb.ownmallmapper.pojo.Sysinfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class SystemController {
    private Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    private SysInfoService sysInfoServiceIml;

    @RequiresRoles("admin")
    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping("/helloAdmin")
    public String hello(){
        return "后台管理系统";
    }

    @ApiOperation(value="添加子系统",notes="")
    @RequestMapping(value =  "/addSonSys",method = RequestMethod.POST)
    public JSONObject addSonSys(@RequestBody Sysinfo sysinfo){
        JSONObject jsonObject = new JSONObject();
        try{
            System.out.println(sysinfo.toString());
            int i = sysInfoServiceIml.addSonSystem(sysinfo);
            if (i == 1) {
                jsonObject.put("RetCode", ResponseCode.SUCCESS_CODE);
                jsonObject.put("RetMsg", ResponseCode.SUCCESS_DEFAULT_MSG);
            } else {
                jsonObject.put("RetCode", ResponseCode.UPDATA_SYSINFO_ERROR_CODE);
                jsonObject.put("RetMsg", ResponseCode.UPDATA_SYSINFO_ERROR_MSG);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  jsonObject;
    }

    @ApiOperation(value = "根据系统id查询系统信息",notes = "")
    @RequestMapping(value = "/selectInfoOfThis", method = RequestMethod.POST)
    public String selSysInfoOfThis(@RequestBody JSONObject jsonObj){
        JSONObject jsonObject = new JSONObject();
        try {
            Sysinfo sysinfo = sysInfoServiceIml.selectSysInfoBySysNo(jsonObj.getString("sysNo"));
            return jsonObject.toJSONString(sysinfo);
        }catch (Exception e){
            e.printStackTrace();

            jsonObj.put("RetCode", ResponseCode.SEL_SYSINFO_ERROR_CODE);
            jsonObj.put("RetMsg",ResponseCode.SEL_SYSINFO_ERROR_MSG);
            return jsonObject.toJSONString();
        }
    }
    @ApiOperation(value = "修改系统基本信息",notes = "")
    @RequestMapping(value = "/updataSysinfo",method = RequestMethod.POST)
    public JSONObject updataSysinfo(@RequestBody Sysinfo sysinfo) {
        JSONObject jsonObject = new JSONObject();
        try {
            int i = sysInfoServiceIml.updataSysinfoByPrimaryKey(sysinfo);
            if (i == 1) {
                jsonObject.put("RetCode", ResponseCode.SUCCESS_CODE);
                jsonObject.put("RetMsg", ResponseCode.SUCCESS_DEFAULT_MSG);
            } else {
                jsonObject.put("RetCode", ResponseCode.UPDATA_SYSINFO_ERROR_CODE);
                jsonObject.put("RetMsg", ResponseCode.UPDATA_SYSINFO_ERROR_MSG);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("RetCode", ResponseCode.UPDATA_SYSINFO_EXCEPTION_CODE);
            jsonObject.put("RetMsg", ResponseCode.UPDATA_SYSINFO_EXCEPTION_MSG);
        }

        return jsonObject;
    }

    @ApiOperation(value = "查询子系统列表",notes = "")
    @RequestMapping(value = "/selSonSystem", method = RequestMethod.POST)
    public String selSonSystem(@RequestBody JSONObject jsonObj){
        logger.debug("debug查询子系统列表");
        logger.info("info查询子系统列表");
        JSONObject jsonObject = new JSONObject();
        try {
            List<Sysinfo> sysinfo = sysInfoServiceIml.selectSonSystem(jsonObj.getString("sysNo"));
            jsonObject.put("RetCode", ResponseCode.SUCCESS_CODE);
            jsonObject.put("RetMsg", ResponseCode.SUCCESS_DEFAULT_MSG);
            jsonObject.put("systems",sysinfo);
            return jsonObject.toJSONString(jsonObject);
        }catch (Exception e){
            e.printStackTrace();
            jsonObj.put("RetCode", ResponseCode.SEL_SYSINFO_ERROR_CODE);
            jsonObj.put("RetMsg",ResponseCode.SEL_SYSINFO_ERROR_MSG);
            return jsonObject.toJSONString();
        }
    }

}
