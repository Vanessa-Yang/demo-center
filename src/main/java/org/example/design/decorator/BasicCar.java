package org.example.design.decorator;

/**
 * @Description Component Implementation  - The basic implementation of the component interface.
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 17:05
 **/
public class BasicCar implements Car {
    @Override
    public void assemble() {
        System.out.print("Basic Car.");
    }
}
