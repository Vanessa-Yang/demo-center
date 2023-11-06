package org.example.design.template;

/**
 * @Description Template Method Concrete Classes
 * We could have overridden other methods also, but for simplicity I am not doing that.
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 17:41
 **/
public class GlassHouse extends HouseTemplate {
    @Override
    public void buildWalls() {
        System.out.println("Building Glass Walls");
    }

    @Override
    public void buildPillars() {
        System.out.println("Building Pillars with glass coating");
    }
}
