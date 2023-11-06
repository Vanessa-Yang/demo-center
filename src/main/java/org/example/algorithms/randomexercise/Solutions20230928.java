package org.example.algorithms.randomexercise;

import org.apache.commons.collections4.MapUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description 随机刷题系列 - LeetCode热题100
 * @Author VanessaYang
 * @Date: 2023/9/28 0028 9:51
 **/
public class Solutions20230928 {

    public static void main(String[] args) {
//        int[] nums = {0, 1, 0, 3, 12}; // [1,3,12,0,0]
        int[] nums = {0}; // [0]
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));

        // 128. 最长连续序列
//        int[] nums = {100, 4, 200, 1, 3, 2}; // 4
//        int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1}; // 9
//        System.out.println(longestConsecutive(nums));

        // 49. 字母异位词分组
//        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
//        String[] strs = {"stop", "pots", "reed", "", "tops", "deer", "opts", ""};
//        System.out.println(groupAnagrams(strs));
//        System.out.println(groupAnagramsV2(strs));
//        System.out.println(groupAnagramsV3(strs));
    }


    /**
     * 283. 移动零
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     * <p>
     * 解法一：双指针
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    private static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    /**
     * 128. 最长连续序列
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     *
     * @param nums
     * @return
     */
    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longest = 0;
        for (int i = 0; i < nums.length; i++) {
            // 确定起点
            if (!set.contains(nums[i] - 1)) {
                int curNum = nums[i];
                int curLongest = 1;

                // 顺序查找，累加长度
                while (set.contains(curNum + 1)) {
                    curNum++;
                    curLongest++;
                }
                longest = Math.max(longest, curLongest);
            }
        }
        return longest;
    }

    /**
     * 49. 字母异位词分组
     * 难度：中等
     * <p>
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
     * <p>
     * 示例 1:
     * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
     * <p>
     * 提示：
     * 1 <= strs.length <= 104
     * 0 <= strs[i].length <= 100
     * strs[i] 仅包含小写字母
     *
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<Map<Character, Integer>> dicts = new ArrayList<>();
        List<List<String>> res = new ArrayList<>();

        for (String str : strs) {
            // 遍历字典，存在返回下标，不存在新增字典
            int idx = findInDict(dicts, str);
            // 更新字典下标对应的res值
            if (idx > res.size() - 1) {
                List<String> ss = new ArrayList<>();
                ss.add(str);
                res.add(ss);
            } else {
                List<String> ss = res.get(idx);
                ss.add(str);
                res.set(idx, ss);
            }
        }
        return res;
    }

    private static int findInDict(List<Map<Character, Integer>> dicts, String str) {
        int idx = 0;
        Map<Character, Integer> targetDict = new HashMap<>();
        for (char c : str.toCharArray()) {
            targetDict.put(c, targetDict.getOrDefault(c, 0) + 1);
        }
        // 空串单独处理
        if (targetDict.size() == 0 && "".equals(str)) {
            targetDict.put(null, 1);
        }

        for (Map<Character, Integer> dict : dicts) {
            if (targetDict.size() != dict.size()) {
                idx++;
                continue;
            }
            boolean match = true;
            for (Map.Entry<Character, Integer> entry : targetDict.entrySet()) {
                if (!dict.containsKey(entry.getKey()) || !Objects.equals(dict.get(entry.getKey()), entry.getValue())) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return idx;
            }
            idx++;
        }
        dicts.add(targetDict);
        return dicts.size() - 1;
    }

    /**
     * 排序+分组
     * T(N) = O(n*k*logk)
     * S(N) = O(n*k)
     *
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagramsV2(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs)
                .collect(Collectors.groupingBy(s -> s.chars().sorted()
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString()))
                .values());

    }

    /**
     * 字符串技术再编码+分组
     * T(N) = O(n(k + 26)) = O(n*k)
     * S(N) = O(n*k)
     * 其中，n为数组长度，k为字符串最大长度
     *
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagramsV3(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(s -> {
            // 转换成唯一key，如:a3b1c3
            int[] count = new int[26];// 范围：纯小写字母作为下标，其值为出现次数
            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a'] += 1;
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < count.length; i++) {
                if (count[i] > 0) {
                    builder.append((char) (i + 'a')).append(count[i]);
                }
            }
            return builder.toString();
        })).values());

    }
}
