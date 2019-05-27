package com.zxb.ownmalladmin.medecryption;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 加密操作类
 */
public class EncryptionOperationUtils {

    private static Logger logger = LoggerFactory.getLogger(EncryptionOperationUtils.class);
    /**
     * 对指定数据进行加密
     * @param params
     * @return
     */
    public static JSONObject encrypt(String params) {
        try {
            //1.获得AES密匙 aeskey
            String aeskey = AesEncryptUtils.getInstance().generateAESpass();
            //加载公钥
            String RASPublicKeyStr = RSAEncryptUtils.loadPublicKeyStr();
            //2.使用公钥加密aeskey
            String aesKeyEncrypt = RSAEncryptUtils.encrypt(aeskey,RASPublicKeyStr);
            //3.生成加密向量
            String aesIv = AesEncryptUtils.getInstance().generateAESpass();
            //4.对加密向量加密
            String aesIvEncrypt = RSAEncryptUtils.encrypt(aesIv,RASPublicKeyStr);
            //5.通过AES对指定内容加密
            String contentEncrypt = AesEncryptUtils.getInstance().encryptContent(params,aeskey,aesIv);
            //6.设置返回参数返回。
            JSONObject resultObj = new JSONObject();
            resultObj.put("aeskey",aesKeyEncrypt);
            resultObj.put("aesIv",aesIvEncrypt);
            resultObj.put("result",contentEncrypt);

            return resultObj;
        }catch (Exception e){
            logger.debug("加密失败");
            logger.debug(e.getMessage(),e);
        }finally {

        }
        return null;
    }

    /**
     * 解密操作
     * @param content
     * @param aesKey
     * @param aesIv
     * @return
     */
    public static  JSONObject descrypt(String content,String aesKey,String aesIv){
        try {
            //1.加载私钥
            String RSAPrivateKeyStr = RSAEncryptUtils.loadPrivateKeyStr();
            //通过私钥解密aeskey
            aesKey = RSAEncryptUtils.decypt(aesKey,RSAPrivateKeyStr);
            //通过私钥解密aesIv
            aesIv = RSAEncryptUtils.decypt(aesIv,RSAPrivateKeyStr);
            //通过aes解密content;
            String contentStr = AesEncryptUtils.getInstance().decryptContent(content, aesKey, aesIv);
            //将结果转为JSON
            JSONObject resultObj = JSONObject.parseObject(contentStr);
            return resultObj;
        }catch (Exception e){
            logger.debug("解密失败");
            logger.debug(e.getMessage(),e);
        }
        return null;
    }


}
