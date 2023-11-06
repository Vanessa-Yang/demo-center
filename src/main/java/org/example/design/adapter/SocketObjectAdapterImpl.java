package org.example.design.adapter;

import java.awt.image.ConvolveOp;

/**
 * @Description Adapter Design Pattern - Object Adapter Implementation
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 10:35
 **/
public class SocketObjectAdapterImpl implements SocketAdapter {

    // Using Composition for adapter pattern
    private Socket socket = new Socket();

    @Override
    public Volt get120Volt() {
        return socket.getVolt();
    }

    @Override
    public Volt get12Volt() {
        Volt volt = socket.getVolt();
        return convertVolt(volt, 10);
    }

    @Override
    public Volt get3Volt() {
        Volt volt = socket.getVolt();
        return convertVolt(volt, 40);
    }

    private Volt convertVolt(Volt volt, int divide) {
        return new Volt(volt.getVolts() / divide);
    }
}
