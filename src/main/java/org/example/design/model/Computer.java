package org.example.design.model;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/4/6 0006 14:08
 **/
public abstract class Computer {

    public abstract String getRAM();

    public abstract String getHDD();

    public abstract String getCPU();

    @Override
    public String toString() {
        return "RAM = " + this.getRAM() + ", HDD = " + this.getHDD() + ", CPU = " + this.getCPU();
    }
}
