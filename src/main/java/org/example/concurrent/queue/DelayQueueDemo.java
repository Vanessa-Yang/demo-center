package org.example.concurrent.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/11/6 0006 18:10
 **/
public class DelayQueueDemo {

}

class DelayObject implements Delayed{
    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}