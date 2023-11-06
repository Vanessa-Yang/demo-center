package org.example.design.bridge;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 16:36
 **/
public class Pentagon extends Shape {
    public Pentagon(Color c) {
        super(c);
    }

    @Override
    public void applyColor() {
        System.out.println("Pentagon filled with color");
        color.applyColor();
    }
}
