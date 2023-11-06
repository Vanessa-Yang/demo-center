package org.example.design.abstract_factory;

import org.example.design.model.Computer;
import org.example.design.model.PC;

import java.sql.JDBCType;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/4/6 0006 16:37
 **/
public class PCFactory implements ComputerAbstractFactory {

    private String ram;
    private String hdd;
    private String cpu;

    public PCFactory(String ram, String hdd, String cpu) {
        this.ram = ram;
        this.hdd = hdd;
        this.cpu = cpu;
    }

    @Override
    public Computer createComputer() {
        return new PC(ram, hdd, cpu);
    }
}
