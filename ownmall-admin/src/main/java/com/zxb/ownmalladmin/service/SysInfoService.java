package com.zxb.ownmalladmin.service;

import com.zxb.ownmallmapper.pojo.Sysinfo;

import java.util.List;

public interface  SysInfoService {

    //添加子系统到当前系统下
    int addSonSystem(Sysinfo sysinfo);

    /**
     * 根据系统编号查询系统的详细信息。
     * @param sysNo
     * @return
     */
    Sysinfo selectSysInfoBySysNo(String sysNo);

    /**
     * 根据系统id更新系统信息
     * @param sysinfo
     * @return
     */
    int updataSysinfoByPrimaryKey(Sysinfo sysinfo);

    List<Sysinfo> selectSonSystem(String sysNo);
}
