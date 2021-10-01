# java_scheduledTask
在已经运行的spring程序中配置定时任务，参考引用松哥的scheduling自己写
需要在数据库中新建一个schedule，并相对配置yml文件

Mysql和Jpa就是帮助任务在数据库中的增删改查

关键的几点：
1. 新建一个ThreadPoolTaskScheduler类，该类可以加入定时任务并且执行，很关键
2. 任务类要继承Runnable，然后通过反射拿到bean的对象，然后调用方法实现执行
3. 构建定时任务是通过往定时任务线程池中加入pool.schedule(scheduleRunnable, new CronTrigger(task.getCronExpres()))实现,
这里支持cron表达式很关键
4. 最后很关键的一点是关闭定时任务的时候，需要构建一个scheduledFutureMap，根据id存储了每一个任务的ScheduledFuture，
然后关闭的时候调用cancel()方法得以实现

只是基本实现了该功能，后续完善等将来有空再说，继续学习中...
