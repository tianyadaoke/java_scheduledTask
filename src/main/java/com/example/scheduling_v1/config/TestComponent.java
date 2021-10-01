package com.example.scheduling_v1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

/**
 * 四个测试用例
 */
@Component
public class TestComponent {
    public void say(){
        System.out.println("我是一个无参的测试任务");
    }
    public void say(String str){
        System.out.println("我是一个有参的测试任务:"+str);
    }
    public void jump() {
        System.out.println("我在蹦蹦跳跳");
    }
    public void jump(String str) {
        System.out.println("我是有参数的蹦蹦跳跳"+str);
    }
}
