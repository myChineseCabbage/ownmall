package com.zxb.ownmallmapper.mapper;

import com.zxb.ownmallmapper.pojo.UmsRoleGroupRole;
import org.apache.ibatis.annotations.Param;

public interface UmsRoleGroupRoleExtendsMapper extends  UmsRoleGroupRoleMapper {

    int deleteByRoleId(@Param("roleId") String roleId);

    int updateRoleGroupRoleByRoleId(UmsRoleGroupRole umsRoleGroupRole);
}
