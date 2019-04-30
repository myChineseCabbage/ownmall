package com.zxb.ownmalladmin.service;

import com.zxb.ownmallmapper.pojo.UmsRolePermission;

import java.util.List;

public interface UmsRolePermissionService {

    /**
     * 给角色分配权限
     * @param umsRolePermissions
     * @return
     */
    int insertRolePermission(List<UmsRolePermission> umsRolePermissions);
}
