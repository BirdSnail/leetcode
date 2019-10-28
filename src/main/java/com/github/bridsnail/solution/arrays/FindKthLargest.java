package com.github.bridsnail.solution.arrays;

import java.util.Arrays;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *   输入: [3,2,1,5,6,4] 和 k = 2
 *   输出: 5
 * 示例 2:
 *   输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 *   输出: 4
 *
 * @author BirdSnail
 * @date 2019/10/14
 */
public class FindKthLargest {

    public static void main(String[] args) {
        int[] nums = {5, 4, 3, 2, 1};
//        System.out.println(solution(nums, 4));
        quackSort(nums,0, nums.length -1);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 思路：
     *  一种简单的想法就是先对数组进行排序，然后输出倒数第K个元素即可
     *  它的查找效率取决于所选用的排序算法
     */
    public static int solution(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**使用自定义的快速排序*/
    public static int solutionOfMySort(int[] nums, int k){
        quackSort(nums, 0, nums.length - 1);
        return nums[nums.length - k];
    }

    /**快速排序*/
    public static void quackSort(int[] nums, int left, int right) {
        if (nums.length == 0)
            return;

        if (left < right) {
            int pos = truePos(nums, left, right);
            quackSort(nums, left, pos-1);
            quackSort(nums, pos + 1, right);
        }
    }

    private static int truePos(int[] nums, int left, int right) {
        int temp = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= temp) {
                right--;
            }
            nums[left] = nums[right]; // 此时一定是 nums[right] < temp
            while (left< right && nums[left] <= temp)
                left++;
            nums[right] = nums[left];
        }
        nums[left] = temp; // 找准了一个数的位置
        return left;
    }
}
