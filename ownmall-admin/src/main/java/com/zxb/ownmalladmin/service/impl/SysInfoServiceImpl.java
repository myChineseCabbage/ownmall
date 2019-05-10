package com.zxb.ownmalladmin.service.impl;

import com.zxb.ownmalladmin.service.SysInfoService;
import com.zxb.ownmallcommon.utils.DataGenerationUtil;
import com.zxb.ownmallmapper.mapper.SysinfoMapper;
import com.zxb.ownmallmapper.pojo.Sysinfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



@Service
public class SysInfoServiceImpl implements SysInfoService {

    @Resource
    private SysinfoMapper sysinfoMapper;

    /**
     * 添加子系统到当前系统下
     * @param sysinfo
     * @return
     */
    @Override
    public int addSonSystem(Sysinfo sysinfo){
        //需要对相关参数进行处理
        //node_id的生成
        String nodeId = DataGenerationUtil.getUUID32();
        //系统编码生成
        //1.查出库中所有的系统编码
        //2.生成一个系统编码，判断是否包含其中，若不包含，插入到数据库
        List<String> sysNoList = sysinfoMapper.selectAllSysNo();
        String sysNo ="Sys"+DataGenerationUtil.random4str(4);
        System.out.println(sysNoList);
        if(sysNoList.contains(sysNo)){
            //查询最大的数(查询出来的最大数第一个，因为是按降序排列的）
            //让其加一
           sysNo = sysNoList.get(0);
           sysNo = "Sys"+(Integer.parseInt(sysNo.substring(3))+1);
        }
        //3.对系统密码加密 采用shiro的MD5加密  明文，盐为系统名，加密2次
        String sysPassword = new Md5Hash(sysinfo.getSysPassword(),sysinfo.getSysName(),2).toString();
        sysinfo.setSysPassword(sysPassword);
        //4.系统创建时间时间
        Timestamp ts = new Timestamp(new Date().getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = simpleDateFormat.format(ts);
        sysinfo.setNodeId(nodeId);
        sysinfo.setSysNo(sysNo);
        sysinfo.setSysCreateTime(createTime);
        sysinfo.setSysUpdataTime(createTime);
        int i = sysinfoMapper.insertSysInfo(sysinfo);
        return i;
    }

    /**
     * 根据sysNo查询系统信息
     * @param sysNo
     * @return
     */
    @Override
    public Sysinfo selectSysInfoBySysNo(String sysNo) {
        Sysinfo sysinfo = sysinfoMapper.selectSysInfoBySysNo(sysNo);
        return sysinfo;
    }

    /**
     * 修改系统基本信息
     * @param sysinfo
     * @return
     */
    @Override
    public int updataSysinfoByPrimaryKey(Sysinfo sysinfo) {
        //4.系统创建时间时间 修改时创建时间也要修改
        Timestamp ts = new Timestamp(new Date().getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updataTime = simpleDateFormat.format(ts);
        sysinfo.setSysUpdataTime(updataTime);
        int i = sysinfoMapper.updateByPrimaryKey(sysinfo);
        return i;
    }

    /**
     * 查询当前系统下的所有系统列表
     * @param sysNo
     * @return
     */
    @Override
    public List<Sysinfo> selectSonSystem(String sysNo) {
        List<Sysinfo> list = sysinfoMapper.selectSonSystem(sysNo);
        return list;
    }
}
