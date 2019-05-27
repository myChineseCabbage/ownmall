package com.zxb.ownmalladmin.comresponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zxb.ownmalladmin.invar.OperContant;
import com.zxb.ownmalladmin.medecryption.EnCryptDecryptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 实现具体的统一json返回处理
 */
public class ResponseBodyWrapHandler implements HandlerMethodReturnValueHandler {
    private Logger logger = LoggerFactory.getLogger(ResponseBodyWrapHandler.class);
    private final HandlerMethodReturnValueHandler delegate;

    public ResponseBodyWrapHandler(HandlerMethodReturnValueHandler delegate){
        this.delegate = delegate;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {

        return delegate.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        logger.debug(returnType.toString()+":"+mavContainer.toString()+":"+webRequest.toString());
        ApiResult apiResult = null;
        if(returnValue instanceof  ApiResult){
            apiResult = (ApiResult) returnValue;
            logger.debug(apiResult.toString());
            //加密操作

        }else{
            apiResult = new ApiResult(ErrorCode.SUCCESS.getRc(),ErrorCode.SUCCESS.getMsg(),returnValue);
            logger.debug(apiResult.toString());
        }
        //是否加密
        if(OperContant.WHETHERENCRYPT>0){ //加密
            Object o  =  apiResult.getResult();
            JSONObject jsonObject = JSONObject.parseObject(o.toString());
            JSONObject resultObj = EnCryptDecryptUtils.encrypt(jsonObject);
            resultObj.put("rc",apiResult.getRc());
            resultObj.put("msg",apiResult.getMsg());
            delegate.handleReturnValue(resultObj,returnType,mavContainer,webRequest);
        }else {
            delegate.handleReturnValue(apiResult,returnType,mavContainer,webRequest);
        }

    }
}
