package com.github.bridsnail.solution.arrays;

import java.util.HashSet;

/**
 * 给定一个未排序的整数数组，找出最长连续序列的长度。要求算法的时间复杂度为 O(n)。
 * <p>
 * 示例:
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * @author BirdSnail
 * @date 2019/10/14
 */
public class LongestConsecutive {

    public static void main(String[] args) {
        int[] nums = {4, 3, 5};
        System.out.println(solution(nums));
    }

    /**
     * 思路：
     * 对暴力法进行优化，优化有两点:
     *  1. 使用HashSet存储，达到查询时为O(1)
     *  2. 跳过连续序列的子序列判断。
     *
     * 时间复杂度：O(n)
     *
     * 尽管在 for 循环中嵌套了一个 while 循环，时间复杂度看起来像是二次方级别的。但其实它是线性的算法。
     * 因为只有当 currentNum 遇到了一个序列的开始， while 循环才会被执行（也就是 currentNum-1 不在数组 nums 里），while 循环在整个运行过程中只会被迭代 n 次。
     * 这意味着尽管看起来时间复杂度为 O(n*n) ，实际这个嵌套循环只会运行 O(n + n) = O(n) 次。所有的计算都是线性时间的，所以总的时间复杂度是 O(n)的。
     *
     */
    public static int solution(int[] nums) {
        HashSet<Integer> hashSet = getHashSetOfArray(nums);
        int result = 0;

        for (int num : nums) {
            if (!hashSet.contains(num - 1)) { // 跳过连续序列的子序列,只会从连续序列的最小的值开始循环
                int tempResult = 0;
                while (hashSet.contains(num++)) {
                    tempResult++;
                }
                result = Math.max(result, tempResult);
            }
        }
        return result;
    }

    private static HashSet<Integer> getHashSetOfArray(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (Integer num : nums) {
            hashSet.add(num);
        }
        return hashSet;
    }
}
