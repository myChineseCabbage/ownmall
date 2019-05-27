package com.zxb.ownmalladmin.commaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspect {
    private Logger logger = LoggerFactory.getLogger(ControllerAspect.class);
    private final String ExpGetResultDataPoint = "execution(* com.zxb.ownmalladmin.*..*.*(..))";

    //定义切入点,拦截servie包其子包下的所有类的所有方法
    //@Pointcut("execution(* com.zxb.ownmalladmin.controller..*.*(..))")
    @Pointcut(ExpGetResultDataPoint)
    public void excuteController() {

    }


    @Before("excuteController()")
    public  void doBefore(JoinPoint joinPoint) throws Throwable{
        logger.debug("我是前置通知");
        //获取目标方法的参数信息
//        Object[] objs = joinPoint.getArgs();
//        for(int i = 0;i<objs.length;i++){
//            logger.debug("now--》argItem--->"+objs[i]);
//
//        }

    }

    @AfterReturning(returning = "ret",pointcut = "excuteController()")
    public void doAfterReturning(Object ret) throws  Throwable{
        //处理完请求返回的内容
        logger.debug("返回的结果"+ret);
    }
}
