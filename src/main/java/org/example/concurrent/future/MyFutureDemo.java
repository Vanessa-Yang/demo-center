package org.example.concurrent.future;

import org.checkerframework.common.reflection.qual.NewInstance;
import org.springframework.scheduling.config.Task;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/11/8 0008 11:30
 **/
public class MyFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        threadDemo();

        futureTaskDemo();
    }

    private static void futureTaskDemo() throws ExecutionException, InterruptedException {
        Task task = new Task();
        // 构建futureTask
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        // 作为Runnable入参
        new Thread(futureTask).start();
        System.out.println("task运行结果：" + futureTask.get());
    }

    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("子线程正在计算");
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            return sum;
        }
    }

    private static void threadDemo() throws ExecutionException, InterruptedException {
        /**
         * Runnable缺陷：
         * - 不能返回一个返回值
         * - 不能抛出 checked Exception
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("通过Runnable方式执行任务");
            }
        }).start();

        /**
         * Callable的call方法可以有返回值，可以声明抛出异常
         */
        FutureTask<String> task = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("通过Callable方式执行任务");
                Thread.sleep(3000);
//                throw new Exception("made it");
                return "返回任务结果";
            }
        });
        new Thread(task).start();
        System.out.println(task.get());
    }

}
