package com.zxb.ownmallmapper.mapper;

import com.zxb.ownmallmapper.pojo.UmsRole;

public interface UmsRoleExtendsMapper extends  UmsRoleMapper {

    /**
     * 在当前的系统删除角色
     * @param umsRole
     * @return
     */
    int deleteRoleOfThisSys(UmsRole umsRole);
}
