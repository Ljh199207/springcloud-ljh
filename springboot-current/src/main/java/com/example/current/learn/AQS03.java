package com.example.current.learn;


/**
 * AbstractQueuedSynchronizer  AQS
 * state >0 表示获取锁
 * state =0 表示释放了锁
 * 1 入列 addWaiter(Node)
 * 2 出列 setHead(Node)
 * 3 独占式获取和释放同步状态 --同一时刻，仅有一个线程持有同步状态。
 * acquire(int arg)
 * 4.共享式获取和释放同步状态
 * 5.查询同步队列中的等待线程情况。
 */
public class AQS03 {


    public static void main(String[] args) {

    }
}
