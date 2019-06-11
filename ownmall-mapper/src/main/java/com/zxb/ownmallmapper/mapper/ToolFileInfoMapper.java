package com.zxb.ownmallmapper.mapper;

import com.zxb.ownmallmapper.pojo.ToolFileChunk;
import com.zxb.ownmallmapper.pojo.ToolFileInfo;

import java.util.List;

public interface ToolFileInfoMapper {

    int deleteByPrimaryKey(Integer id);

    List<ToolFileChunk> selectAll();

    ToolFileChunk selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(ToolFileInfo toolFileInfo);

    int insert(ToolFileInfo toolFileInfo);
}
