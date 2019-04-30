package com.zxb.ownmalladmin.service.impl;

import com.zxb.ownmalladmin.service.UmsPermissionService;
import com.zxb.ownmallcommon.utils.DataGenerationUtil;
import com.zxb.ownmallcommon.utils.DateTimeUtils;
import com.zxb.ownmallmapper.mapper.UmsPermissionMapper;
import com.zxb.ownmallmapper.pojo.UmsPermission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 权限相关功能操作
 */
@Service
public class UmsPermissionServiceImpl implements UmsPermissionService {

    @Resource
    private UmsPermissionMapper umsPermissionMapper;

    /**
     * 添加权限（资源）
     * @param umsPermission
     * @return
     */
    @Override
    public int insertPermission(UmsPermission umsPermission) {
        umsPermission.setPermissionId(DataGenerationUtil.getUUID32());
        umsPermission.setCreateTime(DateTimeUtils.dateTimeFormat());
        umsPermission.setUpdateTime(DateTimeUtils.dateTimeFormat());
        System.out.println(umsPermission);
        int i = umsPermissionMapper.insert(umsPermission);
        return i;
    }
}
