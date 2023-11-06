package org.example.concurrent.lock;

import java.util.concurrent.Semaphore;

/**
 * @Description 信号量 共享锁
 * Semaphore 是一个计数信号量，经常用于限制获取资源的线程数量
 * @Author VanessaYang
 * @Date: 2023/8/15 0015 14:57
 **/
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore windows = new Semaphore(3);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    // 占用窗口
                    windows.acquire();
                    System.out.println(Thread.currentThread().getName() + ": 开始买票");
                    // 模拟买票流程
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread().getName() + ": 购票成功");

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 释放窗口
                    windows.release();
                }
            }).start();
        }
    }

}
