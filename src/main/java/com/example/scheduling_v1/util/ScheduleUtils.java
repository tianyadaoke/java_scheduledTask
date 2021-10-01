package com.example.scheduling_v1.util;

import com.example.scheduling_v1.config.ScheduleRunnable;
import com.example.scheduling_v1.model.ScheduledTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * 这是一个工具类
 * 把任务实体类转换成任务放入线程池
 * 并且把future放入map为了将来关闭线程
 * 实现了线程执行和关闭的功能
 */
@Component
public class ScheduleUtils {
    @Autowired
    ApplicationContext context;
    @Autowired
    ThreadPoolTaskScheduler pool;

    private static Map<Integer, ScheduledFuture> scheduledFutureMap = new HashMap<>();

    public  void addTaskTtoPool(ScheduledTask task){
        ScheduleRunnable scheduleRunnable = new ScheduleRunnable(context,task.getTaskName(),task.getParam(),task.getMethod());
        ScheduledFuture<?> scheduledFuture = pool.schedule(scheduleRunnable, new CronTrigger(task.getCronExpres()));
        scheduledFutureMap.put(task.getId(),scheduledFuture);
    }

    public void removeTaskFromPool(Integer id){
        scheduledFutureMap.get(id).cancel(true);
    }
}
