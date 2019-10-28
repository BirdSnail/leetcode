package com.github.bridsnail.solution.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 * 给定 n 和 k，返回第 k 个排列
 *
 * 说明：
 *  给定 n 的范围是 [1, 9]。
 *  给定 k 的范围是[1,  n!]。
 *
 * 输入: n = 3, k = 3
 * 输出: "213"
 *
 * 输入: n = 4, k = 9
 * 输出: "2314"
 *
 * @author BirdSnail
 * @date 2019/10/15
 */
public class KthPermutation {

    public static void main(String[] args) {
        System.out.println(solution(3, 2, new ArrayList<>()));
    }

    /***
     * 逐个确定
     *  k 与 （n -1）!之间的关系：
     *  (n -1)!代表所能产生的全部排列个数
     *  第K个排列应该落在指定的区间，它满足前 i 个数的排列数小于 k。
     *  每确定一个数就从候选的数字种移除它。
     */
    public static String solution(int n, int k, List<Integer> result) {
        int[] factorial = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
        // 候选的数字
        List<Integer> candidates = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            candidates.add(i + 1);
        }

        k -= 1;
        for (int i = n-1; i >=0; i--) {
            int index = k / factorial[i];
            result.add(candidates.remove(index));
            k -= index * factorial[i];
        }
        return Arrays.toString(result.toArray());
    }
}
