package org.example.concurrent.future;

import java.util.concurrent.*;

/**
 * @Description CompletionService:
 * 主要功能：
 * 一边生成任务，一边获取任务的返回值，让两件事分开执行，
 * 任务之间不会互相阻塞，可以实现先执行完的先取结果，不再依赖任务顺序了。
 * <p>
 * 原理：
 * 内部有一个先进先出的阻塞队列，用于保存已经执行完成的Future，通过调用它的take方法或poll方法
 * 可以获取到一个执行完成的Future，进而通过调用Future接口实现类的get方法获取最终的结果。
 * @Author VanessaYang
 * @Date: 2023/11/8 0008 14:07
 **/
public class CompletionServiceDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(10);
        // 创建 CompletionService
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
        // 异步向电商s1询价
        cs.submit(() -> getPriceByS1());
        // 异步向电商s2询价
        cs.submit(() -> getPriceByS2());
        // 异步向电商s3询价
        cs.submit(() -> getPriceByS3());

        // 将询价结果异步保存到数据库
        for (int i = 0; i < 3; i++) {
            Integer r = cs.take().get();
            executor.execute(() -> save(r));
        }
    }

    private static void save(Integer r) {
        System.out.println("电商报价" + r + "入库");
    }

    private static int getPriceByS1() throws InterruptedException {
        System.out.println("电商S1报价1200");
        Thread.sleep(1050);
        return 1200;
    }

    private static int getPriceByS2() throws InterruptedException {
        System.out.println("电商S2报价800");
        Thread.sleep(5000);
        return 800;
    }

    private static int getPriceByS3() throws InterruptedException {
        System.out.println("电商S3报价1500");
        Thread.sleep(1000);
        return 1500;
    }
}
