package org.example.algorithms.dynamic_planing;

import org.springframework.util.Assert;

import java.util.Map;

/**
 * @Description 最长递增子序列
 * @Author VanessaYang
 * @Date: 2023/6/8 0008 10:52
 **/
public class LongestIncreasingSubsequence {

    /**
     * 300. 最长递增子序列
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
     * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     *
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        int len = 1, prevLen, prev;
        for (int i = 1; i < nums.length - len + 1; i++) {
            System.out.println("len=" + len + "  minus=" + (nums.length - len + 1));
            prev = nums[i - 1];
            prevLen = 1;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] > prev) {
                    prevLen++;
                    len = Math.max(prevLen, len);
                    prev = nums[j];
                }
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 2, 3};
        System.out.println(lengthOfLIS(nums) + "  4");


        nums = new int[]{-2, -1};
        System.out.println(lengthOfLIS(nums) + "  2");

        nums = new int[]{7, 7, 7, 7, 7, 7, 7};
        System.out.println(lengthOfLIS(nums) + "  1");
    }
}
