package org.example.algorithms.dynamic_planing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 354. 俄罗斯套娃信封问题
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * 注意：不允许旋转信封。
 * @Author VanessaYang
 * @Date: 2023/9/27 0027 13:59
 **/
public class MaxEnvelopes {

    public static void main(String[] args) throws Exception {
//        int[][] envelopes = {{5, 4}, {6, 4}, {6, 7}, {2, 3}}; // 3
//        int[][] envelopes = {{1, 1}, {1, 1}, {1, 1}}; // 1
//        int[][] envelopes = {{4, 5}, {4, 6}, {6, 7}, {2, 3}, {1, 1}}; // 4
        int[][] envelopes = {{1, 15}, {7, 18}, {7, 6}, {7, 100}, {2, 200}, {17, 30}, {17, 45}, {3, 5}, {7, 8}, {3, 6}, {3, 10}, {7, 20}, {17, 3}, {17, 45}}; // 3
        System.out.println(maxEnvelopes(envelopes));
    }

    /**
     * 常规的动态规划方法，会超出时间限制
     * 因此采用：二分查找的动态规划
     * 时间复杂度：O(N*logN)
     * 空间复杂度：O(N)
     *
     * @param envelopes
     * @return
     */
    public static int maxEnvelopes(int[][] envelopes) throws Exception {
        // w维升序，h维降序，将二维问题复杂度降为一维问题[求最长严格递增子序列]，避免同一w维不同h值的相互影响
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            } else {
                return o2[1] - o1[1];
            }
        });
        // 维护一个最长严格递增子序列 f
        List<Integer> f = new ArrayList<>();
        f.add(envelopes[0][1]);
        for (int i = 1; i < envelopes.length; i++) {
            if (envelopes[i][1] > f.get(f.size() - 1)) { // 符合严格递增，追加
                f.add(envelopes[i][1]);
            } else { // 在序列f中二分查找严格小于envelopes[i][1]的最大值f[j]，用h[i]接在f[j]之后，即更新f[j+1] = h[i]
                int idx = binarySearchLow(f, envelopes[i][1]);
                f.set(idx, envelopes[i][1]);
            }
        }
        System.out.println(Arrays.toString(f.toArray()));
        return f.size();
    }

    private static int binarySearchLow(List<Integer> sequence, int num) {
        int low = 0, high = sequence.size() - 1;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (num > sequence.get(mid)) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
