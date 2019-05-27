package com.zxb.ownmalladmin.medecryption;

import com.alibaba.fastjson.JSONObject;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 * 前后端数据传输加密工具类
 */
public class AesEncryptUtils {
    private static Logger logger = LoggerFactory.getLogger(AesEncryptUtils.class);

    //可配置到constant中，并读取配置文件注入，16位，也可以在前后端传输的过程中添加上，即好比自己定义
    private static  final  String key  = "1234567890123456";

    //参数分别代表 算法名称/加密模式/数据填充方式
    //CBC加密模式需要传加密向量，ECB模式不需要 其余模式还有CFB,OFB
    //数据填充方式： PKCS5 PKCS7 NOPADDING
    private  static  final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    private  static  AesEncryptUtils aesEncryptUtils = null;

    /**
     * 将类弄成单例模式
     */

    public static  AesEncryptUtils getInstance(){
        if(null == aesEncryptUtils){
            aesEncryptUtils = new AesEncryptUtils();
        }
        return aesEncryptUtils;
    }

    /**
     * AES加密
     * @param content
     * @param encryptkey
     * @param ivparamer
     * @return
     * @throws Exception
     */
    public  String encryptContent(String content,String encryptkey,String ivparamer) throws  Exception{
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE,new SecretKeySpec(encryptkey.getBytes(),"AES"));
        byte[] b = cipher.doFinal(content.getBytes("utf-8"));
        //采用base64算法进行转码，避免出现中文乱码
        return Base64.encodeBase64String(b);
    }

    /**
     * AES解密
     * @param encryptStr 加了密的内容
     * @param decryptKey 加密的aeskey
     * @param ivparamer 加密的向量
     * @return
     * @throws Exception
     */
    public  String decryptContent(String encryptStr,String decryptKey,String ivparamer) throws Exception{
        logger.debug(decryptKey);
        logger.debug(ivparamer);
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE,new SecretKeySpec(decryptKey.getBytes(),"AES"));
        //采用base64算法进行转码，避免出现中文乱码
        byte[] encryptBytes = Base64.decodeBase64(encryptStr);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        logger.debug(new String(decryptBytes));
        return new String(decryptBytes);
    }

    /**
     * 生成一个scretKeySpec 用于得到AES密匙
     * @return
     * @throws NoSuchAlgorithmException
     */
    private SecretKeySpec generateKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128,new SecureRandom());
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] enCodeFormt = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormt,"AES");
            return key;
        }catch (NoSuchAlgorithmException  e){
            logger.debug(e.getMessage(),e);
        }
        return null;
    }
    /**
     *  生成一个AES 字符串
     * @return
     */
    public  String  generatekeyString(){
        return byte2hex(generateKey().getEncoded());
    }

    /**
     * 生成一个AES钥匙
     * @return
     */
    public  String generateAESpass(){
        String str = "";
        try {
            String aessKey = generatekeyString();
            str = aessKey.substring(0,16);
            logger.debug(aessKey+":"+str);
        }catch (Exception e){
            logger.debug(e.getMessage(),e);
        }
       return str;
    }

    /**
     * 将字节数组转为16进制
     * @param b
     * @return
     */
    public String byte2hex(byte[] b){
        StringBuilder sb = new StringBuilder(b.length*2);
        String tmp = "";
        for (int n = 0;n<b.length;n++){
            //整数转成16进制表示，
            tmp = (Integer.toHexString(b[n] & 0XFF));
            if(tmp.length()==1){
                sb.append(0);
            }
            sb.append(tmp);
        }
        return  sb.toString().toUpperCase();//转成大小
    }


    public  static void main(String[] args) throws  Exception{
//        Map map=new HashMap();
//        map.put("key","value");
//        map.put("中文","汉字");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("adminName","adminOne");
        jsonObject.put("password","12345");
        String content = JSONObject.toJSONString(jsonObject);
        logger.debug("加密前：" + content);
        String aessKey = AesEncryptUtils.getInstance().generateAESpass();
        logger.debug(aessKey);
        String encrypt = AesEncryptUtils.getInstance().encryptContent(content,aessKey,aessKey);
        logger.debug("加密后：" + encrypt);

        String decrypt = AesEncryptUtils.getInstance().decryptContent(encrypt, aessKey,aessKey);
        logger.debug("解密后：" + decrypt);

    }




}
