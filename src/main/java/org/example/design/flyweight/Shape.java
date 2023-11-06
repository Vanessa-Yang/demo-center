package org.example.design.flyweight;

import java.awt.*;

/**
 * @Description Flyweight Design pattern intent is:
 * | Use sharing to support large numbers of fine-grained objects efficiently.
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 13:56
 **/
public interface Shape {

    void draw(Graphics g, int x, int y, int width, int height, Color color);

}
