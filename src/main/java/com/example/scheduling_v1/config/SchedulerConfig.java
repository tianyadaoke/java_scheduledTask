package com.example.scheduling_v1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;


/**
 * 这是一个定时任务线程池，用来执行定时任务线程
 */
@Component
public class SchedulerConfig {
    @Bean
    ThreadPoolTaskScheduler pool(){
        ThreadPoolTaskScheduler poll = new ThreadPoolTaskScheduler();
        poll.setPoolSize(4);
        poll.setRemoveOnCancelPolicy(true);
        poll.setThreadNamePrefix("ThreadPool-");
        return poll;
    }
}
