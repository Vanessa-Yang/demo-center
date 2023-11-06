package org.example.design.abstract_factory;

import org.example.design.model.Computer;
import org.example.design.model.Server;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/4/6 0006 17:03
 **/
public class ServerFactory implements ComputerAbstractFactory{
    private String ram;
    private String hdd;
    private String cpu;

    public ServerFactory(String ram, String hdd, String cpu) {
        this.ram = ram;
        this.hdd = hdd;
        this.cpu = cpu;
    }

    @Override
    public Computer createComputer() {
        return new Server(ram, hdd, cpu);
    }
}
