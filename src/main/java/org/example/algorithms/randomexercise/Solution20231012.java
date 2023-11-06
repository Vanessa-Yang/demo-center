package org.example.algorithms.randomexercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 随机刷题系列 - LeetCode热题100
 * @Author VanessaYang
 * @Date: 2023/10/12 0012 11:15
 **/
public class Solution20231012 {

    public static void main(String[] args) {
//        String s = "cbaebabacd", p = "abc"; // [0,6]
        String s = "abab", p = "ab"; // [0,1,2]
        System.out.println("找到字符串中所有字母异位词: " + findAnagrams(s, p));
    }

    /**
     * 438. 找到字符串中所有字母异位词
     * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
     * 数组 +
     *
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams(String s, String p) {
        // 时间复杂度 O(|s| * (|p| + 26)) = O(|s| * |p|)，其中|s|、|p|分别代表字符串s、p的长度
        // 空间复杂度 O(26 * 2)
        List<Integer> ans = new ArrayList<>();
        if (s.length() < p.length()) {
            return ans;
        }
        int[] pChars = new int[26];
        for (int i = 0; i < p.length(); i++) {
            pChars[p.charAt(i) - 'a'] += 1;
        }
        int[] sChars = new int[26];
        for (int i = 0; i <= s.toCharArray().length - p.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                sChars[s.charAt(i + j) - 'a'] += 1;
            }

            if (Arrays.equals(pChars, sChars)) {
                ans.add(i);
            }
            Arrays.fill(sChars, 0);
        }
        return ans;
    }


}
