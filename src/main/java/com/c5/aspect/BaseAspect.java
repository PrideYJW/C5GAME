package com.c5.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/11/4.
 */
@Component
@Aspect
public class BaseAspect {


    @Pointcut("execution(public * com.c5.service.impl.BaseServiceUtilImpl.*(..))")
    public void BaseAspect() {
    }

    @After("BaseAspect()")
    public void after(JoinPoint joinPoint) {
    }
}