package com.zxb.ownmalladmin.medecryption;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * md5加密
 */
public class Md5Utils {

    public static  String ecodeByMd5(String string){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            Base64.Encoder base64Encoder = Base64.getEncoder();
            //加密字符串
            String resultStr = base64Encoder.encodeToString(md5.digest(string.getBytes("utf-8")));
            return  resultStr;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
        }
        return  null;
    }
}
