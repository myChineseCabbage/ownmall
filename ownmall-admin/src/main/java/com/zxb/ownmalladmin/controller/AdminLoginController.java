package com.zxb.ownmalladmin.controller;

import com.alibaba.fastjson.JSONObject;
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
    private Logger logger = LoggerFactory.getLogger(SystemController.class);

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
            resultObject.put("msg", "登录成功");
        } catch (IncorrectCredentialsException e) {
            resultObject.put("msg", "密码错误");
        } catch (LockedAccountException e) {
            resultObject.put("msg", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            resultObject.put("msg", "该用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObject.toString();

    }
}
