package com.zxb.applying.controller;

import com.alibaba.fastjson.JSONObject;
import com.zxb.applying.Service.ChunkService;
import com.zxb.applying.Service.FileInfoService;
import com.zxb.applying.Utils.FileUtils;
import com.zxb.applying.pojo.Chunk;
import com.zxb.applying.pojo.FileInfo;
import com.zxb.ownmallmapper.pojo.ToolFileChunk;
import com.zxb.ownmallmapper.pojo.ToolFileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/uploader")
public class UploadController {

    private Logger logger = LoggerFactory.getLogger(UploadController.class);

    private  String uploadFolder = "C:\\MyTreasures\\MyDevelope\\JavaDevlope\\workspace\\uploader";

    @Autowired
    private FileInfoService fileInfoServiceImpl;

    @Autowired
    private ChunkService chunkServiceImpl;

    /**
     * 上传块
     * @param chunk
     * @return
     */
    @PostMapping("/chunk")
    public String uploadChunk(Chunk chunk){
        logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.debug(chunk.toString());
        MultipartFile file  = chunk.getFile();
        logger.debug("file.originName{},chunkNumber{}",file.getOriginalFilename(),chunk.getChunkNumber());
        //需要将chunk转为  ToolFileChunk
        ToolFileChunk toolFileChunk = new ToolFileChunk();
        toolFileChunk.setId(chunk.getId());
        toolFileChunk.setIdentifier(chunk.getIdentifier());
        toolFileChunk.setChunknumber(chunk.getChunkNumber());
        toolFileChunk.setChunksize(chunk.getChunkSize());
        toolFileChunk.setCurrentchunksize(chunk.getCurrentChunkSize());
        toolFileChunk.setFilename(chunk.getFilename());
        toolFileChunk.setRelativepath(chunk.getRelativePath());
        toolFileChunk.setTotalchunks(chunk.getTotalChunks());
        toolFileChunk.setTotalsize(chunk.getTotalSize());
        toolFileChunk.setType(chunk.getType());
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(FileUtils.generatePath(uploadFolder,toolFileChunk));
            //文件写入指定路径
            Files.write(path,bytes);
            logger.debug("文件{}写入成功，uuid{}",chunk.getFilename(),chunk.getIdentifier());
            chunkServiceImpl.saveChunk(toolFileChunk);
            return "文件上传成功";

        } catch (IOException e) {
            logger.debug(e.getMessage(),e);
            return "后端异常";
        } finally {

        }
    }

    @GetMapping("/chunk")
    public  Object checkChunk(Chunk chunk, HttpServletResponse response){
        logger.debug(chunk.toString());
        logger.debug("get chunk ===========================");
        if(!chunkServiceImpl.checkChunk(chunk.getIdentifier(),chunk.getChunkNumber())){
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED); //304状态码，标识该请求内容与上次并没有改变
        }
        return  chunk;
    }


    @PostMapping("/mergeFile")
    public  String mergeFile(@RequestBody  ToolFileInfo fileInfo){
        JSONObject resultObj = new JSONObject();
        try {
            logger.debug(fileInfo.toString());
            logger.debug("合并所上传文件");
            String path = uploadFolder+"/"+fileInfo.getIdentifier()+"/"+fileInfo.getFilename();
            String folder = uploadFolder + "/" +fileInfo.getIdentifier();
            FileUtils.merge(path,folder,fileInfo.getFilename());
            fileInfo.setLocation(path);
            fileInfoServiceImpl.saveFileInfo(fileInfo);

            resultObj.put("RetCode","AAAAAAAA");
            resultObj.put("RetMsg","合并成功");
            return  resultObj.toString();
        }catch (Exception e){
            logger.debug(e.getMessage(),e);
            resultObj.put("RetCode","mergeFileERRO");
            resultObj.put("RetMsg","合并失败");
            return  resultObj.toString();

        }

    }

}
