package org.example.design.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description Composite Object: A composite object contains group of leaf objects and
 * we should provide some helper methods tp add or delete leafs from the group.
 *
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 11:02
 **/
public class Drawing implements Shape {
    // collection of Shapes
    private List<Shape> shapes = new ArrayList<>();

    @Override
    public void draw(String fillColor) {
        for (Shape sh : shapes) {
            sh.draw(fillColor);
        }
    }

    // adding shape to drawing
    public void add(Shape s) {
        shapes.add(s);
    }

    // removing shape from drawing
    public void remove(Shape s) {
        shapes.remove(s);
    }

    public void clear() {
        System.out.println("Clearing all the shapes from drawing");
        shapes.clear();
    }
}
