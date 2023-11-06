package org.example.design.bridge;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 16:37
 **/
public class GreenColor implements Color{
    @Override
    public void applyColor() {
        System.out.println("green");
    }
}
