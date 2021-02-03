package com.example.current.curr.example.count;

import com.example.current.curr.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class CountExample5 {

    private static AtomicIntegerFieldUpdater<CountExample5> updater = AtomicIntegerFieldUpdater.newUpdater(CountExample5.class, "count");

    @Getter
    public volatile int count = 100;

    private static CountExample5 example5 = new CountExample5();


    public static void main(String[] args) {
        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success, {}", example5.getCount());
        }

        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success, {}", example5.getCount());
        } else {

        }
        log.info("update failed, {}", example5.getCount());
    }
}
