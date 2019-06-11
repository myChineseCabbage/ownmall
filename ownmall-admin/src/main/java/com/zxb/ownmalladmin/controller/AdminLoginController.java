package com.zxb.ownmalladmin.controller;


import com.zxb.ownmalladmin.invar.ResponseCode;
import com.zxb.ownmalladmin.service.UmsAdminService;
import com.zxb.ownmallmapper.pojo.UmsAdmin;
import com.zxb.ownmallmapper.pojo.UmsPermission;
import com.zxb.ownmallmapper.pojo.UmsRole;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/")
public class AdminLoginController {
    private Logger logger = LoggerFactory.getLogger(AdminLoginController.class);

    @Autowired
    private UmsAdminService umsAdminServiceImpl;

    @ApiOperation(value = "登录接口",notes = " ")
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
            //登录成功之后，查询资源，角色，以及用户信息，并将这些信息返给前端
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
        logger.debug(resultObject.toString());
        return resultObject.toString();
    }


    /**
     * 查询管理员信息 ，角色，菜单等
     * @param adminId
     * @return
     */
    public String selUserInfo(String adminId){
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setAdminId(adminId);
        UmsAdmin admin = umsAdminServiceImpl.selectByUmsAdmin(umsAdmin);
        List<UmsRole> umsRoles = umsAdminServiceImpl.selectAllRolesOfUmsAdmin(umsAdmin);
        List<UmsPermission> umsPermissions = umsAdminServiceImpl.selectAllPermissionsOfUmsAdmin(umsAdmin);
        JSONObject resultObj = JSONObject.fromObject(admin);
        resultObj.put("umsRoles",umsRoles);
        resultObj.put("umsPermissions",umsPermissions);
        resultObj.put("RetCode",ResponseCode.SUCCESS_CODE);
        resultObj.put("RetMsg",ResponseCode.ERROE_DEFAULT_MSG);
        return resultObj.toString();
    }
    
}
