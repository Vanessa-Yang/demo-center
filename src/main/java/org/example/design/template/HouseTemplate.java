package org.example.design.template;

/**
 * @Description Template Method Abstract Class
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 17:31
 **/
public abstract class HouseTemplate {

    /**
     * template method, final so subclasses can't override
     * `buildHouse()` is the template method and defines the order of execution for performing
     * several steps.
     */
    public final void buildHouse() {
        buildFoundation();
        buildPillars();
        buildWalls();
        buildWindows();
        System.out.println("House is built.");
    }

    // default implementation
    protected void buildWindows() {
        System.out.println("Building Glass Windows");
    }


    // methods to be implemented by subclasses
    public abstract void buildWalls();

    public abstract void buildPillars();

    protected final void buildFoundation() {
        System.out.println("Building foundation with cement, iron rods and sand");
    }
}
