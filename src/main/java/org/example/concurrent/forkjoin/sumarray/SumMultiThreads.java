package org.example.concurrent.forkjoin.sumarray;

import org.example.util.Utils;

/**
 * @Description 多线程计算1亿个整数的和
 * @Author VanessaYang
 * @Date: 2023/11/7 0007 15:12
 **/
public class SumMultiThreads {
    // 拆分的粒度
    public final static int NUM = 10_000_000;

    public static void main(String[] args) {
        // 准备数组
        int[] arr = Utils.buildRandomIntArray(100_000_000);
        // 获取线程数
        int numThreads = arr.length / NUM > 0 ? arr.length / NUM : 1;

    }
}
