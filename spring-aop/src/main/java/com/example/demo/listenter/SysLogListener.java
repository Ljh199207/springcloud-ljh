package com.example.demo.listenter;

import com.example.demo.bean.SysLog;
import com.example.demo.mapper.SysLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SysLogListener {
    @Autowired
    private SysLogDao sysLogDao;

    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) throws InterruptedException {
        SysLog sysLog = (SysLog) event.getSource();
        // 保存日志
        Thread.sleep(10000);
        sysLogDao.saveSysLog(sysLog);
    }
}
