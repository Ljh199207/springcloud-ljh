package com.example.springribbon.xxlJob;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class SampleXxlJob {

    @XxlJob("jobHandle")
    public ReturnT<String> jobHandle(String name) throws InterruptedException {
        XxlJobLogger.log("这是一个测试abc");
        for (int i = 0; i < 5; i++) {
            XxlJobLogger.log("beat at:" + i);
            System.out.println(i);
            TimeUnit.SECONDS.sleep(2);
        }
        return ReturnT.SUCCESS;
    }
}
