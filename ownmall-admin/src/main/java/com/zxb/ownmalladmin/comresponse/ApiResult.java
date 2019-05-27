package com.zxb.ownmalladmin.comresponse;


/**
 * 返回参数实体类
 */
public class ApiResult {
    /**
     * 错误码，对应{@Link ErroCode} ,表示一种错误类型
     * 如果成功，则RetCode = "AAAAAAAA"
     */
    private  String rc;
    /**
     * 错误信息
     */
    private String  msg;
    /**
     * 返回的结果包装到result中 result可以是单个对象
    */
    private Object result;

    public ApiResult() {
    }
    public ApiResult(Object result){
        this.result = result;
    }

    public ApiResult(String rc, String msg) {
        this.rc = rc;
        this.msg = msg;
    }

    public ApiResult(String rc, String msg, Object result) {
        this.rc = rc;
        this.msg = msg;
        this.result = result;
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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "rc='" + rc + '\'' +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
