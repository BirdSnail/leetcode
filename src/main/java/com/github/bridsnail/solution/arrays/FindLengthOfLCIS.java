package com.github.bridsnail.solution.arrays;


/**
 * 给定一个未经排序的整数数组，找到最长且连续的的递增序列
 *
 * example 1：
 *      输入: [1,3,5,4,7]
 *      输出: 3
 *      解释: 最长连续递增序列是 [1,3,5], 长度为3。尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。
 * example 2：
 *      输入: [2,2,2,2,2]
 *      输出: 1
 *      解释: 最长连续递增序列是 [2], 长度为1。
 */
public class FindLengthOfLCIS {
    public static void main(String[] args) {
        int[] nums = {2, 3, 5, 1, 5, 6, 7, 8};
        System.out.println(solution(nums));
    }

    /**
     * 思路：
     *   1. 利用滑动窗口，保持窗口右边界不断递增
     *   2. 当遇到数组不是递增的位置时，将窗口左边界移到右边界的位置
     */
    public static int solution(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int result = 1;
        int start = 0;
        int end = start + 1 ;

        while (end < nums.length) {
            if (nums[end] <= nums[end -1]) {
                result = Math.max(result, end - start);
                start = end;
            }
            end++;
        }
        result = Math.max(result, end - start);
        return result;
    }

}
