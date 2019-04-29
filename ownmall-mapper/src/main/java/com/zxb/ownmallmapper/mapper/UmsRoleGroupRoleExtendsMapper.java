package com.zxb.ownmallmapper.mapper;

import org.apache.ibatis.annotations.Param;

public interface UmsRoleGroupRoleExtendsMapper extends  UmsRoleGroupRoleMapper {

    int deleteByRoleId(@Param("roleId") String roleId);
}
