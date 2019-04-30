package com.zxb.ownmalladmin.service.impl;

import com.zxb.ownmalladmin.service.UmsRoleService;
import com.zxb.ownmallcommon.utils.DataGenerationUtil;
import com.zxb.ownmallcommon.utils.DateTimeUtils;
import com.zxb.ownmallmapper.mapper.UmsRoleGroupRoleMapper;
import com.zxb.ownmallmapper.mapper.UmsRoleMapper;
import com.zxb.ownmallmapper.pojo.UmsRole;
import com.zxb.ownmallmapper.pojo.UmsRoleGroupRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UmsRoleServiceImpl implements UmsRoleService {

    @Resource
    private UmsRoleMapper umsRoleMapper;

    @Resource
    private UmsRoleGroupRoleMapper umsRoleGroupRoleMapper;

    /**
     * 新增角色
     * @param umsRole
     * @param umsRoleGroupRole
     * @return
     */
    @Transactional
    @Override
    public int insertRole(UmsRole umsRole, UmsRoleGroupRole umsRoleGroupRole) {
        int i = 0;
        String roleId = DataGenerationUtil.getUUID32();
        String rgrId = DataGenerationUtil.getUUID32();
        Timestamp ts = new Timestamp(new Date().getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = simpleDateFormat.format(ts);
        //插入角色
        umsRole.setRoleId(roleId);
        umsRole.setAdminCount("0");
        umsRole.setSort("0");
        i = umsRoleMapper.insert(umsRole);
        //插入角色与角色组关联关系
        umsRoleGroupRole.setRgrId(rgrId);
        umsRoleGroupRole.setRoleId(roleId);
        umsRoleGroupRole.setCreateTime(createTime);
        umsRoleGroupRole.setUpdateTime(createTime);
        i +=umsRoleGroupRoleMapper.insert(umsRoleGroupRole);
        return i;
    }

    /**
     * 删除角色
     * @param umsRole
     * @return
     */
    @Transactional
    @Override
    public int deleteRolesOfThisSys(UmsRole umsRole) {
       int i = 0;
       //删除角色表中的数据
       i = umsRoleMapper.deleteRoleOfThisSys(umsRole);
        //删除角色与角色组关系表中的数据
       i += umsRoleGroupRoleMapper.deleteByRoleId(umsRole.getRoleId());
       return i;
    }


    /**
     * 更新校色相关信息
     * @param umsRole
     * @param umsRoleGroupRole
     * @return
     */
    @Transactional
    @Override
    public int updateRole(UmsRole umsRole, UmsRoleGroupRole umsRoleGroupRole) {
        int i = 0;
        i = umsRoleMapper.updateByRoleId(umsRole);
        umsRoleGroupRole.setUpdateTime(DateTimeUtils.dateTimeFormat());
        i+= umsRoleGroupRoleMapper.updateRoleGroupRoleByRoleId(umsRoleGroupRole);
        return i;
    }
}
