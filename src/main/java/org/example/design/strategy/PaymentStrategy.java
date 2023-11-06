package org.example.design.strategy;

/**
 * @Description Strategy pattern is also known as Policy Pattern. we define multiple
 * algorithms and let client application pass the algorithm to be used as a parameter.
 * One of the best example of strategy pattern is `Collections.sort()` method that takes
 * Comparator parameter.
 * @Author VanessaYang
 * @Date: 2023/4/10 0010 14:08
 **/
public interface PaymentStrategy {
    void pay(int amount);
}
