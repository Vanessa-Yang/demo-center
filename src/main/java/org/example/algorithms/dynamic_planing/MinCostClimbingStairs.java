package org.example.algorithms.dynamic_planing;

import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 746. 使用最小花费爬楼梯
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 * 请你计算并返回达到楼梯顶部的最低花费。
 * @Author VanessaYang
 * @Date: 2023/5/6 0006 9:51
 **/
public class MinCostClimbingStairs {

    /**
     * 740. 删除并获得点数
     * 给你一个整数数组 nums ，你可以对它进行一些操作。
     * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。
     * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
     *
     * @param nums
     * @return
     */
    public static int deleteAndEarn(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int maxNum = nums[0];
        for (int num : nums) {
            maxNum = Math.max(num, maxNum);
        }
        int[] all = new int[maxNum + 1];
        for (int num : nums) {
            all[num] += 1;
        }
        // 数组all符合“打家劫舍”问题的最优子结构公式，可得该问题的状态转移方程式为:
        // dp[i] = Math.maxNum(dp[i - 1], dp[i - 2] + i * all[i])
        int pre_far = all[0];
        int pre_near = Math.max(all[0], all[1]);
        for (int i = 2; i < all.length; i++) {
            int maxEarn = Math.max(pre_far + i * all[i], pre_near);
            pre_far = pre_near;
            pre_near = maxEarn;
        }
        return pre_near;
    }

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额
     *
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[length - 1];
    }

    public static int rob_v2(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int pre_far = nums[0];
        int pre_near = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            int maxRob = Math.max(pre_far + nums[i], pre_near);
            pre_far = pre_near;
            pre_near = maxRob;
        }
        return pre_near;
    }

    /**
     * 动态规划：状态转移方程
     *
     * @param cost
     * @return
     */
    public static int minCostClimbingStairs(int[] cost) {
        if (cost.length < 2) {
            return 0;
        }
        int[] dp = new int[cost.length + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[dp.length - 1];
    }

    /**
     * 动态规划：滑动数组思想
     *
     * @param cost
     * @return
     */
    public static int minCostClimbingStairs_v2(int[] cost) {
        if (cost.length < 2) {
            return 0;
        }
        int a, b = 0, c = 0;
        for (int i = 2; i < (cost.length + 1); i++) {
            a = b;
            b = c;
            c = Math.min(a + cost[i - 2], b + cost[i - 1]);
        }
        return c;
    }

    public static void main(String[] args) {
//        int[] cost = new int[]{1, 100};
//        int minCost = minCostClimbingStairs(cost);
//        System.out.println(minCost);

//        int[] nums = {2, 1, 1, 2};
//        int rob = rob(nums);
//        System.out.println("rob = " + rob);

        int[] nums = {3, 4, 2};
        int earn = deleteAndEarn(nums);
        System.out.println("earn = " + earn);

    }
}
