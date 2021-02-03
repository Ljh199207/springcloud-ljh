package com.example.current.learn;

/**
 * volatile  保证可见性、不保证原子性  禁止指令重排序
 * happens-before原则
 * 1.程序次序规则：一个线程内，按照代码顺序，书写在前面的操作，happens-before 于书写在后面的操作。
 * 2.锁定规则：一个 unLock 操作，happens-before 于后面对同一个锁的 lock 操作。
 * 3.volatile 变量规则： 对一个变量的写操作，happens-before 于后面对这个变量的读操作。
 * 4.传递规则：如果操作 A happens-before 操作 B，而操作 B happens-before 操作C，则可以得出，操作 A happens-before 操作C
 * 5.线程启动规则：Thread 对象的 start 方法，happens-before 此线程的每个一个动作。
 * 6.线程中断规则：对线程 interrupt 方法的调用，happens-before 被中断线程的代码检测到中断事件的发生。
 * 7.线程终结规则：线程中所有的操作，都 happens-before 线程的终止检测，我们可以通过Thread.join() 方法结束、Thread.isAlive() 的返回值手段，检测到线程已经终止执行。
 * 8.对象终结规则：一个对象的初始化完成，happens-before 它的 finalize() 方法的开始
 * 在每一个volatile 写操作前面 插入一个storeStore屏障  storeStore在写之前，其前面所有的写操作都刷新到主存中
 * 在每一个volatile 写操作后面 插入一个storeLoad屏障  storeLoad是避免后面可能有的volatile 读/写操作重排序
 * 在每一个volatile 读操作后面 插入一个loadLoad屏障  loadLoad 是避免可能与下面的普通读重排序
 * 在每一个volatile 读操作后面 插入一个loadStore屏障 loadStore是避免可能与下面普通写重排序
 *
 */

public class Volatile02 {
    int i = 0;
    volatile boolean flag = false;

    // Thread A
    public void write() {
        i = 2;              // 1
        flag = true;        // 2
    }

    // Thread B
    public void read() {
        if (flag) {                                   // 3
            System.out.println("---i = " + i);      // 4
        }
    }
}
