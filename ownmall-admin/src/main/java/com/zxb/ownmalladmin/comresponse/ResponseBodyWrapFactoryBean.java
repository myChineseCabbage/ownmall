package com.zxb.ownmalladmin.comresponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 设置bean过滤原则
 */
public class ResponseBodyWrapFactoryBean implements InitializingBean {


    @Autowired
    private RequestMappingHandlerAdapter adapter;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> handlers = new ArrayList(returnValueHandlers);
        decorateHandlers(handlers);
        adapter.setReturnValueHandlers(handlers);

    }


    public void decorateHandlers(List<HandlerMethodReturnValueHandler> handlers){
        Iterator itHandlers = handlers.iterator();
        while(itHandlers.hasNext()){
            HandlerMethodReturnValueHandler handler = (HandlerMethodReturnValueHandler)itHandlers.next();
            if(handler instanceof RequestResponseBodyMethodProcessor){
                ResponseBodyWrapHandler decorateHandlers = new ResponseBodyWrapHandler(handler);
                int index = handlers.indexOf(handler);
                handlers.set(index,decorateHandlers);
                break;
            }
        }

    }
}
