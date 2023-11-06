package org.example.algorithms.learn;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/6/9 0009 11:16
 **/
public class PartitionAndQuickSort {

    // 荷兰国旗问题，返回值长度为2的数组，其中[0]是等于区的左边界，[1]是等于区的右边界
    // arr[L..R] 玩荷兰国旗问题的划分，以arr[R]做划分值
    // <arr[R]   == arr[R]   >arr[R]
    public static int[] netherLandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1; // < 区 右边界
        int more = R; // > 区 左边界
        int index = L;
        while (index < R) {
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        // L...less more-1 more...R
        // 将R移到 >区的左边界 上：L...less more more+1...R
        swap(arr, more, R);
        return new int[]{less + 1, more};

    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
