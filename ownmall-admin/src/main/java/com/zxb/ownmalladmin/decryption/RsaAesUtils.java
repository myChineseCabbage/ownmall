package com.zxb.ownmalladmin.decryption;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import com.alibaba.fastjson.JSONObject;

import com.zxb.ownmalladmin.medecryption.RSAEncryptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 功能描述：解密工具类
 * @author mafuying
 * @date 2018-9-23 上午10:17:09
 * @修改日志：
 */
public class RsaAesUtils {
	private static Logger log4j = LoggerFactory.getLogger(RsaAesUtils.class);
	
	public String CqrcbTest(String jsonstr) {
		log4j.debug("测试");
		return "{\"2\":\"efg\",\"1\":\"abc\"}";
	}
	
	/**
	 * 解密前端上送的加密参数
	 * @param
	 * @return
	 * @throws Exception
	 */
	public static String decryptContent(String content,String aesKey,String aesIv) throws Exception{
		RSAPrivateKey privateKey = RSAOperator.loadPrivateKey();
    	aesKey = RSAOperator.decryptByPrivateKey(aesKey, privateKey);
    	aesIv = RSAOperator.decryptByPrivateKey(aesIv, privateKey);
//    	log4j.debug("解密后aesKey：" + aesKey);
//		log4j.debug("解密后aesIv：" + aesIv);
    	AESOperator.getInstance().setIvParameter(aesIv);//保存向量值 解密时使用
    	String methodParams = AESOperator.getInstance().decrypt(content, aesKey);
    	return methodParams;
	}
	
	/**
     * 功能描述：使用AES对上送内容进行加密
     * @param content
     * @param key
     * @return String
     * @author LiuBo
     * @date 2017年7月5日 下午4:32:49
     * @修改日志：
     */
	private static String generateAesCode(String content,String key){
        String result = "";
        try {
            //再使用AES加密内容，传给服务器
            String encryptContent = AESOperator.getInstance().encrypt(content,key);
            result = encryptContent;
        }catch(Exception e){
          	
        }
        return result;
    }
    /**
     * 功能描述：生成RS私钥加密的AES秘钥
     * @param
     * @return String
     * @author LiuBo
     * @date 2017年4月15日 下午5:08:09
     * @修改日志：
     */
	@SuppressWarnings("unused")
	private static String generateRsaAes(String key){
        String result = "";
        try{
            //加载RSA公钥
            RSAPrivateKey rsaPrivateKey = RSAOperator.loadPrivateKey();
            //用RSA公钥加密AES的密钥
            String encryptAesKey = RSAOperator.encryptByPrivateKey(key, rsaPrivateKey);
            result = encryptAesKey;
        }catch(Exception e){
        	
        }
        return result;
    }
	
	/**
	 * 功能描述：使用公钥加密，获取aesKey
	 * @param key
	 * @return String
	 * @author mafuying
	 * @date 2018-9-23 下午9:59:11
	 * @修改日志：
	 */
	public static String generateRsaAesByPublicKey(String key){
		String result = "";
		try{
			//加载RSA公钥
//			RSAPublicKey rsaPublicKey = RSAOperator.loadPublicKey();
			String  rsaPublicKeyStr = RSAEncryptUtils.loadPublicKeyStr();
			RSAPublicKey rSAPublicKey = RSAOperator.loadPublicKey(rsaPublicKeyStr);
			//用RSA公钥加密AES的密钥
			String encryptAesKey = RSAOperator.encryptByPublicKey(key, rSAPublicKey);
			result = encryptAesKey;
		}catch(Exception e){
			log4j.debug(e.getMessage(),e);
		}
		return result;
	}

	public static  void  main(String[] args) throws  Exception{
		log4j.debug("加密");
		String str= "1234567890101112";
		String result = generateRsaAesByPublicKey(str);
		log4j.debug(result);
	}
    /**
     * 功能描述： 生成AES秘钥
     * @param
     * @return String
     * @author LiuBo
     * @date 2017年4月15日 下午5:06:30
     * @修改日志：
     */
	private static String generateAesPass(){
        String result = "";
        try{
            //生成一个AES密钥
            String aesKey = AESOperator.generateKeyString();
            result = aesKey.substring(0, 16);
        }catch(Exception e){
        }
        return result;
    }
    /**
	 * 功能描述：前端进行数据传输前，先将数据进行AES对称加密，返回加密后的数据及加密后的AES密钥
	 * @param params
	 * @return String
	 * @author LiuBo
	 * @date 2017年4月28日 下午9:42:13
	 * @修改日志：
	 */
    public static String encryptContent(String params){
		try{
			//1.生成AES密钥
			String aesKey = generateAesPass();
//			log4j.debug("加密前aesKey：" + aesKey);
			//2.用RSA公钥将aes密钥进行加密
			String encryptAesKey = generateRsaAesByPublicKey(aesKey);
			//3.生成AES加密向量并保存
			String ivParameter =  generateAesPass();
//			log4j.debug("加密前aesIv：" + ivParameter);
			AESOperator.getInstance().setIvParameter(ivParameter);
			//4.生成加密向量的密文
			String encryptIv =  generateRsaAesByPublicKey(ivParameter);
			//5.用AES密钥将原文加密
			String encryptContent =  generateAesCode(params, aesKey);
			//RSA加密后密钥、RSA加密后向量、加密内容
			JSONObject resultJSON = new JSONObject();
			resultJSON.put("aesKey", encryptAesKey);
			resultJSON.put("aesIv", encryptIv);
			resultJSON.put("result", encryptContent);
			return resultJSON.toString();
		}catch(Exception e){
			
		}
    	return params;
    }


}