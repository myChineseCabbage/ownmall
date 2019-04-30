package com.zxb.ownmalladmin.service.impl;

import com.zxb.ownmalladmin.service.UmsRolePermissionService;
import com.zxb.ownmallmapper.mapper.UmsRolePermissionMapper;
import com.zxb.ownmallmapper.pojo.UmsRolePermission;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色和权限关系操作表
 */

@Service
public class UmsRolePermissionServiceImpl implements UmsRolePermissionService {

    @Resource
    private UmsRolePermissionMapper umsRolePermissionMapper;

    /**
     * 权限分配操作
     * @param umsRolePermissions
     * @return
     */
    @Transactional
    @Override
    public int insertRolePermission(List<UmsRolePermission> umsRolePermissions) {
        int num = 0;
        //分配权限操作
        //先查看是否有该角色权限已经分配 删除所有的该角色的权限联系情况 再依次添加权限信息。
        long count = umsRolePermissionMapper.selCountsByRoleId(umsRolePermissions.get(0));
        if(count>0){ //如果角色权限关系已经存在 则删除原有的权限
            num -= umsRolePermissionMapper.deleteByRoleId(umsRolePermissions.get(0));
        }
        //分配新权限
        for (int i =0;i<umsRolePermissions.size();i++){
            num += umsRolePermissionMapper.insert(umsRolePermissions.get(i));
        }
        return num;
    }
}
