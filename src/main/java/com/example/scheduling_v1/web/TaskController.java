package com.example.scheduling_v1.web;

import com.example.scheduling_v1.model.Resp;
import com.example.scheduling_v1.model.ScheduledTask;
import com.example.scheduling_v1.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    @Autowired
    TaskService taskService;
    @PostMapping("/task")
    public Resp addTask(ScheduledTask task){
        int id = taskService.addTask(task);
        if (id==0) {
            return  Resp.Error(HttpStatus.BAD_REQUEST.value(), "加入任务失败");
        }
        return Resp.Ok("加入任务成功",id);
    }

    @DeleteMapping("/task/{id}")
    public Resp deleteTask(@PathVariable Integer id){
        taskService.deleteById(id);
        return Resp.Ok("删除任务成功");
    }

}
