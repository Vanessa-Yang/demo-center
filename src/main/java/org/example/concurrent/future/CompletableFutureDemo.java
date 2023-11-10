package org.example.concurrent.future;

import org.example.design.facade.OracleHelper;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Description CompletableFuture 是 Future 接口的扩展和增强，实现了对任务的编排能力。
 * 应对于业务逻辑存在串行[依赖]、并行、聚合的关系
 * 使用注意：
 * 1. Supplier 接口的 get() 方法是有返回值的（会阻塞）
 * 2. 没有指定 Executor 的方法会使用  ForkJoinPool.commonPool() 作为它的线程池执行异步代码。
 * 如果指定线程池，则使用指定的线程池运行。
 * 3. 强烈建议根据不同的业务类型创建不同的线程池，避免互相干扰。
 * @Author VanessaYang
 * @Date: 2023/11/8 0008 14:35
 **/
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        runSupplyAsyncDemo();

//        whenCompleteExceptionallyDemo();

//        thenApplyDemo();

//        thenComposeDemo();

//        diffBetweenThenArrayAndThenCompose();

//        thenAcceptDemo();

//        thenAcceptBothDemo();

//        thenRunDemo();

//        thenCombineDemo();

//        applyToEitherDemo();

//        anyOfDemo();

        allOfDemo();
    }

    /**
     * allOf 方法用来实现多 CompletableFuture 的同时返回。
     */
    private static void allOfDemo() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future1完成！");
            return "future1完成";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future2完成！");
            return "future2完成";
        });

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2);

        try {
            combinedFuture.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("future1: " + future1.isDone() + ", future2: " + future2.isDone());
    }

    /**
     * anyOf 方法的参数是多个给定的 CompletableFuture，当其中的任何一个完成时，方法返回这个 CompletableFuture.
     */
    private static void anyOfDemo() throws ExecutionException, InterruptedException {
        Random random = new Random();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(2));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        });

        CompletableFuture<Object> result = CompletableFuture.anyOf(future1, future2);
        System.out.println(result.get());

    }

    /**
     * 任务交互：是指将两个线程任务获取结果的速度相比较，按一定的规则进行下一步处理
     * applyToEither: 两个线程任务相比较，先获得执行结果的，就对该结果进行下一步的转化操作。
     */
    private static void applyToEitherDemo() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int number = new Random().nextInt(10);
                System.out.println("第一阶段start：" + number);
                try {
                    TimeUnit.SECONDS.sleep(number);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("第一阶段end：" + number);
                return number;
            }
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int number = new Random().nextInt(10);
                System.out.println("第二阶段start：" + number);
                try {
                    TimeUnit.SECONDS.sleep(number);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("第二阶段end：" + number);
                return number;
            }
        });

        Integer result = future1.applyToEither(future2, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                System.out.println("最快结果：" + integer);
                return integer * 2;
            }
        }).join();

        System.out.println("最终结果：" + result);
    }

    /**
     * 合并两个线程任务的结果，并进一步处理
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void thenCombineDemo() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int number = new Random().nextInt(10);
                System.out.println("第一阶段：" + number);
                return number;
            }
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int number = new Random().nextInt(10);
                System.out.println("第二阶段：" + number);
                return number;
            }
        });

        CompletableFuture<Integer> result = future1.thenCombine(future2, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        });

        System.out.println("最终结果：" + result.get());
    }

    /**
     * thenRun 也是对线程任务结果的一种消费函数，与thenAccept不同的是，thenRun会在上一阶段
     * CompletableFuture 计算完成的时候执行一个 Runnable，Runnable 并不使用该
     * CompletableFuture 计算的结果。
     */
    private static void thenRunDemo() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(10);
            System.out.println("第一阶段：" + number);
            return number;
        }).thenRun(() -> System.out.println("thenRun 执行"));

        System.out.println("最终结果：" + future.get());

    }

    /**
     * 函数作用：当两个 CompletionStage 都正常完成计算的时候，就会执行提供的action消费两个异步的结果
     */
    private static void thenAcceptBothDemo() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int number = new Random().nextInt(3) + 1;
                try {
                    TimeUnit.SECONDS.sleep(number);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("第一阶段：" + number);
                return number;
            }
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int number = new Random().nextInt(3) + 1;
                try {
                    TimeUnit.SECONDS.sleep(number);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                int i = number / 0;
                System.out.println("第二阶段：" + number);
                return number;
            }
        });

        future.thenAcceptBoth(future2, new BiConsumer<Integer, Integer>() {
            @Override
            public void accept(Integer integer, Integer integer2) {
                System.out.println("最终结果：" + (integer + integer2));
            }
        }).join();
    }

    private static void thenAcceptDemo() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(10);
            System.out.println("第一阶段：" + number);
            return number;
        }).thenAccept(number -> {
            System.out.println("第二阶段：" + number * 5);
        });

        System.out.println("最终结果：" + future.get());
        // 第一阶段：8
        // 第二阶段：40
        // 最终结果：null
    }

    /**
     * thenApply和thenCompose的区别：
     * 1. thenApply 转换的是泛型中的类型，返回的是同一个 CompletableFuture;
     * 2. thenCompose 将内部的 CompletableFuture 调用展开来并使用上一个 CompletableFuture 调用的结果
     * 在下一步的 CompletableFuture 调用中进行运算，是生成一个新的CompletableFuture.
     */
    private static void diffBetweenThenArrayAndThenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<String> result = future.thenApply(param -> param + " World");
        System.out.println("result: " + result.get()); // result: Hello World

        CompletableFuture<String> result2 = future.thenCompose(param -> CompletableFuture.supplyAsync(() -> param + " Again!"));
        System.out.println("result2: " + result2.get()); // result2: Hello Again!
    }

    /**
     * thenCompose 的参数为一个返回 CompletableFuture 实例的函数，该函数的参数是先前计算步骤的结果
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void thenComposeDemo() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int number = new Random().nextInt(30);
                System.out.println("第一阶段：" + number);
                return number;
            }
        }).thenCompose(new Function<Integer, CompletionStage<Integer>>() {
            @Override
            public CompletionStage<Integer> apply(Integer integer) {
                return CompletableFuture.supplyAsync(new Supplier<Integer>() {
                    @Override
                    public Integer get() {
                        int number = integer * 2;
                        System.out.println("第二阶段：" + number);
                        return number;
                    }
                });
            }
        });

        System.out.println("最终结果：" + future.get());
    }

    /**
     * 结果转换：将上一段任务的执行结果作为下一阶段任务的入参参与重新计算，产生新的结果
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void thenApplyDemo() throws ExecutionException, InterruptedException {
        CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
            int result = 100;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("一阶段: " + result);
            return result;
        }).thenApply(number -> {
            int result = number * 3;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println("二阶段：" + result);
            return result > 0;
        });

        System.out.println("最终结果：" + future.get());
    }

    private static void whenCompleteExceptionallyDemo() {
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            if (new Random().nextInt(10) % 2 == 0) {
                int i = 12 / 0;
            }
            System.out.println("执行结束！");
            return "test";
        }).whenComplete(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String s, Throwable throwable) {
                System.out.println(s + "执行完成");
            }
        }).exceptionally(new Function<Throwable, String>() {
            @Override
            public String apply(Throwable throwable) {
                System.out.println("执行失败：" + throwable.getMessage());
                return "异常xxxx";
            }
        }).join();
    }


    private static void runSupplyAsyncDemo() throws ExecutionException, InterruptedException {
        Runnable runnable = () -> System.out.println("执行无返回结果的异步任务");
        CompletableFuture.runAsync(runnable);

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行有返回值的异步任务");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello World";
        });
        String result = future.get();
        System.out.println(result);
    }
}
