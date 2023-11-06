package org.example.design.decorator;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 17:18
 **/
public class LuxuryCar extends CarDecorator {

    public LuxuryCar(Car car) {
        super(car);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.print(" Adding features of Luxury Car.");
    }
}
