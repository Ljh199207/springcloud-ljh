package com.example.springbootquartz;

import com.example.springbootquartz.job.RamJob;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class SpringbootQuartzApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 1. 创建SchedulerFactory
     * 2. 创建Scheduler
     * 3. 创建JobDetail
     * 4. 创建Trigger
     * 5. 注册到Scheduler：scheduler.scheduleJob(jobDetail, trigger)
     * 6. 启动Scheduler：scheduler.start()
     */

    @Test
    public void testExecute() throws SchedulerException, InterruptedException {
        // 1.创建Scheduler的工厂
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();

        // 2. 从工厂中获取调度器实例
        Scheduler scheduler = schedulerFactory.getScheduler();
        // 3.创建JobDetail
        JobDetail jobDetail = JobBuilder.newJob(RamJob.class)
                .withDescription("job 描述")
                .withIdentity("name", "group")
                .build();
        //4. 创建Trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withDescription("trigger 描述")
                .withIdentity("trigger name", "triggerGroup")
                .startAt(new Date())
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
        // 5.注册任务和定时器
        scheduler.scheduleJob(jobDetail, trigger);
        // 6.启动调度器
        scheduler.start();
        System.out.println("启动时间 ： " + new Date() + " " + Thread.currentThread().getName());
        Thread.sleep(60000);
        System.out.println("done");
    }
}
