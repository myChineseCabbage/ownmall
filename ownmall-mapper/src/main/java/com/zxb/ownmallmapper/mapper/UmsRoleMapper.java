package com.zxb.ownmallmapper.mapper;

import com.zxb.ownmallmapper.pojo.UmsRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UmsRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_role
     *
     * @mbggenerated
     */
    int insert(UmsRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_role
     *
     * @mbggenerated
     */
    List<UmsRole> selectAll();

    /**
     * 在当前的系统删除角色
     * @param umsRole
     * @return
     */
    int deleteRoleOfThisSys(UmsRole umsRole);

    /**
     * 更新角色信息
     * @param umsRole
     * @return
     */
    int updateByRoleId(UmsRole umsRole);

    /**
     * 查询角色关联角色组的列表
     * @param sysId
     * @return
     */
    List<Map<String,String>> selectAllRolesOfThisSys(String sysId);
}