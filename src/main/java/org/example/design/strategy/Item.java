package org.example.design.strategy;

/**
 * @Description Now our strategy pattern example algorithms are ready.We can implement
 * Shopping Cart and payment method will require input as Payment strategy.
 * @Author VanessaYang
 * @Date: 2023/4/10 0010 14:16
 **/
public class Item {

    private String upcCode;
    private int price;

    public Item(String upcCode, int price) {
        this.upcCode = upcCode;
        this.price = price;
    }

    public String getUpcCode() {
        return upcCode;
    }

    public int getPrice() {
        return price;
    }
}
