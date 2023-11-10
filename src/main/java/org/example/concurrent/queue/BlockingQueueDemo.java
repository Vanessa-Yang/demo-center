package org.example.concurrent.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.graphql.data.GraphQlQueryByExampleAutoConfiguration;

import java.util.concurrent.*;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/11/3 0003 14:26
 **/
@Slf4j
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        Executors.newCachedThreadPool();
//        testArrayBlockingQueue();
        testLinkedBlockingQueue();
    }

    private static void testLinkedBlockingQueue() {
        // 指定队列的大小创建有界队列
        BlockingQueue<Integer> boundedQueue = new LinkedBlockingQueue<>(100);
        // 无界队列
        BlockingQueue<Integer> unboundedQueue = new LinkedBlockingQueue<>();
    }

    private static void testArrayBlockingQueue() throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1024);
        queue.put("1");
        String take = queue.take();
        log.info("take: {}", take);
    }


}
