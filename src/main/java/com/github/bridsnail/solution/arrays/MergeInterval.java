package com.github.bridsnail.solution.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *      输入: [[1,3],[2,6],[8,10],[15,18]]
 *      输出: [[1,6],[8,10],[15,18]]
 *      解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 示例 2:
 *      输入: [[1,4],[4,5]]
 *      输出: [[1,5]]
 *      解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 *
 * @author BirdSnail
 * @date 2019/10/17
 */
public class MergeInterval {

    public static void main(String[] args) {
        int[][] interval = {{1, 5}, {6, 9}, {3, 5}};

        for (int[] ints : solution(interval)) {
            System.out.println(Arrays.toString(ints));
        }

//        List<int[]> res = new ArrayList<>();
//        for (int[] ints : interval) {
//            res.add(ints);
//        }
//
//        int[][] temp = new int[res.size()][];
//        for (int i = 0; i < res.size(); i++) {
//            temp[i] = res.get(i);
//        }
//
//        for (int[] ints : temp) {
//            System.out.println(Arrays.toString(ints));
//        }
//
//        for (int[] ints : res.toArray(new int[0][])) {
//            System.out.println(Arrays.toString(ints));
//        }
    }


    /**
     * 思路：
     *   1. 先对二维数组按每个数组的第一位从小到达排序
     *   2. 邻近的下个数组的 next[1] <= first[0]，就代表下一个区间要和当前区间进行合并
     *   3. 合并的区间的边界：左边界为两个的最小值（first[0]就是最小值），右边界为最大值
     */
    public static int[][] solution(int[][] interval) {
        List<int[]> result = new ArrayList<>();

        Arrays.sort(interval, (a, b) -> a[0] -b[0]);
        int i = 0;
        while (i < interval.length) {
            int left = interval[i][0];
            int right = interval[i][1];
            while (i < interval.length - 1 && interval[i+1][0] <= right) {
                i++;
                right = Math.max(right, interval[i][1]);
            }

            i++;
            result.add(new int[]{left, right});
        }

        // Otherwise, a new array is allocated with the runtime type of the specified array and the size of this list.
        // 长度指定为0是为了让它自动适配list的大小
        return  result.toArray(new int[0][]);
    }
}
