package com.example.demo.listenter;

import com.example.demo.bean.SysLog;
import org.springframework.context.ApplicationEvent;

public class SysLogEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public SysLogEvent(SysLog source) {
        super(source);
    }
}
