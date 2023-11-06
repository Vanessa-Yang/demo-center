package org.example.algorithms.dynamic_planing;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/5/8 0008 10:49
 **/
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        // 状态转移方程：f(i, j) = f(i - 1, j) + f(i, j - 1)
        // 令边界值为1：f(i, 0) = 1, f(0, j) = 1，则最终求的结果应该是 f(m - 1, n - 1)
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public int minPathSum(int[][] grid) {
        // 状态转移方程：f(i, j) = min(f(i - 1, j), f(i, j - 1)) + grid[i][j]
        // 确定边界值：f(0,0) = grid[0][0]; 当i > 0时，f(i, 0) = f(i - 1, 0) + grid[i][0]; 当j > 0时，f(0, j) = f(0, j - 1) + grid[0][j];
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 63. 不同路径 II
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // 状态转移方程： f(i) = f(i - 1) 当前行前一列 + f(i) 上一行当前列 ==> f(i) += f(i-1)
        // 临界值： f(0) = obstacleGrid[0][0] == 1 ? 0 : 1;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] f = new int[n];
//        f[0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    f[j] = 0;
                    continue;
                }
                if (j - 1 >= 0 && obstacleGrid[i][j - 1] == 0) {
                    f[j] += f[j - 1];
                }
            }
        }
        return f[n - 1];
    }
}
