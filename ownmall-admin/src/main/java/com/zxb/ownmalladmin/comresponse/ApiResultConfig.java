package com.zxb.ownmalladmin.comresponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiResultConfig {

    @Bean
    public ResponseBodyWrapFactoryBean getResponseBodyWrap(){
        return new ResponseBodyWrapFactoryBean();
    }
}
