package org.example.design.chain_of_responsibility;

/**
 * @Description Create a class `Currency` that will store the amount to dispense and
 * used by the chain implementations.
 * @Author VanessaYang
 * @Date: 2023/4/10 0010 9:18
 **/
public class Currency {
    private int amount;

    public Currency(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
