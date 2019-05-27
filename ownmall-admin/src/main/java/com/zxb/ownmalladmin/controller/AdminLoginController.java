package com.zxb.ownmalladmin.controller;

import com.alibaba.fastjson.JSONObject;
import com.zxb.ownmalladmin.invar.ResponseCode;
import com.zxb.ownmallmapper.pojo.UmsAdmin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class AdminLoginController {
    private Logger logger = LoggerFactory.getLogger(AdminLoginController.class);

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String  vueLogin(@RequestBody UmsAdmin umsAdmin){
        logger.debug(umsAdmin.toString());
        JSONObject resultObject = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(umsAdmin.getAdminName(),umsAdmin.getPassword());
        logger.debug(token.getUsername()+":"+token.getPassword());
        try {
            subject.login(token);
            resultObject.put("token", subject.getSession().getId());
            resultObject.put("RetCode", ResponseCode.SUCCESS_CODE);
            resultObject.put("RetMsg", "登录成功");
        } catch (IncorrectCredentialsException e) {
            resultObject.put("RetCode", "ADMINPASSSERROR");
            resultObject.put("RetMsg", "密码错误");
        } catch (LockedAccountException e) {
            resultObject.put("RetCode", "ADMINNOTUSE");
            resultObject.put("RetMsg", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            resultObject.put("RetCode", "ADMINNOTEXIST");
            resultObject.put("RetMsg", "该用户不存在");
        } catch (Exception e) {
            resultObject.put("RetCode", "SYSTEMEXCEPTION");
            resultObject.put("RetMsg", "系统异常");
            logger.debug(e.getMessage(),e);
        }
        return resultObject.toString();

    }
}
