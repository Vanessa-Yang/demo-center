package org.example.design.adapter;

/**
 * @Description Adapter Design Pattern: mobile charger works as an adapter between mobile
 * charging socket and the wall socket.
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 9:57
 **/
public class Volt {
    private int volts;

    public Volt(int volts) {
        this.volts = volts;
    }

    public int getVolts() {
        return volts;
    }

    public void setVolts(int volts) {
        this.volts = volts;
    }
}
