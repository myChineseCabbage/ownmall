package com.zxb.applying.Service;

import com.zxb.applying.pojo.Chunk;
import com.zxb.ownmallmapper.pojo.ToolFileChunk;

/**
 * 文件块相关的业务操作
 */
public interface ChunkService {


    /**
     * 保存块
     * @param chunk
     */
    public void  saveChunk(ToolFileChunk chunk);


    /**
     * 检查块
     * @param identifier
     * @param chunkNumber
     * @return
     */
    public boolean checkChunk(String identifier,Integer chunkNumber);
}
