package org.example.design.mediator;

/**
 * @Description Mediator Design Pattern Concrete Colleague
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 18:13
 **/
public class UserImpl extends User {

    public UserImpl(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    /**
     * Notice that send() method is using mediator to send the message to the users and it has
     * no idea how it will be handled by the mediator.
     *
     * @param msg
     */
    @Override
    public void send(String msg) {
        System.out.println(this.name + ": Sending Message = " + msg);
        mediator.sendMessage(msg, this);
    }

    @Override
    public void receive(String msg) {
        System.out.println(this.name + ": Received Message: " + msg);

    }
}
