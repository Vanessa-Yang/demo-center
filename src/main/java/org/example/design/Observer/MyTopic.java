package org.example.design.Observer;

import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description Now our contract is ready, let's proceed with the concrete implementation of our topic.
 * @Author VanessaYang
 * @Date: 2023/4/10 0010 11:42
 **/
public class MyTopic implements Subject {

    private List<Observer> observers;
    private String message;
    private boolean changed;
    private final Object MUTEX = new Object();

    public MyTopic() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void register(Observer obj) {
        if (obj == null) throw new NullPointerException("Null Observer");
        synchronized (MUTEX) {
            if (!observers.contains(obj)) observers.add(obj);
        }
    }

    @Override
    public void unregister(Observer obj) {
        synchronized (MUTEX) {
            observers.remove(obj);
        }
    }

    @Override
    public void notifyObservers() {
        List<Observer> observerLocal = null;
        // synchronization is used to make sure any observer registered
        // after message is received is not notified
        synchronized (MUTEX) {
            if (!changed)
                return;
            observerLocal = new ArrayList<>(observers);
            this.changed = false;
        }
        for (Observer obj : observerLocal) {
            obj.update();
        }
    }

    @Override
    public Object getUpdate(Observer obj) {
        return this.message;
    }

    // method to post message to the topic
    public void postMessage(String msg) {
        System.out.println("Message Posted to Topic:" + msg);
        this.message = msg;
        this.changed = true;
        notifyObservers();
    }
}
