package org.example.concurrent.queue;

import cn.hutool.core.lang.Assert;
import com.google.common.primitives.Ints;

import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 延迟队列的使用样例
 * @Author VanessaYang
 * @Date: 2023/11/6 0006 18:10
 **/
public class DelayQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        givenDelayQueue_whenProduceElement_thenShouldConsumeAfterGivenDalay();
        // print after execution:
        // Put object: org.example.concurrent.queue.DelayObject@75310f69
        // Consumer take: org.example.concurrent.queue.DelayObject@75310f69
        // Put object: org.example.concurrent.queue.DelayObject@ccca8a8
        // Consumer take: org.example.concurrent.queue.DelayObject@ccca8a8
    }


    /**
     * DelayQueue Usage Test
     */
    public static void givenDelayQueue_whenProduceElement_thenShouldConsumeAfterGivenDalay() throws InterruptedException {
        // given
        ExecutorService executor = Executors.newFixedThreadPool(2);

        BlockingQueue<DelayObject> queue = new DelayQueue<>();
        // 生产的消息数
        int numberOfElementsToProduce = 2;
        // 消息延迟消费的毫秒值：
        // 传正数，代表生产消息后消费者间隔多少毫秒后消费该消息；
        // 传负数，代表即时消费，无延迟。
        int delayOfEachProducedMessageMilliseconds = 500;

        DelayQueueConsumer consumer = new DelayQueueConsumer(queue, numberOfElementsToProduce);
        DelayQueueProducer producer = new DelayQueueProducer(queue, numberOfElementsToProduce, delayOfEachProducedMessageMilliseconds);

        // when
        executor.submit(producer);
        executor.submit(consumer);

        // then
        executor.awaitTermination(5, TimeUnit.SECONDS);
        executor.shutdown();
        Assert.equals(consumer.numberOfConsumedElements.get(), numberOfElementsToProduce);
    }
}

/**
 * The consumer implementation is very similar, but it also keeps track of the number of
 * messages that were consumed.
 */
class DelayQueueConsumer implements Runnable {
    private BlockingQueue<DelayObject> queue;
    private Integer numberOfElementsToTake;
    public AtomicInteger numberOfConsumedElements = new AtomicInteger();

    // standard constructors
    public DelayQueueConsumer(BlockingQueue<DelayObject> queue, Integer numberOfElementsToTake) {
        this.queue = queue;
        this.numberOfElementsToTake = numberOfElementsToTake;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfElementsToTake; i++) {
            try {
                DelayObject object = queue.take();
                numberOfConsumedElements.incrementAndGet();
                System.out.println("Consumer take: " + object);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * Producer：produce message
 */
class DelayQueueProducer implements Runnable {

    private BlockingQueue<DelayObject> queue;
    private Integer numberOfElementsToProduce;
    private Integer delayOfEachProducedMessageMilliseconds;

    // standard constructor
    public DelayQueueProducer(BlockingQueue<DelayObject> queue, Integer numberOfElementsToProduce, Integer delayOfEachProducedMessageMilliseconds) {
        this.queue = queue;
        this.numberOfElementsToProduce = numberOfElementsToProduce;
        this.delayOfEachProducedMessageMilliseconds = delayOfEachProducedMessageMilliseconds;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfElementsToProduce; i++) {
            DelayObject object = new DelayObject(UUID.randomUUID().toString(), delayOfEachProducedMessageMilliseconds);
            System.out.println("Put object: " + object);

            try {
                queue.put(object);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

/**
 * 声明一个延时对象
 */
class DelayObject implements Delayed {
    private String data;
    private long startTime;

    public DelayObject(String data, long delayInMilliseconds) {
        this.data = data;
        this.startTime = System.currentTimeMillis() + delayInMilliseconds;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }


    /**
     * The item what will expire first is kept at the head of the queue and the element
     * with the highest expiration time is kept at the tail of the queue.
     *
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        return Ints.saturatedCast(this.startTime - ((DelayObject) o).startTime);
    }
}