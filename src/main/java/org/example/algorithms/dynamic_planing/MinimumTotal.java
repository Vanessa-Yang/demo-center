package org.example.algorithms.dynamic_planing;

import java.util.List;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/5/11 0011 10:26
 **/
public class MinimumTotal {

    /**
     * 120. 三角形最小路径和
     * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
     * <p>
     * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
     * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
     * <p>
     * 复杂度分析：该方案的时间复杂度为O(n^2), 空间复杂度为O(n^2)
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] f = new int[n][n];
        f[0][0] = triangle.get(0).get(0);
        // 二维的状态转义方程如下
        for (int i = 1; i < n; i++) {
            f[i][0] = f[i - 1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; j++) {
                f[i][j] = Math.min(f[i - 1][j - 1], f[i - 1][j]) + triangle.get(i).get(j);
            }
            f[i][i] = f[i - 1][i - 1] + triangle.get(i).get(i);
        }
        int ret = f[n - 1][0];
        for (int i = 1; i < n; i++) {
            ret = Math.min(f[n - 1][i], ret);
        }
        return ret;
    }

    /**
     * 空间复杂度降低为O(n)，列顺序遍历，需借助辅助变量
     *
     * @param triangle
     * @return
     */
    public int minimumTotal_v2(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] f = new int[n];
        f[0] = triangle.get(0).get(0);
        // 借用 pre_s2 来缓存f[i-1][j-1]的最小和值
        int pre_s2 = f[0];
        // 一维的状态转义方程如下
        for (int i = 1; i < n; i++) {
            f[0] += triangle.get(i).get(0);
            for (int j = 1; j < i; j++) {
                int temp = f[j];
                f[j] = Math.min(pre_s2, f[j]) + triangle.get(i).get(j);
                pre_s2 = temp;
            }
            f[i] = pre_s2 + triangle.get(i).get(i);
            pre_s2 = f[0];
        }
        int ret = f[0];
        for (int i = 1; i < n; i++) {
            ret = Math.min(ret, f[i]);
        }
        return ret;
    }

    /**
     * 空间复杂度降低为O(n)，列逆序遍历，不借助辅助变量
     *
     * @param triangle
     * @return
     */
    public int minimumTotal_v3(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] f = new int[n];
        f[0] = triangle.get(0).get(0);
        // 一维的状态转义方程如下
        for (int i = 1; i < n; i++) {
            f[i] = f[i - 1] + triangle.get(i).get(i);
            for (int j = i - 1; j > 0; j--) {
                f[j] = Math.min(f[j - 1], f[j]) + triangle.get(i).get(j);
            }
            f[0] += triangle.get(i).get(0);
        }
        int ret = f[0];
        for (int i = 1; i < n; i++) {
            ret = Math.min(ret, f[i]);
        }
        return ret;
    }

    /**
     * 空间复杂度降低为O(n)，由底至顶、由左到右
     *
     * @param triangle
     * @return
     */
    public int minimumTotal_v4(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] f = new int[n];
        int idx = 0;
        for (Integer integer : triangle.get(n - 1)) {
            f[idx++] = integer;
        }
        // 一维的状态转义方程如下
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                f[j] = Math.min(f[j], f[j + 1]) + triangle.get(i).get(j);
            }
        }
        return f[0];
    }

    /**
     * 算法原地工作：直接在给定的三角形数组上进行状态转移，不使用额外的空间
     * 空间复杂度降低为O(1)，由底至顶、由左到右
     *
     * @param triangle
     * @return
     */
    public int minimumTotal_v5(List<List<Integer>> triangle) {
        int n = triangle.size();
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                // 状态转移方程式如下
                triangle.get(i).set(j, Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)) + triangle.get(i).get(j));
            }
        }
        return triangle.get(0).get(0);
    }
}
