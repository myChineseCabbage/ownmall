package com.zxb.ownmalladmin.decodeandencode;
import com.alibaba.fastjson.JSONObject;
import com.zxb.ownmalladmin.invar.OperContant;
import com.zxb.ownmalladmin.medecryption.EnCryptDecryptUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonInputMessage;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;


@Component
@ControllerAdvice(basePackages = "com.zxb.ownmalladmin.controller")
public class DecodeRequestBodyAdvice implements RequestBodyAdvice {
    private static Logger logger = LoggerFactory.getLogger(DecodeRequestBodyAdvice.class);
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        //设置成false,就不会走这个类了
        if(OperContant.WHETHERENCRYPT>0){ //走加密
            return true;
        }else { //不加密
            return  false;
        }

    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        HttpInputMessage inputMessage2 = inputMessage;
        logger.debug("对方法method："+parameter.getMethod().getName());
       InputStream encryptStream =inputMessage.getBody();
       JSONObject jsonObject = EnCryptDecryptUtils.descrypt(encryptStream);
        logger.debug(jsonObject.toJSONString());

        return new SecretHttpMessage(new ByteArrayInputStream(jsonObject.toJSONString().getBytes()),inputMessage.getHeaders());
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}
