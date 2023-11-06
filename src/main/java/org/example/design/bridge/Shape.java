package org.example.design.bridge;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 16:04
 **/
public abstract class Shape {
    // Composition  - implementor
    protected Color color;

    // constructor with implementor as input argument
    public Shape(Color c) {
        this.color = c;
    }

    abstract public void applyColor();
}
