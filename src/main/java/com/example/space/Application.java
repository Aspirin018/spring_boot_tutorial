package com.example.space;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 人口
 * 放在root package ‘com.example.space’下
 * @author liyu
 * @date 18-7-26
 */
@SpringBootApplication
@EnableCaching
@EnableScheduling //启用定时任务
@EnableAsync //开启异步调用
@EnableAspectJAutoProxy(proxyTargetClass = true) //引入aop, 不适用此注解时默认使用java代理方式，使用此注解则是用cglib方式
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
