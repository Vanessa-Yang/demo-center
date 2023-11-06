package org.example.design.chain_of_responsibility;

/**
 * @Description Chain of Responsibilities Pattern - Chain Implementations
 * We are developing our system to work with three types of currency bills - 50$, 20$ and 10$.
 * We need to create three kinds of processor classes that will implement the `DsipenseChain`
 * interface and provide implementation  of dispense methods.
 * @Author VanessaYang
 * @Date: 2023/4/10 0010 9:39
 **/
public class Dollar50Dispenser implements DispenseChain {
    private DispenseChain chain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain = nextChain;
    }

    /**
     * Note: The important point is the implementation of dispense method. Every implementation
     * is trying to process the request and based on the amount, it might process some or full
     * part of it. If one of the chain not able to process it fully, it sends the request to
     * the next processor in chain to process the remaining request. If the processor is not
     * able to process anything, it just forwards the same request to the next chain.
     *
     * @param currency
     */
    @Override
    public void dispense(Currency currency) {
        if (currency.getAmount() >= 50) {
            int num = currency.getAmount() / 50;
            int remainder = currency.getAmount() % 50;
            System.out.println("Dispensing " + num + " 50$ note");
            if (remainder != 0) this.chain.dispense(new Currency(remainder));
        } else {
            this.chain.dispense(currency);
        }
    }
}
