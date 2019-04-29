package com.zxb.ownmallproscenium.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class HelloController {

    @RequestMapping("/helloUser")
    public String hello(){
        return "前台管理系统";
    }
}
