package com.zxb.ownmalladmin.config;

import com.zxb.ownmalladmin.controller.SystemController;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    private Logger logger = LoggerFactory.getLogger(SystemController.class);

    public ShiroConfig(){
        logger.debug("shiro配置内容。。。。。。。");
    }

    /*shiroFilter*/
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        logger.debug("ShiroConfig.shiroFilterFactoryBean()>>>>>>  ");

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/*","anon");
        //配置退出 过滤器。其中具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout","logout");
        ///*过滤定义 从上向下顺序执行，一般将/**放在最为下面 这是一个坑，一不小心代码就不好使*/
        //authc:所有url都必须认证通过才可以访问 anon:所有url都乐意匿名访问
        filterChainDefinitionMap.put("/**","authc");
        //如果不设置默认自动会寻找web更目录下的/login.jsp页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");

        //未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return  shiroFilterFactoryBean;
    }

    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return  myShiroRealm;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return  securityManager;
    }
}
