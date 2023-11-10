package org.example.concurrent.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Description 烧水泡茶，CompletableFuture 实现
 * 背景：著名数学家华罗庚在《统筹方法》这篇文章里介绍了一个烧水泡茶的例子，
 * 文中提到最优的工序应该是这样：
 * 对于烧水泡茶这个程序，一种最优的分工方案：用两个线程 T1 和 T2 来完成烧水泡茶程序，
 * T1 负责烧水壶、烧开水、泡茶这三道工序，T2 负责洗茶壶、洗茶杯、拿茶叶三道工序，其中
 * T1 在执行泡茶这道工序时需要等待 T2 完成拿茶叶的工序。
 * @Author VanessaYang
 * @Date: 2023/11/8 0008 16:24
 **/
public class MakingTeaUsingCompletableFuture {

    public static void main(String[] args) {
        // 任务1：洗水壶 -> 烧开水
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            System.out.println("T1: 洗水壶");
            sleep(1, TimeUnit.SECONDS);

            System.out.println("T1: 烧开水");
            sleep(15, TimeUnit.SECONDS);
        });

        // 任务2：洗茶壶 -> 洗茶杯 -> 拿茶叶
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T2: 洗茶壶");
            sleep(1, TimeUnit.SECONDS);

            System.out.println("T2: 洗茶杯");
            sleep(2, TimeUnit.SECONDS);

            System.out.println("T2: 拿茶叶");
            sleep(1, TimeUnit.SECONDS);
            return "龙井";
        });


        // 任务3：任务1和任务2完成后执行：泡茶

        CompletableFuture<String> f3 = f1.thenCombine(f2, (__, tea) -> {
            System.out.println("T1: 拿到茶叶：" + tea);
            System.out.println("T1: 泡茶");
            return "上茶：" + tea;
        });
        // 等待任务3执行结果
        System.out.println(f3.join());
    }

    private static void sleep(int timeout, TimeUnit unit) {
        try {
            unit.sleep(timeout);
        } catch (InterruptedException ignore) {
        }

    }


}
