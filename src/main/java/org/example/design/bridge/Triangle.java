package org.example.design.bridge;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 16:33
 **/
public class Triangle extends Shape {
    public Triangle(Color c) {
        super(c);
    }

    @Override
    public void applyColor() {
        System.out.println("Triangle filled with color");
        color.applyColor();
    }
}
