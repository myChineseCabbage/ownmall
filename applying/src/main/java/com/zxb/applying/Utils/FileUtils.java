package com.zxb.applying.Utils;

import com.zxb.ownmallmapper.pojo.ToolFileChunk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 文件工具类
 */
public class FileUtils {

    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 创建块存储路径
     * @param uploaderFolder
     * @param chunk
     * @return
     */
    public static  String generatePath(String uploaderFolder, ToolFileChunk chunk){

        StringBuilder sb = new StringBuilder();
        sb.append(uploaderFolder).append("/").append(chunk.getIdentifier());
        //判断uploaderFolder/identifier,是否存在，不存在创建
        if(!Files.isWritable(Paths.get(sb.toString()))){
            logger.debug("path not exist,create path:{}",sb.toString());
            try {
                Files.createDirectories(Paths.get(sb.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }
        }
        return  sb.append("/").append(chunk.getFilename()).append("-").append(chunk.getChunknumber()).toString();
    }

    /**
     * 合并文件
     * @param targetFile
     * @param folder
     * @param fileName
     */
    public  static void  merge(String targetFile,String folder,String fileName){
        try {
            Files.createFile(Paths.get(targetFile));
            Files.list(Paths.get(folder))
                    .filter(path -> !path.getFileName().toString().equals(fileName))
                    .sorted((o1,o2)->{
                        String p1 = o1.getFileName().toString();
                        String p2 = o2.getFileName().toString();
                        int i1 = p1.lastIndexOf("-");
                        int i2 = p2.lastIndexOf("-");
                        return  Integer.valueOf(p2.substring(i2)).compareTo(Integer.valueOf(p1.substring((i1))));
                    })
                    .forEach(path -> {
                        try {
                            Files.write(Paths.get(targetFile),Files.readAllBytes(path), StandardOpenOption.APPEND);
                            //合并后删除该块
                            Files.delete(path);
                        } catch (IOException e) {
                            logger.error(e.getMessage(),e);
                        }

                    });
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        } finally {
        }
    }
}
