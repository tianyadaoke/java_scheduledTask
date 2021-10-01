package com.example.scheduling_v1.model;

import org.springframework.http.HttpStatus;


public class Resp{
    private Integer code;
    private String msg;
    private Object data;

    private Resp(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private Resp(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Resp Ok(String msg) {
        return new Resp(HttpStatus.OK.value(), msg);
    }

    public static Resp Ok(String msg, Object data) {
        return new Resp(HttpStatus.OK.value(), msg, data);
    }

    public static Resp Error(Integer code, String msg) {
        return new Resp(code, msg);
    }

    public static Resp Error(Integer code, String msg, Object data) {
        return new Resp(code, msg, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
