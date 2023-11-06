package org.example.design.flyweight;

import javax.sql.rowset.serial.SerialJavaObject;
import java.util.HashMap;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 14:27
 **/
public class ShapeFactory {
    private static final HashMap<ShapeType, Shape> shapes = new HashMap<>();

    public static Shape getShape(ShapeType type) {
        Shape shapeImpl = shapes.get(type);

        if (shapeImpl == null) {
            if (type.equals(ShapeType.OVAL_FILL)) {
                shapeImpl = new Oval(true);
            } else if (type.equals(ShapeType.OVAL_NO_FILL)) {
                shapeImpl = new Oval(false);
            } else if (type.equals(ShapeType.LINE)) {
                shapeImpl = new Line();
            }
            shapes.put(type, shapeImpl);
        }
        return shapeImpl;
    }

    public enum ShapeType {
        OVAL_FILL, OVAL_NO_FILL, LINE;
    }
}
