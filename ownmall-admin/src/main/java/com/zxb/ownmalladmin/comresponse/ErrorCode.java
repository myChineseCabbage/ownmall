package com.zxb.ownmalladmin.comresponse;

public enum ErrorCode {
    SUCCESS("1","成功"),
    NO_PERMISSION("211","权限不足"),
    SERVER_ERROR("10000","服务器异常"),
    AUTH_ERROR("10001","认证失败"),
    PARAMS_ERROR("10002","参数错误"),
    JSON_PARSE_ERROR("10003","Json解析错误"),
    ILLEAGAL_STRING("15001","非法字符串"),
    UNKNOW_ERROR("16000","未知错误");

    private  String rc;
    private  String msg;

    ErrorCode() {
    }

    ErrorCode(String rc, String msg) {
        this.rc = rc;
        this.msg = msg;
    }

    public String getRc() {
        return rc;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
