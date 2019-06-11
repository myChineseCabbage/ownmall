package com.zxb.applying.pojo;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * 文件块
 */
public class Chunk implements Serializable {

    //文件id
    private  Integer id;

    private  String filename;
    //当前文件块 从1开始
    private  Integer chunkNumber;
    //块总大小
    private  Integer chunkSize;

    //当前分块大小
    private Integer currentChunkSize;

    //总大小
    private Integer totalSize;

    //文件标识
    private String identifier;

    //相对路径
    private String relativePath;

    //总块数
    private  Integer totalChunks;

    //文件类型
    private  String type;

    //
    private MultipartFile file;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getChunkNumber() {
        return chunkNumber;
    }

    public void setChunkNumber(Integer chunkNumber) {
        this.chunkNumber = chunkNumber;
    }

    public Integer getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(Integer chunkSize) {
        this.chunkSize = chunkSize;
    }

    public Integer getCurrentChunkSize() {
        return currentChunkSize;
    }

    public void setCurrentChunkSize(Integer currentChunkSize) {
        this.currentChunkSize = currentChunkSize;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public Integer getTotalChunks() {
        return totalChunks;
    }

    public void setTotalChunks(Integer totalChunks) {
        this.totalChunks = totalChunks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Chunk{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", chunkNumber=" + chunkNumber +
                ", chunkSize=" + chunkSize +
                ", currentChunkSize=" + currentChunkSize +
                ", totalSize=" + totalSize +
                ", identifier='" + identifier + '\'' +
                ", relativePath='" + relativePath + '\'' +
                ", totalChunks=" + totalChunks +
                ", type='" + type + '\'' +
                ", file=" + file +
                '}';
    }
}
