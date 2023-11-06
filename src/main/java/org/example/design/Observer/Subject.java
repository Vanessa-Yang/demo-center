package org.example.design.Observer;

/**
 * @Description Observer Pattern Java Example
 * We would implement a simple topic and observers can register to this topic.
 * Whenever any new message will be posted to the topic, all the registers observers
 * will be notified, they can consume the message.
 * @Author VanessaYang
 * @Date: 2023/4/10 0010 11:21
 **/
public interface Subject {

    // methods to register and unregister observers
    void register(Observer obj);

    void unregister(Observer obj);

    // method to notify observers of change
    void notifyObservers();

    // method to get updates from subject
    Object getUpdate(Observer obj);
}
