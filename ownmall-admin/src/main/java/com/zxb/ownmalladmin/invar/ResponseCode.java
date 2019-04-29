package com.zxb.ownmalladmin.invar;

/**
 * 响应编码定义类
 */
public class ResponseCode {
    /**
     * 成功状态
     */
    public static  final  String SUCCESS_CODE = "AAAAAAAA";
    public static final String SUCCESS_DEFAULT_MSG = "操作成功";
    /**
     * 操作失败
     */
    public static final String ERROR_DEFAULT_CODE = "BBBBBBBB";
    public static final String ERROE_DEFAULT_MSG = "操作失败";
    /**
     * 存在异常
     */
    public static final String EXCEPTION_DEFAULT_CODE = "CCCCCCCC";
    public static final String EXCEPTION_DEFAULT_MSG = "操作异常";

    /**
     * 错误状态，每个编码对应一种操作失败情况
     */
    //系统信息查询
    public static final String SEL_SYSINFO_ERROR_CODE = "ZXCSEL0001";
    public static final String SEL_SYSINFO_ERROR_MSG = "查询系统信息失败";
    //修改系统信息
    public static final String UPDATA_SYSINFO_ERROR_CODE = "ZXCUPDATA0001";
    public static final String UPDATA_SYSINFO_ERROR_MSG = "修改系统信息失败";
    //修改系统信息异常
    public static final String UPDATA_SYSINFO_EXCEPTION_CODE = "ZXCUPDATA0002";
    public static final String UPDATA_SYSINFO_EXCEPTION_MSG = "修改系统信息发生异常";


}
