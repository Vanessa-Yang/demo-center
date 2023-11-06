package org.example.concurrent.lock;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description 限流器
 * @Author VanessaYang
 * @Date: 2023/8/15 0015 15:09
 **/
public class SemaphoreDemo2 {

    /**
     * 实现一个同时只能处理5个请求的限流器
     */
    private static Semaphore semaphore = new Semaphore(5);

    /**
     * 定义一个线程池
     */
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(10,
            50, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(200));

    /**
     * 模拟执行方法
     */
    public static void exec() {
        try {
            // 占用一个资源
            semaphore.acquire(1);
            // TODO 模拟业务执行
            System.out.println("执行exec方法");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放一个资源
            semaphore.release(1);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (; ; ) { // 自旋
            // 设置的10个核心线程，模拟以10个/s的速度发起请求
            Thread.sleep(100);
            executor.submit(() -> exec());
        }
    }
}
