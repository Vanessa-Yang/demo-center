package org.example.algorithms.dynamic_planing;

import java.util.Arrays;

/**
 * @Description 1027. 最长等差数列
 * 给你一个整数数组 nums，返回 nums 中最长等差子序列的长度。
 * 回想一下，nums 的子序列是一个列表 nums[i1], nums[i2], ..., nums[ik] ，且 0 <= i1 < i2 < ... < ik <= nums.length - 1。
 * 并且如果 seq[i+1] - seq[i]( 0 <= i < seq.length - 1) 的值都相同，那么序列 seq 是等差的。
 * <p>
 * 提示：
 * 2 <= nums.length <= 1000
 * 0 <= nums[i] <= 500
 * @Author VanessaYang
 * @Date: 2023/8/25 0025 15:50
 **/
public class Solution_LongestArithSeqLength {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 6, 9, 12};
        System.out.println(longestArithSeqLength(nums));

        nums = new int[]{9, 4, 7, 2, 10};
        System.out.println(longestArithSeqLength(nums));

        nums = new int[]{20, 1, 15, 3, 10, 5, 8};
        System.out.println(longestArithSeqLength(nums));
    }

    public static int longestArithSeqLength(int[] nums) {
        int maxV = Arrays.stream(nums).max().getAsInt();
        int minV = Arrays.stream(nums).min().getAsInt();
        int diff = maxV - minV;
        int ans = 1;
        // 遍历等差的所有可能步长
        for (int d = -diff; d <= diff; d++) {
            // nums[i]作为dp下标，dp[nums[i]]代表以nums[i]结尾等差为d的子序列的长度，
            // 初始化dp元素为-1，代表无效
            int[] dp = new int[maxV + 1];
            Arrays.fill(dp, -1);

            for (int num : nums) {
                if (num - d >= minV && num - d <= maxV && dp[num - d] != -1) {
                    // 存在有效的前一个等差数
                    dp[num] = Math.max(dp[num], dp[num - d] + 1);
                }
                // 不存在：长度为1
                dp[num] = Math.max(dp[num], 1);
                ans = Math.max(ans, dp[num]);
            }
        }
        return ans;
    }
}
