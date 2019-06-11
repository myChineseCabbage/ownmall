package com.zxb.applying.Service.impl;

import com.zxb.applying.Service.ChunkService;
import com.zxb.applying.pojo.Chunk;
import com.zxb.ownmallmapper.mapper.ToolFileChunkMapper;
import com.zxb.ownmallmapper.pojo.ToolFileChunk;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChunkServiceImpl implements ChunkService {

    @Resource
    private ToolFileChunkMapper toolFileChunkMapper;

    /**
     * 保存块信息
     * @param chunk
     */
    @Override
    public void saveChunk(ToolFileChunk  chunk) {
        //调用保存块信息
        toolFileChunkMapper.insert(chunk);
    }

    /**
     * 检查块信息 checkChunk()方法会根据文件唯一标识，和当前块数判断是否已经上传过这个块。
     * @param identifier
     * @param chunkNumber
     * @return
     */
    @Override
    public boolean checkChunk(String identifier, Integer chunkNumber) {
        //根据文件文件唯一标识查询是否有该块，有返回false,没有返回true
        ToolFileChunk toolFileChunk = new ToolFileChunk();
        toolFileChunk.setChunknumber(chunkNumber);
        toolFileChunk.setIdentifier(identifier);
        List<ToolFileChunk> toolFileChunkList = toolFileChunkMapper.selectAllChunk(toolFileChunk);
        return toolFileChunkList==null;
    }
}
