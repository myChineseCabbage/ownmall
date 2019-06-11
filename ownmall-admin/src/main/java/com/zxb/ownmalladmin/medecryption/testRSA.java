package com.zxb.ownmalladmin.medecryption;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class testRSA {
    public static void main(String[] args) {
        RSAPublicKey publicKey = null;

        // 待加密的数据
        String data = "123456789";
        try
        {
            String publicKeyStr = RSAUtils.loadKeyByFile(RSAConstant.RSA_PUBLICKEY_PATH2);
            System.out.println(publicKeyStr);
            publicKey = RSAUtils.loadPublicKeyByStr(publicKeyStr);
//                    RSAUtil.loadKeyByFile("/Users/rabbit/publicKey.keystore"));
            System.out.println("公钥========>" + publicKey);
//            String privateKeyStr = RSAUtils.loadKeyByFile(RSAConstant.RSA_PUBLICKEY_PATH2);
//            RSAPrivateKey privateKey = RSAUtils.loadPrivateKeyByStr(privateKeyStr);
////                    RSAUtil.loadKeyByFile("/Users/rabbit/privateKey.keystore"));
//            System.out.println("私钥========>" + publicKey);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        // 加密后的数据串（String型）
        String encryStr = "";
        // 解密后数据串（String型）
        String decryStr = "";
        // 加密后的数据（byte[]型的）
        byte[] encryptBytes;
        // 解密后数据（byte[]型的）
        byte[] decryptBytes;

        /**
         * 对数据进行：公钥加密，私钥解密
         */
        try
        {
            //公钥加密
            encryptBytes = RSAUtils.encrypt(data, publicKey);
            encryStr = new String(encryptBytes);
            System.out.println("加密后数据===== 2 =====>" + encryStr);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
