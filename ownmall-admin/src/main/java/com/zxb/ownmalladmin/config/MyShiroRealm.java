package com.zxb.ownmalladmin.config;

import com.alibaba.fastjson.JSONObject;
import com.zxb.ownmalladmin.controller.SystemController;
import com.zxb.ownmalladmin.service.UmsAdminService;
import com.zxb.ownmallmapper.pojo.UmsAdmin;
import com.zxb.ownmallmapper.pojo.UmsPermission;
import com.zxb.ownmallmapper.pojo.UmsRole;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(SystemController.class);


    @Autowired
    private UmsAdminService umsAdminServiceImpl;
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.debug("MyShiroRealm.doGetAuthorizationInfo()>>>>>>>>>授权");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //得到当前登录的用户
        String currentLoginUser = (String)principalCollection.getPrimaryPrincipal();
        //根据该用户获取到角色信息，得到角色名字集合
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setAdminName(currentLoginUser);
        umsAdmin = umsAdminServiceImpl.selectByUmsAdmin(umsAdmin);
        List<UmsRole> roles = umsAdminServiceImpl.selectAllRolesOfUmsAdmin(umsAdmin);
        Set<String> roleNames = new HashSet<>();
        for (int i =0;i<roles.size();i++){
            roleNames.add(roles.get(i).getRoleName());
        }
        //把当前用户的所有角色交给shiro,调用hasRole的时候就根据这些role值判断
        simpleAuthorizationInfo.setRoles(roleNames);
        //根据该用户获取权限信息，得到权限名的集合
        List<UmsPermission> permissions = umsAdminServiceImpl.selectAllPermissionsOfUmsAdmin(umsAdmin);
        Set<String> permissionNames = new HashSet<>();
        for (int i=0;i<permissions.size();i++){
            permissionNames.add(permissions.get(i).getPermissionName());
        }
        //把当前subject对应的权限信息交给shiro,当调用hasPermission的时候就会根据这些判断
        simpleAuthorizationInfo.addStringPermissions(permissionNames);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.debug("MyShiroRealm.doGetAuthenticationInfo()>>>>>>>>>>认证开始");
        //获取用户的输入账号
        String username  = (String) authenticationToken.getPrincipal();
        logger.debug(authenticationToken.getCredentials().toString());
        //通过username从数据库中查找User对象，如果找到或者没找到
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间机制 2分钟类不会执行该方法
        //这里管理员登录
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setAdminName(username);
        umsAdmin = umsAdminServiceImpl.selectByUmsAdmin(umsAdmin);
        logger.debug("查询出来的用户："+umsAdmin.toString());

        if(umsAdmin == null){
            return  null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                umsAdmin,
                umsAdmin.getPassword(),
                ByteSource.Util.bytes(umsAdmin.getCredentialsSalt()),//salt = username+salt
                getName() //realm name
        );
        return authenticationInfo;
    }


}
