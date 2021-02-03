package com.example.current.learn;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 在lock 前  配合 Object 的 #wait()、#notify() 等一系列方法可以实现等待 / 通知模式
 */
public class Condition04 {

    private LinkedList<String> buffer;

    private int maxSize;

    private Lock lock;

    private Condition fullCondition;

    private Condition notFullCondition;

    Condition04(int maxSize) {
        this.maxSize = maxSize;
        buffer = new LinkedList<String>();
        lock = new ReentrantLock();
        fullCondition = lock.newCondition();
        notFullCondition = lock.newCondition();
    }

    public void set(String messgae) {
        try {
            lock.lock();
            while (maxSize == buffer.size()) {
                notFullCondition.await();
            }
            buffer.add(messgae);
            fullCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void get() {
        try {
            lock.lock();
            while (maxSize == 0) {
                fullCondition.await();
            }
            buffer.poll();
            notFullCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
