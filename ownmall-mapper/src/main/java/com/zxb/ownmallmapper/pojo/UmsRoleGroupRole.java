package com.zxb.ownmallmapper.pojo;

import java.io.Serializable;

public class UmsRoleGroupRole implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_role_group_role.rgr_id
     *
     * @mbggenerated
     */
    private String rgrId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_role_group_role.group_id
     *
     * @mbggenerated
     */
    private String groupId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_role_group_role.role_id
     *
     * @mbggenerated
     */
    private String roleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_role_group_role.create_time
     *
     * @mbggenerated
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_role_group_role.update_time
     *
     * @mbggenerated
     */
    private String updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ums_role_group_role
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_role_group_role.rgr_id
     *
     * @return the value of ums_role_group_role.rgr_id
     *
     * @mbggenerated
     */
    public String getRgrId() {
        return rgrId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_role_group_role.rgr_id
     *
     * @param rgrId the value for ums_role_group_role.rgr_id
     *
     * @mbggenerated
     */
    public void setRgrId(String rgrId) {
        this.rgrId = rgrId == null ? null : rgrId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_role_group_role.group_id
     *
     * @return the value of ums_role_group_role.group_id
     *
     * @mbggenerated
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_role_group_role.group_id
     *
     * @param groupId the value for ums_role_group_role.group_id
     *
     * @mbggenerated
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_role_group_role.role_id
     *
     * @return the value of ums_role_group_role.role_id
     *
     * @mbggenerated
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_role_group_role.role_id
     *
     * @param roleId the value for ums_role_group_role.role_id
     *
     * @mbggenerated
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_role_group_role.create_time
     *
     * @return the value of ums_role_group_role.create_time
     *
     * @mbggenerated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_role_group_role.create_time
     *
     * @param createTime the value for ums_role_group_role.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_role_group_role.update_time
     *
     * @return the value of ums_role_group_role.update_time
     *
     * @mbggenerated
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_role_group_role.update_time
     *
     * @param updateTime the value for ums_role_group_role.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_role_group_role
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rgrId=").append(rgrId);
        sb.append(", groupId=").append(groupId);
        sb.append(", roleId=").append(roleId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}