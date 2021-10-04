package com.example.scheduling_v1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class ScheduledTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "不能为空")
    private String taskName;
    @NotNull(message = "不能为空")
    private String method;
    private String param;
    @NotNull(message = "不能为空")
    private String cronExpres;
    //0为不可用，1为可用
    private Boolean status = Boolean.TRUE;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getCronExpres() {
        return cronExpres;
    }

    public void setCronExpres(String cronExpres) {
        this.cronExpres = cronExpres;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ScheduledTask{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", param='" + param + '\'' +
                ", cronExpres='" + cronExpres + '\'' +
                ", status=" + status +
                '}';
    }
}
