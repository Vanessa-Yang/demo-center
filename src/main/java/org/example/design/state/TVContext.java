package org.example.design.state;

/**
 * @Description State Design Pattern Context Implementation
 * Notice that Context also implements State and keep a reference of its current state and
 * forwards the request to the state implementation.
 * @Author VanessaYang
 * @Date: 2023/4/11 0011 13:45
 **/
public class TVContext implements State {
    private State tvState;

    public void setTvState(State tvState) {
        this.tvState = tvState;
    }

    public State getTvState() {
        return tvState;
    }

    @Override
    public void doAction() {
        this.tvState.doAction();
    }
}
