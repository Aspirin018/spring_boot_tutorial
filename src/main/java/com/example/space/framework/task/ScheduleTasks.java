package com.example.space.framework.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liyu
 * @date 18-7-28
 */
@Component
public class ScheduleTasks {

    private static final SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 3000) //上次开始执行后3秒
//    @Scheduled(fixedDelay = 3000) //上次执行完毕后3秒
//    @Scheduled(initialDelay = 1000, fixedRate = 3000) //第一次延迟1秒后执行，之后每3秒执行一次
    public void reportCurrentTime(){
        System.out.println("现在时间:" + dataFormat.format(new Date()));
    }
}
