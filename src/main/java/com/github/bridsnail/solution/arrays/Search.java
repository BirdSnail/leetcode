package com.github.bridsnail.solution.arrays;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * (例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别
 *
 */
public class Search {
    public static void main(String[] args) {
        int[] nums = {1,3};
//        System.out.println(binarySearch(nums, 0, nums.length - 1, 2));
        System.out.println(solution(nums, 3));
    }


    /**
     * 思路：
     *  1. 找出发生旋转处的位置
     *  2. 进行二分查找
     * @param partSortArray 旋转过后的数组
     * @param target 待查找值
     * @return index of target
     */
    public static int solution(int[] partSortArray, int target) {
        if (partSortArray.length == 0){ return -1;}

        int pos = findPosOfCharged(partSortArray);
        if (target < partSortArray[0]) {
            return binarySearch(partSortArray, pos + 1, partSortArray.length - 1, target);
        } else {
            return binarySearch(partSortArray, 0, pos, target);
        }
    }

    /**二分查找*/
    public static int binarySearch(int[] array, int start, int end, int target) {
        if (start > array.length - 1 || start > end) {
            return -1;
        }

        int base = (start + end) / 2;
        if (array[base] == target) {
            return base;
        } else if (array[base] > target) {
            return binarySearch(array, start, end - 1, target);
        } else {
            return binarySearch(array, start + 1, end, target);
        }
    }

    private static int findPosOfCharged(int[] array) {
        for (int i = 0; i < array.length -1; i++) {
            if (array[i] > array[i + 1]) {
                return i;
            }
        }
        return array.length - 1;
    }
}
