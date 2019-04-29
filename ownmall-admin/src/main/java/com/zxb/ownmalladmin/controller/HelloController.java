package com.zxb.ownmalladmin.controller;


import com.alibaba.fastjson.JSONObject;
import com.zxb.ownmalladmin.invar.ResponseCode;
import com.zxb.ownmalladmin.service.SysInfoService;
import com.zxb.ownmallmapper.pojo.Sysinfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class HelloController {

    @Autowired
    private SysInfoService sysInfoServiceIml;

    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping("/helloAdmin")
    public String hello(){
        return "后台管理系统";
    }

    @ApiOperation(value="添加子系统",notes="")
    @RequestMapping(value =  "/addSonSys",method = RequestMethod.POST)
    public String addSonSys(@RequestBody Sysinfo sysinfo){
        int i = sysInfoServiceIml.addSonSystem(sysinfo);
        if(i==1){
            return "添加子系统成功";
        }
        return  "添加子系统失败";
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
    public JSONObject updataSysinfo(@RequestBody Sysinfo sysinfo){
        JSONObject jsonObject = new JSONObject();
        try{
            int i = sysInfoServiceIml.updataSysinfoByPrimaryKey(sysinfo);
            if(i==1){
                jsonObject.put("RetCode",ResponseCode.SUCCESS_CODE);
                jsonObject.put("RetMsg",ResponseCode.SUCCESS_DEFAULT_MSG);
            }else{
                jsonObject.put("RetCode",ResponseCode.UPDATA_SYSINFO_ERROR_CODE);
                jsonObject.put("RetMsg",ResponseCode.UPDATA_SYSINFO_ERROR_MSG);
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("RetCode", ResponseCode.UPDATA_SYSINFO_EXCEPTION_CODE);
            jsonObject.put("RetMsg",ResponseCode.UPDATA_SYSINFO_EXCEPTION_MSG);
        }

        return  jsonObject;
    }
}
