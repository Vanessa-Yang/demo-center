package org.example.design.strategy;

/**
 * @Description Now we will have to create concrete implementation of algorithms
 * for payment using credit/debit card or through paypal.
 * @Author VanessaYang
 * @Date: 2023/4/10 0010 14:12
 **/
public class CreditCardStrategy implements PaymentStrategy {
    private String name;
    private String cardNumber;
    private String cvv;
    private String dataOfExpiry;

    public CreditCardStrategy(String name, String cardNumber, String cvv, String dataOfExpiry) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.dataOfExpiry = dataOfExpiry;
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid with credit/debit card");
    }
}
