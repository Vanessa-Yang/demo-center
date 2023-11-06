package org.example.design.visitor;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/4/11 0011 13:54
 **/
public class Book implements ItemElement{
    private int price;
    private String isbnNumber;

    public Book(int price, String isbnNumber) {
        this.price = price;
        this.isbnNumber = isbnNumber;
    }

    public int getPrice() {
        return price;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }


    @Override
    public int accept(ShoppingCartVisitor visitor) {
       return visitor.visit(this);
    }
}
