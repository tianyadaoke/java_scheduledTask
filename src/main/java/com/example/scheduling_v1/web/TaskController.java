package com.example.scheduling_v1.web;

import com.example.scheduling_v1.model.Resp;
import com.example.scheduling_v1.model.ScheduledTask;
import com.example.scheduling_v1.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        try {
            taskService.deleteById(id);
        } catch (Exception e) {
           return Resp.Error(HttpStatus.BAD_REQUEST.value(), "删除任务失败，请确定是否有次任务");
        }
        return Resp.Ok("删除任务成功");
    }
    @PutMapping("/task/{id}")
    public Resp updateTask(@PathVariable Integer id){
        if(taskService.changeStatus(id)){
            return  Resp.Ok("更改任务状态成功");
        }
        return Resp.Error(HttpStatus.BAD_REQUEST.value(), "更改任务状态失败，请确定是否有次任务");
    }
    @GetMapping("/tasks")
    public Resp findAllTasks(){
        List<ScheduledTask> tasks = taskService.findAll();
        return Resp.Ok("查找所有任务成功",tasks);
    }

}
