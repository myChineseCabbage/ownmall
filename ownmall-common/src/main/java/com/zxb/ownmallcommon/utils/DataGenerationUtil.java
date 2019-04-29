package com.zxb.ownmallcommon.utils;

import java.util.Random;
import java.util.UUID;

/**
 * 一些变量值的生成工具
 */
public class DataGenerationUtil {


    /**
     * 生成uuid32位
     * @return
     */
    public static String getUUID32(){
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-","");
        return  uuid;
    }

    /**
     * 生成指定的四位数字字符串
     * @return
     */
    public static String random4str(int length){
        String str = "";
        for (int i=0;i<length;i++){
            str = str+new Random().nextInt(10);
        }
        return str;
    }
}
