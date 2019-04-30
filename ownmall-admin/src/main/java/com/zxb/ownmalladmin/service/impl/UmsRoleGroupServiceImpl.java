package com.zxb.ownmalladmin.service.impl;

import com.zxb.ownmalladmin.service.UmsRoleGroupService;
import com.zxb.ownmallcommon.utils.DataGenerationUtil;
import com.zxb.ownmallmapper.mapper.UmsRoleGroupMapper;
import com.zxb.ownmallmapper.pojo.UmsRoleGroup;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 角色相关操作
 */
@Service
public class UmsRoleGroupServiceImpl implements UmsRoleGroupService {

    @Resource
    private UmsRoleGroupMapper umsRoleGroupMapper;

    /**
     * 新增角色组
     * @param umsRoleGroup
     * @return
     */
    @Override
    public int insertRoleGroup(UmsRoleGroup umsRoleGroup) {
        String groupId = DataGenerationUtil.getUUID32();
        Timestamp ts = new Timestamp(new Date().getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = simpleDateFormat.format(ts);
        umsRoleGroup.setGroupId(groupId);
        umsRoleGroup.setCreateTime(createTime);
        umsRoleGroup.setUpdateTime(createTime);
        int i = umsRoleGroupMapper.insert(umsRoleGroup);
        return i;
    }

    /**
     * 根据系统id查询角色组
     * @param umsRoleGroup
     * @return
     */
    @Override
    public List<UmsRoleGroup> selectRoleGroups(@RequestBody  UmsRoleGroup umsRoleGroup) {
        List<UmsRoleGroup> list = umsRoleGroupMapper.selectAllBySysId(umsRoleGroup.getSysId());
        return list;
    }
}
