package com.example.space.framework.aspect;

//import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObject;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 * 使用AOP统一处理Web请求日志
 * @author liyu
 * @date 18-7-28
 */
/**
 * @Order定义每个切面的优先级，我们需要@Order(i)注解来标识切面的优先级。i的值越小，优先级越高。
 * 在@Before中优先执行@Order(5)的内容，再执行@Order(10)的内容
 * 在@After和@AfterReturning中优先执行@Order(10)的内容，再执行@Order(5)的内容
 * 在切入点前的操作，按order的值由小到大执行
 * 在切入点后的操作，按order的值由大到小执行
 */
@Aspect
@Component
@Order(1)
public class WebLogAspect {

//    private Logger logger = Logger.getLogger(getClass());
    /**
     * 注意这里的logger要打印日志到mongo: "mongodb"和log4j.properties中的配置要一致
     */
    private Logger logger = Logger.getLogger("mongodb");

    /**
     * 在WebLogAspect切面中，分别通过doBefore和doAfterReturning两个独立函数实现了切点头部和切点返回后执行的内容，
     * 若我们想统计请求的处理时间，就需要在doBefore处记录时间，并在doAfterReturning处通过当前时间与开始处记录的时间计算得到请求处理的消耗时间。
     * 那么我们是否可以在WebLogAspect切面中定义一个成员变量来给doBefore和doAfterReturning一起访问呢？是否会有同步问题呢？
     * 的确，直接在这里定义基本类型会有同步问题，所以我们可以引入ThreadLocal对象
     * ThreadLocal用于保存某个线程共享变量：对于同一个static ThreadLocal，不同线程只能从中get，set，remove自己的变量，而不会影响其他线程的变量。
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.example.space.api.web..*.*(..))")
    public void webLog(){}


   /* *//**
     * 利用切面打印请求日志
     * @param joinPoint
     *//*
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){

        startTime.set(System.currentTimeMillis());

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("URL：" + request.getRequestURL().toString());
        logger.info("HTTP_METHOD:" + request.getMethod());
        logger.info("IP:" + request.getRemoteAddr());
        logger.info("CLASS_METHOD:" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS:" + Arrays.toString(joinPoint.getArgs()));
    }*/

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        BasicDBObject logInfo = getBasicDBObject(request, joinPoint);
        logger.info(logInfo);
    }

    private BasicDBObject getBasicDBObject(HttpServletRequest request, JoinPoint joinPoint){
        BasicDBObject object = new BasicDBObject();
        object.append("requestURL", request.getRequestURL().toString());
        object.append("requestURI", request.getRequestURI());
        object.append("queryString", request.getQueryString());
        object.append("remoteAddr", request.getRemoteAddr());
        object.append("remotePort", request.getRemotePort());
        object.append("localAddr", request.getLocalAddr());
        object.append("localName", request.getLocalName());
        object.append("method", request.getMethod());
        object.append("headers", getHeaderInfo(request));
        object.append("parameters", request.getParameterMap());
        object.append("classMethod", joinPoint.getSignature().getDeclaringTypeName());
        object.append("args", Arrays.toString(joinPoint.getArgs()));
        return object;
    }

    private Map<String, String> getHeaderInfo(HttpServletRequest request){
        Map<String, String> map = new HashMap<>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturing(Object ret) {
        logger.info("RESPONSE:" + ret);
        logger.info("SPEND TIME:" + (System.currentTimeMillis() - startTime.get()));
    }
}
