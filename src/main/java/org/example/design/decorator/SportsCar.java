package org.example.design.decorator;

/**
 * @Description Concrete Decorators - Extending the base decorator functionality and modifying
 * the component behavior accordingly.
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 17:10
 **/
public class SportsCar extends CarDecorator {

    public SportsCar(Car car) {
        super(car);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.print(" Adding features of Sports Car.");
    }
}
