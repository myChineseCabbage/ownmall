package com.zxb.ownmallmapper.mapper;

import com.zxb.ownmallmapper.pojo.UmsRoleGroup;

import java.util.List;

public interface UmsRoleGroupExtendsMapper extends UmsRoleGroupMapper {

    /**查询当前系统下的角色组
     * 根据系统id
     * @param sysId
     * @return
     */
    List<UmsRoleGroup> selectAllBySysId(String sysId);

    /**
     * 在当前系统下添加角色组
     * @param record
     * @return
     */
    @Override
    int insert(UmsRoleGroup record);
}
