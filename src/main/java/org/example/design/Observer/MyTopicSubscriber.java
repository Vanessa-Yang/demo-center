package org.example.design.Observer;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/4/10 0010 13:42
 **/
public class MyTopicSubscriber implements Observer {
    private String name;
    private Subject topic;

    public MyTopicSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update() {
        // Notice the implementation of update() method where it's calling Subject
        // getUpdate() method to get the message to consume. We could have avoided
        // this call by passing message as argument to update() method.
        String msg = (String) topic.getUpdate(this);
        if (msg == null) {
            System.out.println(name + ":: No new message");
        } else
            System.out.println(name + ":: Consuming message::" + msg);
    }

    @Override
    public void setSubject(Subject sub) {
        this.topic = sub;
    }
}
