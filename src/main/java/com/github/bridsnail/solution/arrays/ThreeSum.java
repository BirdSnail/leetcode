package com.github.bridsnail.solution.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 *
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *  满足要求的三元组集合为：
 *  [
 *    [-1, 0, 1],
 *    [-1, -1, 2]
 *  ]
 *
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = {0, -1, 0, 1, 0};
//        printList(solution(nums));
        System.out.println(Arrays.asList(nums));
//        printList(solution1(nums));
    }

    /**
     * 思路：
     * 1. 对数组进行排序，以第一个元素为基数。
     * 2. 如果基数大于0，肯定找不到三个数之和为0，直接返回。
     * 3. 再基数后面利用两个指针，一个基数后面的的左指针，一个结尾处的右指针。三个数之和为0就添加到结果集中
     * 4. 如果sum < 0,左指针右移；sum > 0 ,右指针左移</>
     * 5. <>跳过重复元素：
     *        1. left,right重复:如果 num[i] = num[i+1],代表可能会出现重复的元素，需要跳过该元素</>
     *        2. 基数重复: base > 0时,当前基数与前一个数相比较,如果num[base] = num[base -1],代表会有重复的元素</>
     */
    public static List<List<Integer>> solution(int[] num) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(num);

        for (int i = 0; i < num.length - 2 && num[i] <= 0; i++) {
            if (i == 0 || (i > 0 && num[i] != num[i - 1])) { // 防止基数重复
                // 使用left, right两个指针
                int left = i + 1;
                int right = num.length - 1;

                while (left < right) {
                    int sum = num[i] + num[left] + num[right];
                    if (sum == 0) {
                        result.add(Arrays.asList(num[i], num[left], num[right]));
                        while (left < right && num[left] == num[left + 1]) {
                            left++;
                        }
                        while (left < right && num[right] == num[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (sum > 0) {
                        while (left < right && num[right] == num[right - 1]) {
                            right--;
                        }
                        right--;
                    } else {
                        while (left < right && num[left] == num[left + 1]) {
                            left++;
                        }
                        left++;
                    }
                }
            }
        }
        return result;
    }

    private static void printList(List<List<Integer>> result) {
        for (List<Integer> integers : result) {
            System.out.println(Arrays.toString(integers.toArray()));
        }
    }

    /**
     * 该解法代码更少,但是有一个Bug: 会排除[0,0,0]这种情况
     * 产生的原因在于提前跳过了重复的元素然后执行求和,而不是先执行求和然后再跳过重复的元素.
     * 当{@code num[base] = 0},left指针会直接到达最后一个0出现的位置,right指针也就不会到达中间0出现的位置,因此不会出现 {@code 0+0+0}这种情况
     * @param num 数组
     * @return
     */
    public static List<List<Integer>> solution1(int[] num) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(num);

        for (int i = 0; i < num.length - 2 && num[i] <= 0; i++) {
            if (i == 0 || (i > 0 && num[i] != num[i - 1])) { // 防止基数重复
                // 使用left, right两个指针
                int left = i + 1;
                int right = num.length - 1;
                while (left < right && num[left] == num[left + 1]) {
                    left++;
                }
                while (left < right && num[right] == num[right - 1]) {
                    right--;
                }

                while (left < right) {
                    int sum = num[i] + num[left] + num[right];
                    if (sum == 0) {
                        result.add(Arrays.asList(num[i], num[left], num[right]));
                        left++;
                        right--;
                    } else if (sum > 0) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return result;
    }
}
