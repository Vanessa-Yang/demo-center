package org.example.algorithms.randomexercise;

import cn.hutool.core.lang.Assert;

import java.lang.annotation.Documented;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 随机刷题系列 - LeetCode热题100
 * @Author VanessaYang
 * @Date: 2023/10/16 0016 10:58
 **/
public class Solution20231016 {

    public static void main(String[] args) {
        // 560. 和为 K 的子数组
        int[] nums = new int[]{1, 1, 1};
        int k = 2; // 2
        System.out.println("560. 和为 K 的子数组(expect 2): " + subarraySum(nums, k));
        nums = new int[]{1, 2, 3};
        k = 3; // 2
        System.out.println("560. 和为 K 的子数组(expect 2): " + subarraySum(nums, k));
        nums = new int[]{1};
        k = 0; // 0
        System.out.println("560. 和为 K 的子数组(expect 0): " + subarraySumV2(nums, k));
        nums = new int[]{1, -1, 0};
        k = 0; // 3
        System.out.println("560. 和为 K 的子数组(expect 3): " + subarraySumV2(nums, k));

    }

    /**
     * 560. 和为 K 的子数组
     * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。
     * 子数组是数组中元素的连续非空序列。
     *
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum(int[] nums, int k) {
        // 暴力解法：穷举
        // 时间复杂度：O(n^2)
        // 空间复杂度：O(1)
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j >= 0; j--) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 560. 和为 K 的子数组
     * 解法二：前缀和 + 哈希表优化
     * 思路：
     * 定义 pre[i]为 [0...i] 里所有数的和，则 pre[i] 可以由 pre[i-1] 递推而来，即：
     * pre[i] = pre[i-1] + nums[i]
     * 那么“[j...i]这个子数组和为 k ”这个条件可以转换为
     * pre[i] - pre[j-1] = k
     * 简单移项可得符合条件的下标 j 需要满足：
     * pre[j-1] = pre[i] - k
     * 因此问题转化为统计有多少个前缀和为 pre[i] - k 的 pre[j] 即可。
     *
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySumV2(int[] nums, int k) {
        // 前缀和 + 哈希表优化
        // 时间复杂度 O(n)
        // 空间复杂度 O(n)。哈希表在最坏情况下可能有 n 个不同的键值，因此需要 O(n) 的空间复杂度
        int count = 0, pre = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}
