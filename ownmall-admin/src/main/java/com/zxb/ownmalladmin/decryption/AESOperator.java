package com.zxb.ownmalladmin.decryption;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * AES 是一种可逆加密算法，对用户的敏感信息加密处理 对原始数据进行AES加密后，在进行Base64编码转化；
 */
public class AESOperator{
    /*
     * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
     * //使用CBC模式，需要一个向量iv，可增加加密算法的强度(与移动端该字段保持一致)
     */
    private String ivParameter;
    private static AESOperator instance = null;

    public static AESOperator getInstance(){
        if (instance == null)
            instance = new AESOperator();
        return instance;
    }
    /**
     * 功能描述：AES加密
     * @param content 明文
     * @param key     密钥
     * @return String
     * @throws Exception 
     * @author LiuBo
     * @date 2017年4月17日 上午10:27:43
     * @修改日志：
     */
    public String encrypt(String content,String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = key.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(content.getBytes("utf-8"));
        return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。
    }

    /**
     * 功能描述：解密
     * @param content 密文
     * @param key     密钥
     * @return String
     * @throws Exception 
     * @author LiuBo
     * @date 2017年4月17日 上午10:27:13
     * @修改日志：
     */
    public String decrypt(String content,String key) throws Exception {
        try {
            byte[] raw = key.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(content);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }
    /**
     * 生成一个AES密钥字符串
     * @return
     */
    public static String generateKeyString(){
    	return byte2hex(generateKey().getEncoded());
    }
    /**
     * 生成一个AES密钥对象
     * @return
     */
    private static SecretKeySpec generateKey(){
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
	        kgen.init(128, new SecureRandom());  
	        SecretKey secretKey = kgen.generateKey();  
	        byte[] enCodeFormat = secretKey.getEncoded();  
	        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			return key;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
    }
    /**
     * 字节数组转成16进制字符串
     * @param b
     * @return
     */
    private static String byte2hex(byte[] b){// 一个字节的数，
        StringBuffer sb = new StringBuffer(b.length * 2);
        String tmp = "";
        for (int n = 0; n < b.length; n++) {
            // 整数转成十六进制表示
            tmp = (Integer.toHexString(b[n] & 0XFF));
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
        }
        return sb.toString().toUpperCase(); // 转成大写
    }
    /**
     * 功能描述：设置加密向量
     * @param ivParameter 
     * @author LiuBo
     * @date 2017-4-29 上午10:29:46
     * @修改日志：
     */
	public void setIvParameter(String ivParameter){
		this.ivParameter = ivParameter;
	}
}