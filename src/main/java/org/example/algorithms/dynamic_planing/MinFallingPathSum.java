package org.example.algorithms.dynamic_planing;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/5/11 0011 13:44
 **/
public class MinFallingPathSum {

    /**
     * 931. 下降路径最小和
     * 给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
     * <p>
     * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列
     * （即位于正下方或者沿对角线向左或者向右的第一个元素）。
     * 具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
     * <p>
     * 复杂度计算：空间复杂度为O(1)，时间复杂度为O(n^2)
     *
     * @param matrix
     * @return
     */
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        if (n == 1) {
            return matrix[0][0];
        }
        // 原地工作：由顶之底，由左至右遍历。则底层的最小值为最后的结果
        for (int i = 1; i < n; i++) {
            matrix[i][0] += Math.min(matrix[i - 1][0], matrix[i - 1][1]);
            for (int j = 1; j < n - 1; j++) {
                matrix[i][j] += Math.min(Math.min(matrix[i - 1][j - 1], matrix[i - 1][j]), matrix[i - 1][j + 1]);
            }
            matrix[i][n - 1] += Math.min(matrix[i - 1][n - 2], matrix[i - 1][n - 1]);
        }
        int ret = matrix[n - 1][0];
        for (int i = 1; i < n; i++) {
            ret = Math.min(ret, matrix[n - 1][i]);
        }
        return ret;
    }


    /**
     * 221. 最大正方形
     * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
     * <p>
     * 暴力解法：遍历计算
     * 时间复杂度：O(m*n*min(m,n)^2)，其中 m 和 n 是矩阵的行数和列数。
     * - 需要遍历整个矩阵寻找每个 1，遍历矩阵的时间复杂度是 O(mn)
     * - 对于每个可能的正方形，其边长不超过 m 和 n 中的最小值，需要遍历该正方形中的每个元素判断是不是只包含 1，遍历正方形时间复杂度是 O(min(m,n)^2)
     * 总时间复杂度是 O(m*n*min(m,n)^2)
     * 空间复杂度：O(1)。额外使用的空间复杂度为常数。
     *
     * @param matrix
     * @return
     */
    public static int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxBorder = 0;
        for (int i = 0; i < m; i++) {
            int maxCurRow = 0;
            // 结束寻找
            if (maxBorder >= m - i)
                break;
            for (int j = 0; j < n; j++) {
                // 结束寻找
                if (maxBorder >= n - j) {
                    break;
                }
                if (matrix[i][j] == '0')
                    continue;
                int border = 1;
                while (i + border < m && j + border < n && areRightBottomBordersAllTrue(matrix, i, j, border)) {
                    border++;
                }
                maxBorder = Math.max(maxBorder, border);
                maxCurRow = Math.max(maxCurRow, border);
            }
        }
        return maxBorder * maxBorder;
    }

    private static boolean areRightBottomBordersAllTrue(char[][] matrix, int i, int j, int border) {
        char value = '1';
        boolean areBordersTrue = matrix[i + border][j + border] == value;
        for (int k = 0; k < border; k++) {
            if (!areBordersTrue) {
                break;
            }
            areBordersTrue = matrix[i + k][j + border] == value && matrix[i + border][j + k] == value;
        }
        return areBordersTrue;
    }

    public static int maximalSquare_v2(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int threeTimesZeroChar = '0' * 3;
        int maxBorderLen = 0;
        // 初始化最大边长值：取第一行或第一列的最大值
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == '1') {
                maxBorderLen = 1;
                break;
            }
        }
        if (maxBorderLen == 0) {
            for (int j = 0; j < n; j++) {
                if (matrix[0][j] == '1') {
                    maxBorderLen = 1;
                    break;
                }
            }
        }
        // 遍历其他行、其他列，动态方程处理
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 当前单元格与左上一个单元边框组成的2*2矩阵中：含有0则跳过，继续下一列
                if (matrix[i][j] == '0' || matrix[i - 1][j - 1] == '0' || matrix[i - 1][j] == '0' || matrix[i][j - 1] == '0') {
                    maxBorderLen = Math.max(maxBorderLen, Math.max(Math.max(matrix[i][j], matrix[i - 1][j - 1]), Math.max(matrix[i - 1][j], matrix[i][j - 1])) - '0');
                    continue;
                }
                // 进一步判断：
                matrix[i][j] = (char) (Math.min(Math.min(matrix[i - 1][j - 1], matrix[i - 1][j]), matrix[i][j - 1]) + '1' - '0');
                maxBorderLen = Math.max(maxBorderLen, matrix[i][j] - '0');
            }
        }
        return maxBorderLen * maxBorderLen;
    }

    public static void main(String[] args) {
        char[][] matrix = {{'1', '1', '1', '1', '1', '1', '1', '1'}, {'1', '1', '1', '1', '1', '1', '1', '0'}, {'1', '1', '1', '1', '1', '1', '1', '0'}, {'1', '1', '1', '1', '1', '0', '0', '0'}, {'0', '1', '1', '1', '1', '0', '0', '0'}};
        int res = maximalSquare_v2(matrix);
        System.out.println("res = " + res);
    }
}
