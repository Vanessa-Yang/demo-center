package org.example.algorithms.dynamic_planing;

/**
 * @Description 673. 最长递增子序列的个数
 * @Author VanessaYang
 * @Date: 2023/8/17 0017 9:19
 **/
public class Solution_FindNumberOfLIS {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 4, 7};
        System.out.println(findNumberOfLIS(nums));
    }

    /**
     * 方法一：序列DP
     * 通过在朴素LIS问题的基础上[记录额外信息]来进行求解
     * 定义 f[i] 为考虑以 nums[i] 为结尾的最长上升子序列的长度。最终答案为所有 f[0...(n-1)] 中的最大值
     * 1. 不失一般性地考虑 f[i] 该如何转移：
     * - 由于每个数都能独自一个成为子序列，因此起始必然有 f[i]=1;
     * - 枚举区间 [0,i) 的所有数 nums[i]，如果满足 nums[j] < nums[i], 说明 nums[i] 可以接在 nums[j] 后面形成上升子序列，
     * 此时使用 f[j] 更新 f[i], 即有 f[i] = f[j] + 1。
     * <p>
     * 由于我们需要求解的是最长上升子序列的个数，因此需要额外定义 g[i] 为考虑以 nums[i] 结尾的最长上升子序列的个数
     * 2. 结合 f[i] 的转移过程，不失一般性地考虑 g[i] 该如何转移：
     * - 同理，由于每个数都能独自一个成为子序列，因此起始必然有 g[i] = 1;
     * - 枚举区间 [0,i) 的所有数 nums[i]，如果满足 nums[j] < nums[i]，说明 nums[i] 可以接在 nums[j] 后面形成上升子序列，
     * 此时对 f[i] 和 f[j] + 1 的大小关系进行分情况讨论：
     * - - 满足 f[i] < f[j] + 1：说明 f[i] 会被 f[j] + 1 直接更新，此时同步直接更新 g[i] = g[j] 即可。
     * - - 满足 f[i] = f[j] + 1：说明找到了一个新的符合条件的前驱，此时将值继续累加到方案数当中，即有 g[i] += g[j];
     * 在转移过程中，我们可以同时记录全部最长上升子序列的最大长度 max，最终答案为所有满足 f[i] = max 的 g[i] 的累加值。
     * 3. 复杂度分析
     * - 时间复杂度：O(n^2)
     * - 空间复杂度：O(n)
     *
     * @param nums
     * @return
     */
    public static int findNumberOfLIS(int[] nums) {
        int n = nums.length, max = 0, ans = 0;
        int[] f = new int[n]; // f[i] 为以 nums[i] 结尾的最长子序列的长度
        int[] g = new int[n]; // g[i] 为以 nums[i] 结尾的最长子序列的个数

        for (int i = 0; i < n; i++) {
            f[i] = g[i] = 1;
            for (int j = 0; j < i; j++) {
                // 可以继续构成升序子序列
                if(nums[i] > nums[j]) {
                    if (f[i] < f[j] + 1) {
                        // 第一种情况，必然进来，更新f[i]的值为+1后
                        f[i] = f[j] + 1;
                        g[i] = g[j];
                    } else if (f[i] == f[j] + 1) {
                        // 新的情况，等价于第一种情况中f[j]的值，即与那时的f[j]并列的情形，则子序列个数累加
                        g[i] += g[j];
                    }
                }
            }
            // 更新最长子序列的长度
            max = Math.max(max, f[i]);
        }

        // 求满足 f[i] = max 的 g[i] 的累加和
        for (int i = 0; i < n; i++) {
            if (f[i] == max) {
                ans += g[i];
            }
        }
        return ans;
    }
}
