package org.example.design.mediator;


/**
 * @Description Mediator Pattern Interface
 * define the contract for concrete mediators
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 18:00
 **/
public interface ChatMediator {

    void sendMessage(String msg, User user);

    void addUser(User user);
}