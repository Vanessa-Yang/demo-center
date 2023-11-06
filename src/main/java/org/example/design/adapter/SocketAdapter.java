package org.example.design.adapter;

/**
 * @Description We want to build an adapter that can product 3 volts, 12 volts
 * and default 120 volts.
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 10:02
 **/
public interface SocketAdapter {

    Volt get120Volt();

    Volt get12Volt();

    Volt get3Volt();

}
