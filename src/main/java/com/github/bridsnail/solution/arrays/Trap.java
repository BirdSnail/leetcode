package com.github.bridsnail.solution.arrays;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水
 *  输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 *  输出: 6
 * @author BirdSnail
 * @date 2019/10/17
 */
public class Trap {

    private int[] height = {4, 2, 0, 3, 2, 5};

    public static void main(String[] args) {
        Trap trap = new Trap();
//        System.out.println(trap.solution());
        System.out.println(trap.solution3());
//        System.out.println(trap.solution2());
    }

    /**
     * 按列求，求出每一列的上面可以放多少水
     * 根据木桶效应，每一列上面可以放水的数量取决如两个因数：该列左边最高的墙，该列右边最高的墙
     * 1. cur >= Math.min(left,right) 当前列上面不会有水
     * 2. cur < Math.min(left, right) 当前列上面可以放 （Math.min(left,right) - cur）数量的水
     *
     * @return
     */
    public int solution() {
        int count = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int temp = Math.min(getMaxWallOfLeft(i), getMaxWallOfRight(i));
            if (height[i] < temp) {
                count += (temp - height[i]);
            }
        }

        return count;
    }

    private int getMaxWallOfLeft(int start) {
        int max = 0;
        for (int i = 0; i < start; i++) {
            if (height[i] > max) {
                max = height[i];
            }
        }
        return max;
    }

    private int getMaxWallOfRight(int start) {
        int max = 0;
        for (int i = start + 1; i < height.length; i++) {
            if (height[i] > max) {
                max = height[i];
            }
        }
        return max;
    }

    /**
     * 动态规划：
     * 提前将每个位置的左边最高墙的值储存在数组中，当前位置处的左侧最高值为 之前的最高值与这个位置前一个元素的大小的最大值。
     * 右边最高墙同理，之不过从最右边开始遍历
     */
    public int solution1() {
        int sum = 0;
        // 动态规划，提前解决子问题，空间换时间
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];
        for (int i = 1; i < height.length - 1; i++) {
            max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
        }

        for (int i = height.length - 2; i >= 1; i--) {
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }

        // 每一列可以装下的雨水
        for (int i = 1; i < height.length - 1; i++) {
            int temp = Math.min(max_left[i], max_right[i]);
            if (height[i] < temp) {
                sum += (temp - height[i]);
            }
        }

        return sum;
    }

    /**
     * 双指针法
     * 1. 当前元素是属入从左向右的更新还是从右向左的更新
     * 2. 已数组中的最高值为界限，界限在左的是从左向右的更新，在界限右的是从右向左的更新
     *
     * @return
     */
    public int solution2() {
        int sum = 0;
        int max_left = 0;
        int max_right = 0;
        int left = 1;
        int right = height.length - 2; // 加右指针进去
        for (int i = 1; i < height.length - 1; i++) {
            //从左到右更
            if (height[left - 1] < height[right + 1]) {
                max_left = Math.max(max_left, height[left - 1]);
                int min = max_left;
                if (min > height[left]) {
                    sum = sum + (min - height[left]);
                }
                left++;
                //从右到左更
            } else {
                max_right = Math.max(max_right, height[right + 1]);
                int min = max_right;
                if (min > height[right]) {
                    sum = sum + (min - height[right]);
                }
                right--;
            }
        }
        return sum;
    }

    /**
     * 灵感来源于双指针法
     *  1. 我们先寻找最高的一个墙
     *  2. 墙左边的元素从左到右变更，墙右边的元素从右到左变更
     */
    public int solution3() {
        int index = 0; // 最高墙的位置
        for (int i = 0; i < height.length; i++) {
            if (height[i] > height[index]) {
                index = i;
            }
        }

        int max_left = 0;
        int max_right = 0;
        int sum = 0;

        for (int left = 1; left < index; left++) {
            max_left = Math.max(max_left, height[left - 1]);
            if (height[left] < max_left) {
                sum = sum + (max_left - height[left]);
            }
        }

        for (int right = height.length -2; right > index; right--) {
            max_right = Math.max(max_right, height[right + 1]);
            if (height[right] < max_right) {
                sum = sum + (max_right - height[right]);
            }
        }

        return sum;
    }
}