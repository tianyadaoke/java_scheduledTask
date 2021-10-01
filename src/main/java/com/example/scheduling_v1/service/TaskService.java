package com.example.scheduling_v1.service;

import com.example.scheduling_v1.config.InitTask;
import com.example.scheduling_v1.config.ScheduleRunnable;
import com.example.scheduling_v1.model.ScheduledTask;
import com.example.scheduling_v1.repository.ScheduledRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.concurrent.ScheduledFuture;

@Service
public class TaskService {
    @Autowired
    ApplicationContext context;
    @Autowired
    ScheduledRepository repository;
    @Autowired
    ThreadPoolTaskScheduler pool;
    /**
     * 加入定时任务，不仅要加入数据库，还需要加入运行线程池，并且加入停止任务的map
     * @param task
     */
    public Integer addTask(ScheduledTask task) {
        ScheduledTask result = repository.save(task);
        // 判断能存入数据库，并且状态为true,后续操作
        if(Objects.nonNull(result)&&result.getStatus()){
            ScheduleRunnable scheduleRunnable =
                    new ScheduleRunnable(context,result.getTaskName(),result.getParam(),result.getMethod());
            ScheduledFuture<?> scheduledFuture = pool.schedule(scheduleRunnable, new CronTrigger(result.getCronExpres()));
            InitTask.scheduledFutureMap.put(result.getId(),scheduledFuture);
            return result.getId();
        }
        return 0;
    }

    /**
     * 删除定时任务
     * @param id
     */
    public void deleteById(Integer id) {
        //先从map中把任务删了
        InitTask.scheduledFutureMap.get(id).cancel(true);
        //再把数据库中的任务删了
        repository.deleteById(id);
    }
}
