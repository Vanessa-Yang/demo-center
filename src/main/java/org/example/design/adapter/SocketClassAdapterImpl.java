package org.example.design.adapter;

/**
 * @Description Class Adapter: using inheritance for adapter pattern
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 10:31
 **/
public class SocketClassAdapterImpl extends Socket implements SocketAdapter {

    @Override
    public Volt get120Volt() {
        return getVolt();
    }

    @Override
    public Volt get12Volt() {
        Volt volt = getVolt();
        return convertVolt(volt, 10);
    }

    @Override
    public Volt get3Volt() {
        Volt volt = getVolt();
        return convertVolt(volt, 40);
    }

    private Volt convertVolt(Volt volt, int divide) {
        return new Volt(volt.getVolts() / divide);
    }
}
