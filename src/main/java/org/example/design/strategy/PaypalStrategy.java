package org.example.design.strategy;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/4/10 0010 14:15
 **/
public class PaypalStrategy implements PaymentStrategy {
    private String emailId;
    private String password;

    public PaypalStrategy(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid using Paypal.");
    }
}
