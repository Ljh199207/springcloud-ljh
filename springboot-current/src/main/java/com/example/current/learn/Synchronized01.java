package com.example.current.learn;


/**    Java 对象头、Monitor  是组成  synchronized
 *     Java 对象头 又包含   Klass Point 类型指针 与 Mark Word 标记类型
 *     Mark Word 用于存储对象自身的运行时数据，如哈希码（HashCode）、GC 分代年龄、锁状态标志、线程持有的锁、
 *     偏向线程 ID、偏向时间戳等等
 *     jdk1.4 -XX:UseSpinning  开启自旋锁
 *     JDK1.6 中默认开启。同时自旋的默认次数为 10 次 -XX:PreBlockSpin 来调整参数
 *
 *    适应自旋锁
 *    线程如果自旋成功了，那么下次自旋的次数会更加多，因为虚拟机认为既然上次成功了，那么此次自旋也很有可能会再次成功，那么它就会允许自旋等待持续的次数更多。
 *    反之，如果对于某个锁，很少有自旋能够成功的，那么在以后要或者这个锁的时候自旋的次数会减少甚至省略掉自旋过程，以免浪费处理器资源。
 *
 *    锁消除：数据不存在共享就不存在锁的必要
 *
 *    锁粗化：对于连续加锁的操作 、多个连续的加锁、解锁操作连接在一起，扩展成一个范围更大的锁
 *    锁主要存在四种状态 ：无锁状态、偏向锁状态、轻量级锁状态、重量级锁状
 *
 *    重量级锁： 是通过对象内部的监视器 Monitor 来实现
 *    Monitor 的本质是，依赖于底层操作系统的 Mutex Lock 实现。操作系统实现线程之间的切换，需要从用户态到内核态的切换，切换成本非常高。
 *
 *    轻量级锁：通过CAS来获取锁和释放锁与唤醒被挂起的锁
 *
 *    偏向锁：为了在无多线程竞争的情况下，尽量减少不必要的轻量级锁执行路径(全局安全点  这个时间点是上没有正在执行的代码)
 *    偏向锁在 JDK 1.6 以上，默认开启。开启后程序启动几秒后才会被激活，可使用 JVM 参数 -XX：BiasedLockingStartupDelay = 0 来关闭延迟。
 *    JVM参数 -XX:-UseBiasedLocking=false 关闭偏向锁
 *
 *
 */
public class Synchronized01 {

    public synchronized void test1() {

    }

    public void test2() {
        synchronized (this) {

        }
    }
}
