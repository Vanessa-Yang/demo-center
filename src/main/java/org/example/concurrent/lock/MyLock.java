package org.example.concurrent.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Description 基于AQS实现的自定义锁
 * @Author VanessaYang
 * @Date: 2023/8/15 0015 11:13
 **/
public class MyLock extends AbstractQueuedSynchronizer {

    @Override
    protected boolean tryAcquire(int arg) {
        // cas 加锁 setState if state == 0
        if (compareAndSetState(0, 1)) {
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        // 释放锁
        setExclusiveOwnerThread(null);
        // Q:为什么不用cas释放锁？ A: 加锁解锁是成对出现的，只有加锁成功才能走解锁逻辑，所以不用cas解锁
        setState(0);
        return true;
    }

    public void lock() {
        acquire(1);
    }

    public boolean unlock() {
        return tryRelease(0);
    }
}
