package com.zxb.ownmallmapper.mapper;

import com.zxb.ownmallmapper.pojo.Sysinfo;

import java.util.List;

public interface SysinfoExtendsMapper extends SysinfoMapper {
    int insertSysInfo(Sysinfo sysinfo);
    Sysinfo selectSysInfoBySysNo(String sysNo);
    List<String> selectAllSysNo();
}
