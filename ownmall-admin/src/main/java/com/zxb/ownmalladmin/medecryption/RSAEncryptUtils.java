package com.zxb.ownmalladmin.medecryption;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;


public class RSAEncryptUtils {

    private static Logger logger = LoggerFactory.getLogger(RSAEncryptUtils.class);
    /** *//**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /** *//**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    private  static Map<Integer,String> keyMap = new HashMap<Integer,String>();
    public static  void  main(String[] args) throws Exception{
//        //生成私钥和公钥
//        genKeyPair();
//
//        //需要解密的字符串
//        String str = "我是你爸爸";
//        logger.debug("加密前:"+str);
//        //加密
//        str = encrypt(str,keyMap.get(0));
//        logger.debug("加密后："+str);
//        //解密
//        str = decypt(str,keyMap.get(1));
//        logger.debug("解密后："+str);

        String str = "QWERTYUIOPASDFGH";
        String publicKeyStr = loadPublicKeyStr();
        String privateKeyStr = loadPrivateKeyStr();
        str = encrypt(str,publicKeyStr);
        logger.debug("加密后："+str);
        str = decypt(str,privateKeyStr);
        logger.debug("解密后："+str);
    }

    /**
     * 随机生成公钥和私钥字符串
     * @throws NoSuchAlgorithmException
     */
    public static  void genKeyPair() throws NoSuchAlgorithmException{
        //KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        //初始化密钥对生成器，密钥大小为96-1024位
        keyPairGenerator.initialize(1024,new SecureRandom());
        //生成一个密钥对，保存在KeyPair中
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64String(privateKey.getEncoded()));
        //得到公钥字符串
        String publicKeyString = new String(Base64.encodeBase64String(publicKey.getEncoded()));
        logger.debug("私钥："+privateKeyString);
        logger.debug("公钥："+publicKeyString);
        keyMap.put(0,publicKeyString);
        keyMap.put(1,privateKeyString);
    }

    /**
     * RSA公钥加密
     * @param str 加密字符串
     * @param publickeyStr 公钥
     * @return 密文
     * @throws Exception
     */
    public static String encrypt(String str,String publickeyStr) throws Exception {

        //Base64编码的公钥
        byte[] decode = Base64.decodeBase64(publickeyStr);
        RSAPublicKey publicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decode));
        //RSA 加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        String resultStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return  resultStr;

    }

    /**
     * 使用公钥分组加密
     * @param dataStr
     * @return
     */
    public static String encryptByPublickeyStr(String  dataStr,String publickeyStr) throws Exception{
        logger.debug("要加密的数据",dataStr);
        byte[] data = dataStr.getBytes("utf-8");
        byte[] decode = Base64.decodeBase64(publickeyStr);
        RSAPublicKey publicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decode));
        //模长
        int key_len = publicKey.getModulus().bitLength()/8;
        //加密数据长度
        String datas[] = splitString(dataStr,key_len-11);
        String mi="";
        for (int i=0;i<datas.length;i++){
            //通过此处bcd2str转码，使得每组长度一致
            mi+=bcd2Str(encryptByPublicKey(datas[i].getBytes(),publicKey));
        }
        return  mi;
    }

    /**
     * 加密
     * @param data
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data,RSAPublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }
    /**
     * BCD转字符串
     */
    private static String bcd2Str(byte[] bytes) {
        char temp[] = new char[bytes.length * 2], val;

        for (int i = 0; i < bytes.length; i++) {
            val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
            temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');

            val = (char) (bytes[i] & 0x0f);
            temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
        }
        return new String(temp);
    }
    /**
     * 对字符串分组
     * @param str
     * @param length
     * @return
     */
    public static String[] splitString(String str,int length){
        int x = str.length()/length;
        int y = str.length()%length;
        int z = 0;
        if(y!=0){
            z=1;
        }
        String[] strings = new String[x+z];
        String mestr="";
        for (int i =0;i<strings.length;i++){
            if(i==x+z-1 && y!=0){
                mestr = str.substring(i*length,i*length+y);
            }else{
                mestr = str.substring(i*length,i*length+length);
            }
            strings[i]=mestr;
        }
        return strings;
    }
    /**
     * 使用私钥解密
     * @param str
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    public static  String decypt(String str,String privateKeyStr) throws Exception{
        //Base64解码加密过后的字符串，
        byte[] inputBytes = Base64.decodeBase64(str.getBytes("utf-8"));
       //Base64编码的二四幺
        byte[] decoded = Base64.decodeBase64(privateKeyStr.getBytes());
        RSAPrivateKey privateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        String resultStr = new String(cipher.doFinal(inputBytes));
        return  resultStr;
    }


    /**
     * 加载公钥
     * @return
     */
    public   static String loadPublicKeyStr() throws  Exception{
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            String publicKetPath = RSAConstant.RSA_PUBLICKEY_PATH;
            inputStream = RSAEncryptUtils.class.getClassLoader().getResourceAsStream(publicKetPath);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String readLine = null;
            StringBuffer stringBuffer = new StringBuffer();
            while ((readLine = bufferedReader.readLine()) !=null){
                if(readLine.charAt(0)=='-'){
                    continue;
                }else {
                    stringBuffer.append(readLine);

                }
            }
            return  stringBuffer.toString();
        }catch (IOException e){
            throw  new Exception("公钥数据流读取错误");
        }catch (NullPointerException e1){
            throw  new Exception("公钥输入流为空");
        }catch (Exception e){
            throw  new Exception("其他异常");
        }finally {
            try {
                if(bufferedReader!=null){
                    bufferedReader.close();
                }
                if(inputStream!=null){
                    inputStream.close();
                }
            }catch (IOException e){
                logger.debug(e.getMessage(),e);
            }
        }
    }

    /**
     * 加载私钥
     * @return
     */
    public static String loadPrivateKeyStr() throws  Exception{
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            String publicKetPath = RSAConstant.PRIVATE_PATTH;
            inputStream = RSAEncryptUtils.class.getClassLoader().getResourceAsStream(publicKetPath);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String readLine = null;
            StringBuffer stringBuffer = new StringBuffer();
            while ((readLine = bufferedReader.readLine()) !=null){
                if(readLine.charAt(0)=='-'){
                    continue;
                }else {
                    stringBuffer.append(readLine);

                }
            }
            return  stringBuffer.toString();
        }catch (IOException e){
            throw  new Exception("公钥数据流读取错误");
        }catch (NullPointerException e1){
            throw  new Exception("公钥输入流为空");
        }catch (Exception e){
            throw  new Exception("其他异常");
        }finally {
            try {
                if(bufferedReader!=null){
                    bufferedReader.close();
                }
                if(inputStream!=null){
                    inputStream.close();
                }
            }catch (IOException e){
                logger.debug(e.getMessage(),e);
            }
        }
    }
}
