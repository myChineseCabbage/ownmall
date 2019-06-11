package com.zxb.applying.Service.impl;

import com.zxb.applying.Service.FileInfoService;
import com.zxb.ownmallmapper.mapper.ToolFileInfoMapper;
import com.zxb.ownmallmapper.pojo.ToolFileInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FileInfoServiceImpl implements FileInfoService {

    @Resource
    private ToolFileInfoMapper toolFileInfoMapper;

    /**
     * 保存文件信息
     * @param toolFileInfo
     * @return
     */
    @Override
    public int saveFileInfo(ToolFileInfo toolFileInfo) {
        int i = toolFileInfoMapper.insert(toolFileInfo);
        return 1/i;
    }
}
