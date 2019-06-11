package com.zxb.ownmalladmin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zxb.ownmalladmin.controller.SystemController;
import com.zxb.ownmalladmin.service.UmsAdminService;
import com.zxb.ownmallcommon.utils.DataGenerationUtil;
import com.zxb.ownmallcommon.utils.DateTimeUtils;
import com.zxb.ownmallmapper.mapper.UmsAdminMapper;
import com.zxb.ownmallmapper.pojo.UmsAdmin;
import com.zxb.ownmallmapper.pojo.UmsPermission;
import com.zxb.ownmallmapper.pojo.UmsRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 管理员接口相关的实现
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    private Logger logger = LoggerFactory.getLogger(UmsAdminService.class);
    @Resource
    private UmsAdminMapper umsAdminMapper;
    /**
     * 添加管理员
     * @param umsAdmin
     * @return
     */
    @Override
    public int addAdmin(UmsAdmin umsAdmin) {
        //对数据进行处理
        //1.adminId的生成
        umsAdmin.setAdminId(DataGenerationUtil.getUUID32());
        umsAdmin.setCreateTime(DateTimeUtils.dateTimeFormat());
//        umsAdmin.setSysId(""); //系统ID需要从当前的会话中获取，测试暂时从前端获取
        int i = umsAdminMapper.insert(umsAdmin);
        return i;
    }

    /**
     * 根据条件查询客户
     * @param umsAdmin
     * @return
     */
    @Override
    public UmsAdmin selectByUmsAdmin(UmsAdmin umsAdmin) {
        UmsAdmin admin = umsAdminMapper.selectUmsAdminOne(umsAdmin);
        logger.debug(admin.toString());
        return admin;
    }

    /**
     * 查询当前用户在当前系统下的所有角色信息
     * @param umsAdmin
     * @return
     */
    @Override
    public List<UmsRole> selectAllRolesOfUmsAdmin(UmsAdmin umsAdmin) {
        List<UmsRole> umsRoleList = umsAdminMapper.selectRoleOfThisAdmin(umsAdmin);
        logger.debug(umsRoleList.toString());
        return umsRoleList;
    }

    /**
     * 查询当前用户在当前系统下的所有权限信息
     * @param umsAdmin
     * @return
     */
    @Override
    public List<UmsPermission> selectAllPermissionsOfUmsAdmin(UmsAdmin umsAdmin) {
        List<UmsPermission> umsPermissionList = umsAdminMapper.selectPermissionOfThisAdmin(umsAdmin);
        logger.debug(umsPermissionList.toString());
        return umsPermissionList;
    }

    /**
     * 查询管理员信息，角色，资源
     * @param umsAdmin
     * @return
     */
    @Override
    public JSONObject selAdminInfoAlls(UmsAdmin umsAdmin) {
        UmsAdmin u = umsAdminMapper.selectUmsAdminOne(umsAdmin);
        return null;
    }
}
