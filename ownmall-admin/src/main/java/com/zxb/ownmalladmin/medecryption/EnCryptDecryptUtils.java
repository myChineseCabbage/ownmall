package com.zxb.ownmalladmin.medecryption;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * 后端对数据加密解密操作
 */
public class EnCryptDecryptUtils {
    private static Logger logger = LoggerFactory.getLogger(EncryptionOperationUtils.class);

    public static  void main(String[] args) throws  Exception{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("adminName","adminOne");
        jsonObject.put("password","12345");
        JSONObject result = encrypt(jsonObject);
        logger.debug("加密：");
        logger.debug(result.toJSONString());
        logger.debug("解密后");
        JSONObject reultDecry = descrypt(result);
        logger.debug(reultDecry.toJSONString());

    }

    /**
     * 加密操作
     * @param jsonObject
     * @return
     */
    public static JSONObject encrypt(JSONObject jsonObject){
        //md5加密数据用于验签
        String md5Param = Md5Utils.ecodeByMd5(jsonObject.toString());
        //aes加密数据
        JSONObject resultObj = EncryptionOperationUtils.encrypt(jsonObject.toString());
        if(null != md5Param & null !=resultObj){
            logger.debug("加密成功");
            resultObj.put("md5Param",md5Param);
            return resultObj;
        }
        return null;
    }

    /**
     * 解密操作 解密时要验签操作
     * @param jsonObject
     * @return
     */
    public static JSONObject descrypt(JSONObject jsonObject){
        JSONObject jsonResultObj = new JSONObject();
        logger.debug(jsonObject.toJSONString());
        //获取加密了AES密钥
        String aesKey = jsonObject.getString("aeskey");
        logger.debug(aesKey);
        //获取加密了的加密向量
        String aesIv = jsonObject.getString("aesIv");
        logger.debug(aesIv);
        //获取到加密了的加密内容
        String content = jsonObject.getString("result");
        logger.debug(content);
        //获取到 验签标志
        String md5Params = jsonObject.getString("md5Param");
        logger.debug(md5Params);
        //解密
        JSONObject resultObj = EncryptionOperationUtils.descrypt(content,aesKey,aesIv);
        //验签
        String md5ParamsResult = Md5Utils.ecodeByMd5(resultObj.toString());
        if (md5Params.equals(md5ParamsResult)){
            logger.debug("验签成功，解密成功");
            jsonResultObj = resultObj;
        }else {
            logger.debug("验签失败");
            jsonResultObj.put("RetCode","DECRYP001");
            jsonResultObj.put("RetMsg","验签失败");
        }
        return  jsonResultObj;
    }

    /**
     * 解密操作 解密时要验签操作
     * @param inputStream
     * @return
     */
    public static JSONObject descrypt(InputStream inputStream){
        JSONObject jsonResultObj = new JSONObject();
        try {
            logger.debug("读取请求的参数指：");
           String requestParam = IOUtils.toString(inputStream,"UTF-8");
           logger.debug(requestParam);
           JSONObject jsonObject = JSONObject.parseObject(requestParam);
            //获取加密了AES密钥
            String aesKey = jsonObject.getString("aeskey");
            //获取加密了的加密向量
            String aesIv = jsonObject.getString("aesIv");
            //获取到加密了的加密内容
            String content = jsonObject.getString("result");
            //获取到 验签标志
            String md5Params = jsonObject.getString("md5Param");
            //解密
            JSONObject resultObj = EncryptionOperationUtils.descrypt(content,aesKey,aesIv);
            //验签
            String md5ParamsResult = Md5Utils.ecodeByMd5(resultObj.toString());
            if (md5Params.equals(md5ParamsResult)){
                logger.debug("验签成功，解密成功");
                jsonResultObj = resultObj;
            }else {
                logger.debug("验签失败");
                jsonResultObj.put("RetCode","DECRYP001");
                jsonResultObj.put("RetMsg","验签失败");
            }
        }catch (IOException e){
            logger.debug(e.getMessage(),e);
        }
        return  jsonResultObj;
    }
}
