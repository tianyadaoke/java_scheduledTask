package com.example.scheduling_v1.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * 这只是一个测试例子，作为参考 only test
 */
@RestController
public class HelloController {
    public Map<String, ScheduledFuture> scheduledFutureMap = new HashMap<>();

    @Autowired
    ApplicationContext ac;
    @Autowired
    ThreadPoolTaskScheduler scheduler;

    @GetMapping("/hello")
    public void hello(String beanName){
        try {
            Object targetBean=ac.getBean(beanName);
            Method method = targetBean.getClass().getDeclaredMethod("say");
            method.setAccessible(true);
            method.invoke(targetBean);

            CronTask cronTask = new CronTask(() -> System.out.println("我是一个2秒的定时任务，开始啦"), new CronTrigger("0/2 * * * * *"));
            ScheduledFuture scheduledFuture = scheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());
            scheduledFutureMap.put(beanName,scheduledFuture);


        } catch (Exception e) {
            System.out.println("出错了");
            e.printStackTrace();
        }
    }

    @GetMapping("/stop")
    public void stop(String beanName){
        System.out.println("停止任务~~"+beanName);
        ScheduledFuture scheduledFuture = scheduledFutureMap.get(beanName);
        scheduledFuture.cancel(true);
    }

}
