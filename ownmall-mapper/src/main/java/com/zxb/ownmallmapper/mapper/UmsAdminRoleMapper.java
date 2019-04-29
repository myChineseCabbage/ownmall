package com.zxb.ownmallmapper.mapper;

import com.zxb.ownmallmapper.pojo.UmsAdminRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsAdminRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_admin_role
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(@Param("adminId") String adminId, @Param("roleId") String roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_admin_role
     *
     * @mbggenerated
     */
    int insert(UmsAdminRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_admin_role
     *
     * @mbggenerated
     */
    List<UmsAdminRole> selectAll();
}