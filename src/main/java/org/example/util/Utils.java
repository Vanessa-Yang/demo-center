package org.example.util;

import java.util.Random;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/11/7 0007 14:59
 **/
public class Utils {
    public static int[] buildRandomIntArray(final int size) {
        int[] arrayToCalculateSumOf = new int[size];
        Random generator = new Random();
        for (int i = 0; i < arrayToCalculateSumOf.length; i++) {
            arrayToCalculateSumOf[i] = generator.nextInt(1000);
        }
        return arrayToCalculateSumOf;
    }
}
