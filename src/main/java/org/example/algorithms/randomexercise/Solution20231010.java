package org.example.algorithms.randomexercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 随机刷题系列 - LeetCode热题100
 * @Author VanessaYang
 * @Date: 2023/10/10 0010 14:30
 **/
public class Solution20231010 {

    public static void main(String[] args) {
        // 15. 三数之和
        int[] nums = {-1, 0, 1, 2, -1, -4}; // [[-1,-1,2],[-1,0,1]]
        System.out.println("三数之和: " + threeSum(nums));

        // 11. 盛最多水的容器
//        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7}; // 49
//        int[] height = {1, 1}; // 1
//        System.out.println("盛最多水的容器: " + maxArea(height));
    }

    /**
     * 15. 三数之和
     * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足
     * nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        // 解法一：排序 + 双指针
        // 思路分析：难点在于如何去除重复解（通过排序）
        // 1. 特判：对于数组长度 n, 如果数组为null或者数组长度小于3，返回[];
        // 2. 对数组进行排序 T(n) = O(nlogn);
        // 3. 遍历排序后数组：
        // ----> 若nums[i] > 0: 因为已经排好序，所以后面不可能有三数之和等于0，因此直接返回结果；
        // ----> 对于重复元素：跳过，避免出现重复解
        // ----> 令左指针 L = i+1，右指针 R = n-1，当 L < R 时，执行循环：
        // --------> 当 nums[i] + nums[L] + nums[R] = 0 时，执行循环，判断左界和有界是否和下一位置重复，去除重复解。
        // 并同时将L,R移动到下一位置，寻找新的解；
        // --------> 若和大于0，说明 nums[R] 太大，R左移；
        // --------> 若和小于0，说明 nums[L] 太小，L右移；
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3)
            return res;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int L = i + 1, R = nums.length - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    List<Integer> arr = new ArrayList<>();
                    arr.add(nums[i]);
                    arr.add(nums[L]);
                    arr.add(nums[R]);
                    res.add(arr);
                    while (L + 1 < R && nums[L + 1] == nums[L])
                        L++;
                    while (R - 1 > L && nums[R - 1] == nums[R])
                        R--;
                    L++;
                    R--;
                } else if (sum > 0) {
                    R--;
                } else {
                    L++;
                }
            }
        }
        return res;
    }

    /**
     * 11. 盛最多水的容器
     * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 返回容器可以储存的最大水量。
     * 说明：你不能倾斜容器。
     *
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int max = 0;
        // 方法二：双指针（更优）
        // 面积公式：s(i, j) = max(h[i], h[j]) * (j - i)
        // -> 状态分析：在每个状态下，无论长板或短板向中间收窄一格，都会导致水槽 “底边宽度” -1 变短
        // ----> 若向内 “移动短板”，水槽的短板 min(h[i], h[j]) 可能变大，因此下个水槽的面积 “可能增大”
        // ----> 若向内 “移动长板”，水槽的短板 min(h[i], h[j]) 可能不变或变小，因此下个水槽的面积 “一定变小”
        // -> 因此，初始化双指针分列水槽左右两端，循环每轮将短板向内移动一格，并更新面积的最大值，直到两指针相遇时跳出，即可获得最大面积。
        int lp = 0, rp = height.length - 1;
        while (lp < rp) {
            int curWidth = rp - lp;
            int curMinHeight = height[lp] < height[rp] ? height[lp++] : height[rp--];
            max = Math.max(max, curMinHeight * curWidth);
        }


//        // 方法一：穷举法，超时
//        for (int i = 0; i < height.length - 1; i++) {
//            for (int j = i + 1; j < height.length; j++) {
//
//                max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
//            }
//        }
        return max;
    }
}
