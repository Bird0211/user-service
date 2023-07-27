package com.demo.user.aop;

import com.demo.user.util.GsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import java.util.Map;
import java.util.Objects;


@Aspect
@Component
@Slf4j
public class LogAspect {

    @After("log()")
    public void interceptForLog(JoinPoint joinPoint, RequestMapping requestMapping) {

    }

    /** Pointcut */
    @Pointcut("@annotation(com.demo.user.aop.AopLog)")
    public void log() {
    }

    @Before("log()")
    public void beforeLog(JoinPoint point) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuffer sb = new StringBuffer();
        sb.append("\n URL: ").append(request.getRequestURL());
        sb.append("\n IP: ").append(getIp(request));
        sb.append("\n Class: ").append(point.getSignature().getDeclaringTypeName());
        sb.append("\n Method:").append(point.getSignature().getName());
        sb.append("\n body:").append(GsonUtil.GsonString(point.getArgs()));
        sb.append("\n paramter: ").append(GsonUtil.GsonString(parameterMap));

        log.info(sb.toString());
    }


    private String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
