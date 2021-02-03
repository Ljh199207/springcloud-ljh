package com.example.current.curr.example.count;

import com.example.current.curr.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicStampedReference;

@Slf4j
@ThreadSafe
public class CountExample6 {

    private static AtomicStampedReference<Integer> count = new AtomicStampedReference<>(100,0);

    public static void main(String[] args) {

        boolean b = count.compareAndSet(100, 101, count.getStamp(), count.getStamp() + 1);
        boolean c = count.compareAndSet(101, 101, count.getStamp(), count.getStamp() + 1);
    }

}
