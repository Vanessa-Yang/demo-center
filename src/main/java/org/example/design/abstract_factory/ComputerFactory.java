package org.example.design.abstract_factory;

import org.example.design.model.Computer;

/**
 * @Description A consumer class that will provide the entry point for the client classes to create sub-classes.
 * @Author VanessaYang
 * @Date: 2023/4/6 0006 17:05
 **/
public class ComputerFactory {
    /**
     * Benifits:
     * 1. provides approach to code for interface rather than implementation.
     * 2. is "factory of factories" and can be easily extended to accommodate more products, for example we can add
     * another sub-class Laptop and a factory LaptopFactory.
     * 3. is robust and avoid conditional logic of Factory pattern.
     * @param factory
     * @return
     */
    public static Computer getComputer(ComputerAbstractFactory factory){
        return factory.createComputer();
    }
}
