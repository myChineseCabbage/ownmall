package com.zxb.ownmalladmin.service;

import com.zxb.ownmallmapper.pojo.UmsRole;
import com.zxb.ownmallmapper.pojo.UmsRoleGroupRole;

public interface UmsRoleService {

    /**
     * 新增角色
     * @param umsRole
     * @param UmsRoleGroupRole
     * @return
     */
    int insertRole(UmsRole umsRole, UmsRoleGroupRole UmsRoleGroupRole);

    /**
     * 删除角色和角色组的关系
     * @param umsRole
     * @return
     */
    int deleteRolesOfThisSys(UmsRole umsRole);
}
