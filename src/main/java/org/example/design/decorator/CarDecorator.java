package org.example.design.decorator;

/**
 * @Description Decorator class implements the component interface and it has a HAS-A relationship
 * with the component interface. The component variable should be accessible to the child decorator
 * classes, so we will make this variable protected.
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 17:07
 **/
public class CarDecorator implements Car {
    protected Car car;

    public CarDecorator(Car car) {
        this.car = car;
    }

    @Override
    public void assemble() {
        this.car.assemble();
    }
}
