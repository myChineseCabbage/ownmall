package com.zxb.ownmalladmin.service;

import com.zxb.ownmallmapper.pojo.UmsRole;
import com.zxb.ownmallmapper.pojo.UmsRoleGroupRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    /**
     * 更新角色相关信息
     * @param umsRole
     * @param umsRoleGroupRole
     * @return
     */
    int updateRole(UmsRole umsRole,UmsRoleGroupRole umsRoleGroupRole);

    /**
     * 查询角色关联角色组的列表
     * @param sysId
     * @return
     */
    List<Map<String,String>> selectAllRolesOfThisSys(@Param("sysId") String sysId);
}
