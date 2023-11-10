package org.example.concurrent.forkjoin.sumarray;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/11/7 0007 15:07
 **/
public class SumUtils {

    /**
     * 数组求和
     *
     * @param arr
     * @param lo
     * @param hi
     * @return
     */
    public static long sumRange(int[] arr, int lo, int hi) {
        long result = 0;

        for (int i = lo; i < hi; i++) {
            result += arr[i];
        }
        return result;
    }
}
