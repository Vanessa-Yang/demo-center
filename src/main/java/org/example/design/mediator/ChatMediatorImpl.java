package org.example.design.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description ConcreteMediator
 * It will have a list of users in the group and provide logic for the communication
 * between the users.
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 18:05
 **/
public class ChatMediatorImpl implements ChatMediator {

    private List<User> users;

    public ChatMediatorImpl() {
        this.users = new ArrayList<>();
    }

    @Override
    public void sendMessage(String msg, User user) {
        for (User u : this.users) {
            // message should not be received by the user sending it
            if (u != user) {
                u.receive(msg);
            }

        }
    }

    @Override
    public void addUser(User user) {
        this.users.add(user);
    }
}
