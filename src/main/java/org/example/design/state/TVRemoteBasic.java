package org.example.design.state;

/**
 * @Description use if-else condition block to perform different actions based on the state.
 * @Author VanessaYang
 * @Date: 2023/4/10 0010 16:58
 **/
public class TVRemoteBasic {

    private String state = "";

    public void setState(String state) {
        this.state = state;
    }

    public void doAction(){
        if("ON".equalsIgnoreCase(state)){
            System.out.println("TV is turned ON");
        }else if ("OFF".equalsIgnoreCase(state)){
            System.out.println("TV is turned OFF");
        }
    }

    public static void main(String[] args) {
        TVRemoteBasic remote = new TVRemoteBasic();

        remote.setState("ON");
        remote.doAction();

        remote.setState("OFF");
        remote.doAction();
    }
}
