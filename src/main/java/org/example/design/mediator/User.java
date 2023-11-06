package org.example.design.mediator;

/**
 * @Description Mediator Pattern Colleague Interface
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 18:02
 **/
public abstract class User {

    /**
     * Notice that User has a reference to the mediator object, it's required for the communication
     * between different uses.
     */
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void send(String msg);

    public abstract void receive(String msg);

}
