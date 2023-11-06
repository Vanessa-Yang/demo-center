package org.example.design.factory;

import org.example.design.model.Computer;
import org.example.design.model.PC;
import org.example.design.model.Server;

/**
 * @Description Factory class
 * @Author VanessaYang
 * @Date: 2023/4/6 0006 14:16
 **/
public class ComputerFactory {

    /**
     * factory method
     *
     * @param type
     * @param ram
     * @param hdd
     * @param cpu
     * @return
     */
    public static Computer getComputer(String type, String ram, String hdd, String cpu) {
        if ("PC".equalsIgnoreCase(type))
            return new PC(ram, hdd, cpu);
        else if ("Server".equalsIgnoreCase(type))
            return new Server(ram, hdd, cpu);
        return null;
    }
}
