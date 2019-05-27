package com.zxb.ownmalladmin.comresponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 定义未处理异常统一拦截
 */
@ControllerAdvice
public class CommExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ApiResult handle(Exception e){
        ApiResult apiResult = new ApiResult(ErrorCode.SERVER_ERROR.getRc(),"系统异常原因"+e.getMessage());
        return apiResult;
    }

}

