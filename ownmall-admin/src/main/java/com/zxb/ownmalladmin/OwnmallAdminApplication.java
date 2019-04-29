package com.zxb.ownmalladmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zxb.ownmallmapper.mapper")
public class OwnmallAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(OwnmallAdminApplication.class, args);
    }

}
