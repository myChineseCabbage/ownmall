package com.zxb.ownmallcommon.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期生成工具类
 */
public class DateTimeUtils {


    /**
     * 生成的时间格式 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static  String dateTimeFormat(){
        Timestamp ts = new Timestamp(new Date().getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = simpleDateFormat.format(ts);
        return  createTime;
    }
}
