package org.example.algorithms.learn;

/**
 * @Description 归并排序
 * @Author VanessaYang
 * @Date: 2023/6/8 0008 17:30
 **/
public class MergeSort {

    // 递归方法实现 T(N) = O(N * logN)
    // merge() 复杂度为 O(logN)
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    // arr[L...R]范围上，变成有序的
    private static void process(int[] arr, int L, int R) {
        if (L == R) { // base case
            return;
        }
        int mid = L + ((R - L) >> 2);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 要么p1越界了，要么p2越界了
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        // 将有序后的辅助数组放回到原数组中对应位置
        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
    }


    // 非递归方法实现
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        int mergeSize = 1; // 当前有序的，左组长度
        while (mergeSize < N) {
            int L = 0;
            // 0......
            while (L < N) {
                // L..M 左组（mergeSize）
                int M = L + mergeSize - 1;
                if (M >= N) {
                    break;
                }
                int R = Math.min(M + mergeSize, N - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            // 下一步是mergeSize * 2 操作，防止溢出，做此判断
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

}
