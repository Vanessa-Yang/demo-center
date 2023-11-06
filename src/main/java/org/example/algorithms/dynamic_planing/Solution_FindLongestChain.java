package org.example.algorithms.dynamic_planing;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Description 646. 最长数对链
 * 给你一个由 n 个数对组成的数对数组 pairs ，其中 pairs[i] = [lefti, righti] 且 lefti < righti 。
 * 现在，我们定义一种 跟随 关系，当且仅当 b < c 时，数对 p2 = [c, d] 才可以跟在 p1 = [a, b] 后面。我们用这种形式来构造 数对链 。
 * 找出并返回能够形成的 最长数对链的长度 。
 * 你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 * @Author VanessaYang
 * @Date: 2023/8/17 0017 13:43
 **/
public class Solution_FindLongestChain {

    public static void main(String[] args) {
        int[][] pairs = new int[][]{{1, 2}, {2, 3}, {3, 4}};
        System.out.println(findLongestChain(pairs));
        System.out.println(findsLongestChain_v2(pairs));
        System.out.println(findsLongestChain_v3(pairs));
    }

    /**
     * 方法三：贪心
     *
     * @param pairs
     * @return
     */
    public static int findsLongestChain_v3(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(pair -> pair[1]));
        int cur = Integer.MIN_VALUE, len = 0;
        for (int[] pair : pairs) {
            if (cur < pair[0]) {
                cur = pair[1];
                len += 1;
            }
        }
        return len;
    }

    /**
     * 方法二：最长递增子序列
     * 排序 + 二分查找 + 有序插入
     *
     * @param pairs
     * @return
     */
    public static int findsLongestChain_v2(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(pair -> pair[0])); // T(n) = O(nlogn)
        int n = pairs.length;
        List<Integer> dp = new ArrayList<>(n); // dp[i] 表示长度为 i+1 的数对链的末尾可以取得的最小值
        for (int[] pair : pairs) {
            if (dp.isEmpty() || pair[0] > dp.get(dp.size() - 1)) {
                dp.add(pair[1]);
            } else if (pair[0] <= dp.get(dp.size() - 1)) {
                int idx = binary_search_left(dp, pair[1]); // 二分查找比pair[1]小的最大值
                dp.set(idx, Math.min(dp.get(idx), pair[1]));
            }
        }
        return dp.size();
    }

    /**
     * 二分查找比 target 小的最大值
     * 1,3,6,7,8,9  5
     * 0,1,2,3,4,5
     *
     * @param dp
     * @param target
     * @return
     */
    private static int binary_search_left(List<Integer> dp, int target) {
        int l = 0, r = dp.size() - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (dp.get(mid) > target) {
                r = mid;
            } else if (dp.get(mid) < target) {
                l = mid + 1;
            }
        }
        return l;
    }

    /**
     * 方法一：动态规划
     * 序列
     *
     * @param pairs
     * @return
     */
    public static int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));
        int n = pairs.length;
        int[] dp = new int[n]; // 记录第i位上的最长子串的个数
        Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n - 1];
    }
}
