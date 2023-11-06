package org.example.algorithms.randomexercise;

import org.apache.catalina.LifecycleState;

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description Find Intersection
 * @Author VanessaYang
 * @Date: 2023/10/10 0010 10:10
 **/
public class Intersection {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // A [1,3,5,7,9] Strictly ascending sequence
        // B [3,4,6,10,13] Strictly ascending sequence
        int[] a = {1, 3, 5, 7, 9};
        int[] b = {3, 4, 7, 10, 13};
        //     3(left max), 9(right min)
        //     a_eli {3, 5, 7, 9}
        //     b_eli {3, 4, 7}
        // for a_one in a_eli:
        // b.indexOf(a_one) != -1 then collect it into c sequence.
        // return c sequence.
        int[] c = findIntersection(a, b);
        System.out.println(Arrays.toString(c)); // 3，7
        System.out.println(System.currentTimeMillis() - start);
    }

    private static int[] findIntersection(int[] a, int[] b) {
        // eliminate unfolded subsequences.
        int a_l = 0, a_r = a.length - 1, b_l = 0, b_r = b.length - 1;
//        while (a[a_l] != b[b_l] || a[a_r] != b[b_r]) {
        int leftV = Math.max(a[a_l], b[b_l]);
        int rightV = Math.min(a[a_r], b[b_r]);
        // shrink towards the pivot
        while (a[a_l] < leftV)
            a_l++;
        while (b[b_l] < leftV)
            b_l++;
        while (a[a_r] > rightV)
            a_r--;
        while (b[b_r] > rightV)
            b_r--;

        // method 1: time complexity is higher - Priori
        // iterate double sequence to find folded subsequence.
        List<Integer> list = new ArrayList<>();
        for (int i = a_l; i <= a_r; i++) {
            for (int j = b_l; j <= b_r; j++) {
                if (b[j] == a[i]) {
                    list.add(a[i]);
                    // decrease inner search range in b
                    b_l = j + 1;
                    break;
                }
            }
        }
        // method 2: space complexity is higher
//        Set<Integer> distinctSet = new HashSet<>();
//        for (int a_i : a) {
//            if (!distinctSet.add(a_i)) {
//                list.add(a_i);
//            }
//        }
//        for (int b_i : b) {
//            if (!distinctSet.add(b_i)) {
//                list.add(b_i);
//            }
//        }
        int[] arr = new int[list.size()];
        int idx = 0;
        for (Integer integer : list) {
            arr[idx++] = integer;
        }
        return arr;
    }

}
