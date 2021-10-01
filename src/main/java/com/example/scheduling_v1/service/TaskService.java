package com.example.scheduling_v1.service;

import com.example.scheduling_v1.model.ScheduledTask;
import com.example.scheduling_v1.repository.ScheduledRepository;
import com.example.scheduling_v1.util.ScheduleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    ScheduledRepository repository;
    @Autowired
    ScheduleUtils scheduleUtils;
    /**
     * 加入定时任务，不仅要加入数据库，还需要加入运行线程池，并且加入停止任务的map
     * @param task
     */
    public Integer addTask(ScheduledTask task) {
        ScheduledTask result = repository.save(task);
        // 判断能存入数据库，并且状态为true,后续操作
        if(Objects.nonNull(result)&&result.getStatus()){
            scheduleUtils.addTaskTtoPool(result);
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
        scheduleUtils.removeTaskFromPool(id);
        //再把数据库中的任务删了
        repository.deleteById(id);
    }

    public boolean changeStatus(Integer id) {
        //先找到任务
        Optional<ScheduledTask> optional = repository.findById(id);
        if (optional.isPresent()){
            ScheduledTask task = optional.get();
            Boolean status = task.getStatus();
            // 如果当前status为false，说明没有在执行，要加入执行
            if(!status){
                scheduleUtils.addTaskTtoPool(task);
            } else {
                // 如果当前status为true，说明正在执行，要停止该任务
                scheduleUtils.removeTaskFromPool(task.getId());
            }
            // 最后数据库修改为!status
            task.setStatus(!status);
            repository.save(task);
            return true;
        }
        //没有任务直接失败
        return false;
    }
    public List<ScheduledTask> findAll(){
        return repository.findAll();
    }
}
