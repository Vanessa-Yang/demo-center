package org.example.concurrent.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @Description 烧水泡茶，Future 实现
 * 背景：著名数学家华罗庚在《统筹方法》这篇文章里介绍了一个烧水泡茶的例子，
 * 文中提到最优的工序应该是这样：
 * 对于烧水泡茶这个程序，一种最优的分工方案：用两个线程 T1 和 T2 来完成烧水泡茶程序，
 * T1 负责烧水壶、烧开水、泡茶这三道工序，T2 负责洗茶壶、洗茶杯、拿茶叶三道工序，其中
 * T1 在执行泡茶这道工序时需要等待 T2 完成拿茶叶的工序。
 * @Author VanessaYang
 * @Date: 2023/11/8 0008 16:24
 **/
public class MakeTeaUsingFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建任务T2的FutureTask
        FutureTask<String> ft2 = new FutureTask<>(new T2Task());
        // 创建任务T1的FutureTask
        FutureTask<String> ft1 = new FutureTask<>(new T1Task(ft2));

        // 线程T1执行任务ft1
        Thread t1 = new Thread(ft1);
        t1.start();

        // 线程T2执行任务ft2
        Thread t2 = new Thread(ft2);
        t2.start();

        // 等待线程t1执行结果
        System.out.println(ft1.get());
    }

}

class T1Task implements Callable<String> {
    FutureTask<String> ft2;
    // T1 任务需要 T2 任务的 FutureTask

    public T1Task(FutureTask<String> ft2) {
        this.ft2 = ft2;
    }

    @Override
    public String call() throws Exception {
        System.out.println("T1: 洗水壶");
        TimeUnit.SECONDS.sleep(1);

        System.out.println("T1: 烧开水");
        TimeUnit.SECONDS.sleep(15);

        // 获取T2线程的茶叶
        String tea = ft2.get();
        System.out.println("T1: 拿到茶叶" + tea);

        System.out.println("T1: 泡茶");
        return "上茶：" + tea;
    }
}

class T2Task implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("T2: 洗茶壶");
        TimeUnit.SECONDS.sleep(1);

        System.out.println("T2: 洗茶杯");
        TimeUnit.SECONDS.sleep(2);

        System.out.println("T2: 拿茶叶");
        TimeUnit.SECONDS.sleep(1);
        return "龙井";
    }
}
