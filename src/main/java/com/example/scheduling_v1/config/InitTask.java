package com.example.scheduling_v1.config;

import com.example.scheduling_v1.repository.ScheduledRepository;
import com.example.scheduling_v1.model.ScheduledTask;
import com.example.scheduling_v1.util.ScheduleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * 把数据库的任务读取出来，并运行。
 * 同时加入一个map存储执行任务的ScheduledFuture，以方便将来取消任务
 */
@Component
public class InitTask implements CommandLineRunner, Ordered {
    @Autowired
    ScheduleUtils scheduleUtils;
    @Autowired
    ScheduledRepository scheduledRepository;

    @Override
    public void run(String... args) throws Exception {
        List<ScheduledTask> scheduledTasks = scheduledRepository.findAll();
        for (ScheduledTask task : scheduledTasks) {
            if(task.getStatus()){
                scheduleUtils.addTaskTtoPool(task);
            }
        }
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
