package com.example.scheduling_v1.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * 这是一个可执行类，类中的方法就是从数据库中读出
 */
public class ScheduleRunnable implements Runnable{
    private ApplicationContext context;
    private String beanName;
    private String param;
    private String methodName;

    public ScheduleRunnable(ApplicationContext context, String beanName, String param, String methodName) {
        this.context = context;
        this.beanName = beanName;
        this.param = param;
        this.methodName = methodName;
    }

    @Override
    public void run() {
        Object targetBean = context.getBean(beanName);
        try {
            if (StringUtils.hasText(param)) {
                Method method = targetBean.getClass().getDeclaredMethod(methodName, String.class);
                method.invoke(targetBean,param);
            } else {
                Method method = targetBean.getClass().getDeclaredMethod(methodName);
                method.invoke(targetBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
