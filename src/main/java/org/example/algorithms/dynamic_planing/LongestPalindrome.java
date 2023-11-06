package org.example.algorithms.dynamic_planing;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ForkJoinPool;

@Slf4j
/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/5/15 0015 9:10
 **/
public class LongestPalindrome {

    /**
     * 5. 最长回文子串
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
     * <p>
     * 解法二：中心扩散法
     * T(n) = O(n^2)
     * S(n) = O(1)
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > (end - start + 1)) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    /**
     * 139. 单词拆分
     * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
     * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
     * <p>
     * 【暴力解法】：超出时间限制 ×
     *
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        // 将词典列表按首字母分组，建立索引表
        Map<Character, List<String>> capLetterWordsMap = new HashMap<>();
        for (String word : wordDict) {
            char capLetterKey = word.charAt(0);
            List<String> wordsValue = capLetterWordsMap.getOrDefault(capLetterKey, new ArrayList<>(0));
            wordsValue.add(word);
            capLetterWordsMap.put(capLetterKey, wordsValue);
        }

        // 遍历字符串，寻找不同方案
        int startIdx = 0;
        return generateCompositionChain(s, capLetterWordsMap, startIdx);
    }

    private static boolean generateCompositionChain(String s, Map<Character, List<String>> capLetterWordsMap, int startIdx) {
        List<String> curCapWords;
        while (startIdx < s.length() && (curCapWords = capLetterWordsMap.get(s.charAt(startIdx))) != null) {
            for (String curCapWord : curCapWords) {
                // 引入临时变量，目的是保证当前首字母下每个单词的起始下标都相同，保持为方法入口的值。
                int curStart = startIdx;
                if (s.length() - curStart < curCapWord.length() || !curCapWord.equals(s.substring(curStart, curStart + curCapWord.length()))) {
                    continue;
                }
                log.info("{} : {}", curStart, curCapWord);
                curStart += curCapWord.length();
                // 递归出口
                if (curStart == s.length() || generateCompositionChain(s, capLetterWordsMap, curStart)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 动态转移
     *
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak_v2(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        // 理解为右指针
        for (int i = 1; i <= s.length(); i++) {
            // 理解为左指针，构成字串s[j...i-1]
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    /**
     * 516. 最长回文子序列
     * <p>
     * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
     * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
     *
     * @param s
     * @return
     */
    public static int longestPalindromeSubseq(String s) {
        int len = s.length();
        if (len == 1) {
            return 1;
        }
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][len - 1];
    }

    /**
     * 72. 编辑距离
     * <p>
     * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
     * 你可以对一个单词进行如下三种操作：
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        // 边界值初始化：第0行、第0列
        for (int i = 1; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= word2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 上一步分三种情形(取步数最少的值)，再加本次需要的一步操作
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

    /**
     * 712. 两个字符串的最小ASCII删除和
     * 给定两个字符串s1 和 s2，返回 使两个字符串相等所需删除字符的 ASCII 值的最小和
     *
     * @param s1
     * @param s2
     * @return
     */
    public static int minimumDeleteSum(String s1, String s2) {
        // 声明状态转移数组
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        // 处理边界情况：初始化边界的值 (i > 0 && j == 0) || (i == 0 && j > 0)
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }
        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
        }
        // 处理 i > 0 && j > 0 的情况
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + s1.charAt(i - 1), dp[i][j - 1] + s2.charAt(j - 1));
                    // 还有一种情况：dp[i-1][j-1] + s1[i-1] + s2[j-1], 在此动态转移方程中直接忽略，为什么？
                    // 仔细想：dp[i-1][j-1]也是dp[i-1][j]和dp[i][j-1]的子问题
//                    dp[i][j] = Math.min(dp[i - 1][j - 1] + s1.charAt(i - 1) + s2.charAt(j - 1), Math.min(dp[i - 1][j] + s1.charAt(i - 1), dp[i][j - 1] + s2.charAt(j - 1)));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    /**
     * 115. 不同的子序列
     * 给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数。
     * 题目数据保证答案符合 32 位带符号整数范围。
     *
     * @param s
     * @param t
     * @return
     */
    public static int numDistinct(String s, String t) {
        if (s.length() < t.length()) {
            return 0;
        }
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        // Considering th border conditions:

        // 1. When j = n, t[j:] is empty-string, so dp[i][n] = 1 for any 0 <= i <= m
        // due to empty-string is a subsequence of every string.
        for (int i = 0; i <= s.length(); i++) {
            dp[i][t.length()] = 1;
        }
        // 2. When i = m, s[i:] is empty-string, so dp[m][j] = 0 for any 0 <= j < n
        // due to empty-string is a subsequence of every string.

        // 3. When i < m and j < n, then considering:
        // If s[i]==t[j], then dp[i][j] = dp[i+1][j+1]( when match ) + dp[i+1][j]( when not match)
        // Else, then dp[i][j] = dp[i+1][j]
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = t.length() - 1; j >= 0; j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }


    public static void main(String[] args) {
        String s = s = "babgbag", t = "bag";
        System.out.println(numDistinct(s, t));


//        String s1 = "sea", s2 = "eat";
//        System.out.println(minimumDeleteSum(s1, s2));
//        s1 = "delete";
//        s2 = "leet";
//        System.out.println(minimumDeleteSum(s1, s2));

//        String word1 = "horse", word2 = "ros";
//        System.out.println(minDistance(word1, word2));


//        String s = "cbbd";
//        System.out.println(longestPalindromeSubseq(s));

//        String s = "leetcode";
//        List<String> wordDict = Arrays.asList("leet", "code");
//        System.out.println(wordBreak_v2(s, wordDict));

//        String s = "cbbd";
//        System.out.println(longestPalindrome(s));
    }
}
