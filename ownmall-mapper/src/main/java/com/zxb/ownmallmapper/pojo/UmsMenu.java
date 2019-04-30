package com.zxb.ownmallmapper.pojo;

import java.io.Serializable;

public class UmsMenu implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_menu.menu_id
     *
     * @mbggenerated
     */
    private String menuId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_menu.menu_name
     *
     * @mbggenerated
     */
    private String menuName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_menu.menu_url
     *
     * @mbggenerated
     */
    private String menuUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_menu.menu_parent_id
     *
     * @mbggenerated
     */
    private String menuParentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_menu.sys_id
     *
     * @mbggenerated
     */
    private String sysId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ums_menu
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_menu.menu_id
     *
     * @return the value of ums_menu.menu_id
     *
     * @mbggenerated
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_menu.menu_id
     *
     * @param menuId the value for ums_menu.menu_id
     *
     * @mbggenerated
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_menu.menu_name
     *
     * @return the value of ums_menu.menu_name
     *
     * @mbggenerated
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_menu.menu_name
     *
     * @param menuName the value for ums_menu.menu_name
     *
     * @mbggenerated
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_menu.menu_url
     *
     * @return the value of ums_menu.menu_url
     *
     * @mbggenerated
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_menu.menu_url
     *
     * @param menuUrl the value for ums_menu.menu_url
     *
     * @mbggenerated
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_menu.menu_parent_id
     *
     * @return the value of ums_menu.menu_parent_id
     *
     * @mbggenerated
     */
    public String getMenuParentId() {
        return menuParentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_menu.menu_parent_id
     *
     * @param menuParentId the value for ums_menu.menu_parent_id
     *
     * @mbggenerated
     */
    public void setMenuParentId(String menuParentId) {
        this.menuParentId = menuParentId == null ? null : menuParentId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_menu.sys_id
     *
     * @return the value of ums_menu.sys_id
     *
     * @mbggenerated
     */
    public String getSysId() {
        return sysId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_menu.sys_id
     *
     * @param sysId the value for ums_menu.sys_id
     *
     * @mbggenerated
     */
    public void setSysId(String sysId) {
        this.sysId = sysId == null ? null : sysId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_menu
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", menuId=").append(menuId);
        sb.append(", menuName=").append(menuName);
        sb.append(", menuUrl=").append(menuUrl);
        sb.append(", menuParentId=").append(menuParentId);
        sb.append(", sysId=").append(sysId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}