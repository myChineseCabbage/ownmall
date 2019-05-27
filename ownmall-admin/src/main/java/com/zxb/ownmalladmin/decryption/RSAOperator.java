package com.zxb.ownmalladmin.decryption;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


import com.zxb.ownmalladmin.medecryption.RSAConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

/**
 * 功能描述：CA证书验签工具类
 * @author yangkai
 * @date 2016-10-25 上午11:28:33
 * @修改日志：
 */
public class RSAOperator{
	private static Logger log4j = LoggerFactory.getLogger(RsaAesUtils.class);
	
	/** 算法/模式/填充 **/
    private static final String CipherMode = "AES/ECB/PKCS5Padding";
	private static final String ALGORITHM = "RSA";
	private static final String TRANSFORMATION = "RSA";
	
	public String CqrcbTest(String jsonstr) {
		log4j.debug("测试");
		return "{\"2\":\"efg\",\"1\":\"abc\"}";
	}
	
	/**
	 * 从文件中加载私钥
	 * @param
	 *
	 * @return
	 * @throws Exception
	 */
	public static RSAPrivateKey loadPrivateKey() throws Exception{
		InputStream in = null;
		BufferedReader br = null;
		try{
			String privateKeyPath = RSAConstant.RSA_PRIVATEKEY_PATH;
//			log4j.debug("加载私钥开始：");
//			log4j.debug("私钥路径：" + privateKeyPath);
			in = RSAOperator.class.getResourceAsStream(privateKeyPath);
			br = new BufferedReader(new InputStreamReader(in));
			String readLine = null;
			StringBuilder sb = new StringBuilder();
			while((readLine = br.readLine()) != null) {
				if(readLine.charAt(0) == '-') {
					continue;
				}else{
					sb.append(readLine);
					sb.append('\r');
				}
			}
			return loadPrivateKey(sb.toString());
		}catch(IOException e){
			throw new Exception("私钥数据读取错误");
		}catch(NullPointerException e) {
			throw new Exception("私钥输入流为空");
		}finally{
			try{
			if(in != null){
				in.close();
			}
			if(br != null){
				br.close();
			}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 从文件中加载公钥
	 * @param
	 *
	 * @return
	 * @throws Exception
	 */
	public static RSAPublicKey loadPublicKey() throws Exception{
		InputStream in = null;
		BufferedReader br = null;
		try{
			String publicKeyPath = RSAConstant.RSA_PUBLICKEY_PATH;
			log4j.debug("公钥路径：" + publicKeyPath);
			in = RSAOperator.class.getResourceAsStream(publicKeyPath);
			br = new BufferedReader(new InputStreamReader(in));
			String readLine = null;
			StringBuilder sb = new StringBuilder();
			while((readLine = br.readLine()) != null) {
				if(readLine.charAt(0) == '-') {
					continue;
				}else{
					sb.append(readLine);
					sb.append('\r');
				}
			}
			return loadPublicKey(sb.toString());
		}catch(IOException e){
			throw new Exception("公钥数据读取错误");
		}catch(NullPointerException e) {
			throw new Exception("公钥输入流为空");
		}finally{
			try{
			if(in != null){
				in.close();
			}
			if(br != null){
				br.close();
			}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 从字符串中加载私钥
	 * 
	 * @desc
	 * @param privateKeyStr
	 *            私钥字符串
	 * @return
	 * @throws Exception
	 */
	public static RSAPrivateKey loadPrivateKey(String privateKeyStr)
			throws Exception {
		try {
			BASE64Decoder base64Decoder = new BASE64Decoder();
			byte[] buffer = base64Decoder.decodeBuffer(privateKeyStr);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
			throw new Exception("私钥非法");
		} catch (IOException e) {
			throw new Exception("私钥数据内容读取错误");
		} catch (NullPointerException e) {
			throw new Exception("私钥数据为空");
		}
	}
	
	public static RSAPublicKey loadPublicKey(String publicKeyStr) throws Exception {
		try {
			BASE64Decoder base64Decoder = new BASE64Decoder();
			byte[] buffer = base64Decoder.decodeBuffer(publicKeyStr);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
			throw new Exception("公钥非法");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("公钥数据内容读取错误");
		} catch (NullPointerException e) {
			throw new Exception("公钥数据为空");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	/**
	 * 私钥解密
	 * 
	 * @param data
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static String decryptByPrivateKey(String data,RSAPrivateKey privateKey) throws Exception {
		// 模长
		int key_len = privateKey.getModulus().bitLength() / 8;
		byte[] bytes = data.getBytes();
		byte[] bcd = ASCII_To_BCD(bytes, bytes.length);
		// 如果密文长度大于模长则要分组解密
		String ming = "";
		byte[][] arrays = splitArray(bcd, key_len);
		for (byte[] arr : arrays) {
			ming += new String(decryptByPrivateKey(arr, privateKey));
		}
		return ming;
	}
	
	/**
	 * 私钥解密
	 * @desc 
	 * @param data
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] data,
			RSAPrivateKey privateKey) throws Exception {
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}
	/**
	 * 私钥加密
	 * @desc 
	 * @param data
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static String encryptByPrivateKey(String data, RSAPrivateKey privateKey) throws Exception {
		// 模长
		int key_len = privateKey.getModulus().bitLength() / 8;
		// 加密数据长度 <= 模长-11
		String[] datas = splitString(data, key_len - 11);
		String mi = "";
		// 如果明文长度大于模长-11则要分组加密
		for (String s : datas) {
			mi += bcd2Str(encryptByPrivateKey(s.getBytes(), privateKey));
		}
		return mi;
	}
	/**
	 * 功能描述：公钥加密
	 * @param data
	 * @param
	 * @return
	 * @throws Exception String
	 * @author mafuying
	 * @date 2018-9-23 下午10:05:18
	 * @修改日志：
	 */
	public static String encryptByPublicKey(String data, RSAPublicKey publicKey) throws Exception {
		// 模长
		int key_len = publicKey.getModulus().bitLength() / 8;
		// 加密数据长度 <= 模长-11
		String[] datas = splitString(data, key_len - 11);
		String mi = "";
		// 如果明文长度大于模长-11则要分组加密
		for (String s : datas) {
			mi += bcd2Str(encryptByPublicKey(s.getBytes(), publicKey));
		}
		return mi;
	}
	/**
	 * 私钥加密
	 * @desc 
	 * @param data
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data,RSAPrivateKey privateKey) throws Exception {
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}
	
	public static byte[] encryptByPublicKey(byte[] data,RSAPublicKey publicKey) throws Exception {
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}
	/**
	 * ASCII码转BCD码
	 * 
	 */
	private static byte[] ASCII_To_BCD(byte[] ascii, int asc_len) {
		byte[] bcd = new byte[asc_len / 2];
		int j = 0;
		for (int i = 0; i < (asc_len + 1) / 2; i++) {
			bcd[i] = asc_to_bcd(ascii[j++]);
			bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));
		}
		return bcd;
	}

	/**
	 * 拆分数组
	 */
	private static byte[][] splitArray(byte[] data, int len) {
		int x = data.length / len;
		int y = data.length % len;
		int z = 0;
		if (y != 0) {
			z = 1;
		}
		byte[][] arrays = new byte[x + z][];
		byte[] arr;
		for (int i = 0; i < x + z; i++) {
			arr = new byte[len];
			if (i == x + z - 1 && y != 0) {
				System.arraycopy(data, i * len, arr, 0, y);
			} else {
				System.arraycopy(data, i * len, arr, 0, len);
			}
			arrays[i] = arr;
		}
		return arrays;
	}
	
	private static byte asc_to_bcd(byte asc){
		byte bcd;
		if ((asc >= '0') && (asc <= '9'))
			bcd = (byte) (asc - '0');
		else if ((asc >= 'A') && (asc <= 'F'))
			bcd = (byte) (asc - 'A' + 10);
		else if ((asc >= 'a') && (asc <= 'f'))
			bcd = (byte) (asc - 'a' + 10);
		else
			bcd = (byte) (asc - 48);
		return bcd;
	}
	
    /**
     * 将hex字符串转换成字节数组
     * @param inputString
     * @return
     */
    private static byte[] hex2byte(String inputString){
    	int len = inputString.length();
    	byte[] b = new byte[len/2];
    	for(int i = 0; i < len; i+=2){
    		b[i/2] = (byte)((Character.digit(inputString.charAt(i), 16)<<4)+Character.digit(inputString.charAt(i+1), 16));
    	}
    	
        return b;
    }
    
    /**
     * 通过byte[]类型的密钥解密byte[]
     * @param content
     * @param key
     * @return
     */
    public static byte[] decrypt(byte[] content,byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"));
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
	/**
     * 通过String类型的密钥 解密String类型的密文
     * @param content
     * @param key
     * @return
     */
    public static String decrypt(String content, String key) {
        byte[] data = null;
        try {
            data = hex2byte(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = decrypt(data, hex2byte(key));
        if (data == null)
            return null;
        String result = null;
        try {
            result = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
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
	 * 拆分字符串
	 */
	private static String[] splitString(String string, int len) {
		int x = string.length() / len;
		int y = string.length() % len;
		int z = 0;
		if (y != 0) {
			z = 1;
		}
		String[] strings = new String[x + z];
		String str = "";
		for (int i = 0; i < x + z; i++) {
			if (i == x + z - 1 && y != 0) {
				str = string.substring(i * len, i * len + y);
			} else {
				str = string.substring(i * len, i * len + len);
			}
			strings[i] = str;
		}
		return strings;
	}
}
