package org.example.design.test;

import org.example.design.chain_of_responsibility.*;

import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * @Description Chain of Responsibilities Design Pattern - Creating the Chain
 * Note: This is very important step and we should create the chain carefully, otherwise
 * a processor might not be getting any request at all.
 * @Author VanessaYang
 * @Date: 2023/4/10 0010 10:03
 **/
public class ATMDispenseChain {
    private DispenseChain c1;

    public ATMDispenseChain() {
        // initialize the chain
        this.c1 = new Dollar50Dispenser();
        DispenseChain c2 = new Dollar20Dispenser();
        DispenseChain c3 = new Dollar10Dispenser();

        // set the chain of responsibility
        c1.setNextChain(c2);
        c2.setNextChain(c3);
    }

    public static void main(String[] args) {
        ATMDispenseChain atmDispenser = new ATMDispenseChain();
        while (true) {
            int amount = 0;
            System.out.println("Enter amount to dispense");
            Scanner input = new Scanner(System.in);
            amount = input.nextInt();
            if (amount % 10 != 0) {
                System.out.println("Amount should be in multiple of 10s.");
                return;
            }
            // process the request
            atmDispenser.c1.dispense(new Currency(amount));
        }
    }
}
