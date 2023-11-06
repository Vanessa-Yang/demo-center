package org.example.design.template;

/**
 * @Description Template Method Concrete Classes
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 17:38
 **/
public class WoodenHouse extends HouseTemplate {
    @Override
    public void buildWalls() {
        System.out.println("Building Wooden Walls");
    }

    @Override
    public void buildPillars() {
        // wood coating: 木器用涂料
        System.out.println("Building Pillars with Wood coating");
    }

    @Override
    protected void buildWindows() {
//        super.buildWindows();
        System.out.println("Building Wood Windows");
    }
}
