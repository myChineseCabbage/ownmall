package com.zxb.ownmalladmin.service;


import com.zxb.ownmallmapper.pojo.UmsRoleGroup;

import java.util.List;

/**
 * 角色组相关操作
 */
public interface UmsRoleGroupService {

    /**
     * 新增角色组
     * @param umsRoleGroup
     * @return
     */
    int insertRoleGroup(UmsRoleGroup umsRoleGroup);

    /**
     * 根据系统id 查询所有相关角色组
     * @return
     */
    List<UmsRoleGroup> selectRoleGroups(UmsRoleGroup umsRoleGroup);
}
