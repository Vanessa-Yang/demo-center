package org.example.algorithms.learn;

/**
 * @Description 求数组小和
 * @Author VanessaYang
 * @Date: 2023/6/9 0009 10:15
 **/
public class SmallSum {

    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    // arr[L..R] 既要排好序，也要求小和返回
    // 所有merge时，产生的小和，累加
    // 左排序 merge
    // 右排序 merge
    // merge
    private static int process(int[] arr, int l, int r) {
        if (l == r) { // base case
            return 0;
        }
        // l < r
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid)
                + process(arr, mid + 1, r)
                + merge(arr, l, mid, r);
    }

    private static int merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        int minSum = 0;
        while (p1 <= mid && p2 <= r) {
            minSum += arr[p1] < arr[p2] ? arr[p1] * (r - p2 + 1) : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        // p1越界了，或者p2越界了
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        // 将辅助数组中排好序的数刷回原数组中对应位置
        for (int j = 0; j < help.length; j++) {
            arr[l + j] = help[j];
        }
        return minSum;
    }

}
