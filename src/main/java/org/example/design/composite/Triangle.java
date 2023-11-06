package org.example.design.composite;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 10:59
 **/
public class Triangle implements Shape {
    @Override
    public void draw(String fillColor) {
        System.out.println("Drawing Triangle with color " + fillColor);
    }
}
