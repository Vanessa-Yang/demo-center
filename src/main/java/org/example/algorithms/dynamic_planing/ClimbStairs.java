package org.example.algorithms.dynamic_planing;

/**
 * @Description 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * @Author VanessaYang
 * @Date: 2023/5/4 0004 15:27
 **/
public class ClimbStairs {

    public static void main(String[] args) {
        int i = doClimbStairs2(31);
        System.out.println();
        int j = doClimbStairs2(32);
        System.out.println();
        int k = doClimbStairs2(33);
        System.out.println();
        int l = doClimbStairs2(34);
        System.out.printf("%d\t%d\t%d\t%d", i, j, k, l);
    }

    /**
     * 动态规划：f(x) = f(x - 1) + f(x - 2)
     * 【滚动数组思想】适用于 n 比较小的情况
     *
     * @param n
     */
    public int doClimbStairs1(int n) {
        // 初始值 f(0) = 1
        int p = 0, q = 0, r = 1;
        // f(1) = 1, f(2) = 2, f(3) = 3
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    /**
     * 【矩阵快速幂】具体实现不是很理解？？
     *
     * @param n
     * @return
     */
    public static int doClimbStairs2(int n) {
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n);
        return res[0][0];
    }

    /**
     * 【通项公式】这里形成的数列正好是斐波那契数列，答案要求的 f(n) 即是斐波那契数列的第 n 项（下标从 0 开始）
     * 直接用浮点数，可能存在精度误差
     *
     * @param n
     * @return
     */
    public int doClimbStairs3(int n) {
        double sqrt5 = Math.sqrt(5);
        double fibn = Math.pow((1 + sqrt5) / 2, n + 1) + Math.pow((1 - sqrt5) / 2, n + 1);
        return (int) Math.round(fibn / sqrt5);
    }

    /**
     * ？？这个方法没有懂
     *
     * @param a
     * @param n
     * @return
     */
    private static int[][] pow(int[][] a, int n) {
        // 单位矩阵（identity matrix），特点：任何矩阵与 identity matrix 相乘，结果都等于本身。
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            // 任何 非零的奇数 与1做“位与”运算，结果为1
            if ((n & 1) == 1) {
                System.out.println("n = " + n);
                // 疑问？：如果任何数乘以对角矩阵等于自身，那此处逻辑的意义是什么，只为了取n==0前最后一次计算的矩阵a吗？
                ret = multiply(ret, a);
            }
            // 疑问？：这里又是什么意思，为什么能实现a^n的运算结果呢？
            // n = n / 2
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    /**
     * 2 * 2 矩阵乘法
     *
     * @param a
     * @param b
     * @return
     */
    private static int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }


}
