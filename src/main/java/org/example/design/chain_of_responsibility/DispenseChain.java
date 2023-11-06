package org.example.design.chain_of_responsibility;

/**
 * @Description The base interface should have a method to define the next processor in the chain
 * and the method that will process the request.
 * @Author VanessaYang
 * @Date: 2023/4/10 0010 9:36
 **/
public interface DispenseChain {

    void setNextChain(DispenseChain nextChain);

    void dispense(Currency currency);
}
