package org.example.concurrent.forkjoin.sumarray;

import org.example.concurrent.forkjoin.sumarray.SumUtils;
import org.example.util.Utils;

import java.time.Duration;
import java.time.Instant;

/**
 * @Description 单线程计算1亿个整数的和
 * @Author VanessaYang
 * @Date: 2023/11/7 0007 14:55
 **/
public class SumSequential {

    public static void main(String[] args) {
        // 准备数组
        int[] arr = Utils.buildRandomIntArray(100_000_000);
        System.out.printf("The array lenth is: %d\n", arr.length);
        Instant now = Instant.now();
        // 数组求和
        long result = sum(arr);
        System.out.println("执行时间：" + Duration.between(now, Instant.now()).toMillis());

        System.out.printf("The result is: %d\n", result);
    }

    public static long sum(int[] arr) {
        return SumUtils.sumRange(arr, 0, arr.length);
    }
}
