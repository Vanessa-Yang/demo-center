package org.example.design.bridge;

/**
 * @Description Bridge Design Pattern:
 * | Decouple an abstraction from its implementation so that the two can vary independently.
 * The implementation of bridge design pattern follows the notion to prefer `Composition` over `Inheritance`.
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 15:58
 **/
public interface Color {
    void applyColor();
}
