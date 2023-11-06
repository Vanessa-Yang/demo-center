package org.example.design.composite;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 11:00
 **/
public class Circle implements Shape {

    @Override
    public void draw(String fillColor) {
        System.out.println("Draw Circle with color " + fillColor);
    }
}
