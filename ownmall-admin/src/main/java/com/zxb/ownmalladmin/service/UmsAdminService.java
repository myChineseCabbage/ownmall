package com.zxb.ownmalladmin.service;

import com.zxb.ownmallmapper.pojo.UmsAdmin;
import com.zxb.ownmallmapper.pojo.UmsPermission;
import com.zxb.ownmallmapper.pojo.UmsRole;

import java.util.List;

/**
 * 管理员相关的接口
 */
public interface UmsAdminService {

    /**
     * 添加管理员
     * @param umsAdmin
     * @return
     */
    int addAdmin(UmsAdmin umsAdmin);

    UmsAdmin selectByUmsAdmin(UmsAdmin umsAdmin);

    /**
     * 获取该管理员的所有角色信息
     * @param umsAdmin
     * @return
     */
    List<UmsRole> selectAllRolesOfUmsAdmin(UmsAdmin umsAdmin);

    /**
     * 获取该管理员的所有权限信息集合
     * @param umsAdmin
     * @return
     */
    List<UmsPermission> selectAllPermissionsOfUmsAdmin(UmsAdmin umsAdmin);

}
