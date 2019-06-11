package com.zxb.ownmallmapper.mapper;

import com.zxb.ownmallmapper.pojo.ToolFileChunk;

import java.util.List;

public interface ToolFileChunkMapper {

    int deleteByPrimaryKey(Integer id);

    List<ToolFileChunk> selectAll();

    ToolFileChunk selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(ToolFileChunk toolFileChunk);

    int insert(ToolFileChunk toolFileChunk);

    List<ToolFileChunk> selectAllChunk(ToolFileChunk toolFileChunk);
}
