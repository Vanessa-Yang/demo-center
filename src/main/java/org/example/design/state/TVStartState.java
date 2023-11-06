package org.example.design.state;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/4/11 0011 13:43
 **/
public class TVStartState implements State {

    @Override
    public void doAction() {
        System.out.println("TV is turned ON");
    }
}
