package com.github.bridsnail.solution.arrays;

import java.util.*;

/**
 * @author BirdSnail
 * @date 2019/10/15
 */
public class Permute {

    public static void main(String[] args) {
       /* boolean[] booleans = new boolean[5]; // 构建的boolean类型数组默认值为false
        Arrays.fill(booleans,true);
        for (boolean a : booleans) {
            System.out.println(a);
        }*/

        int[] nums = {1, 2, 3};
        printPermuteList(solution(nums));

//        Deque<Integer> lists = new LinkedList<>();
//        lists.push(1);
//        lists.push(4);
//        lists.push(3);
//        lists.push(2);
//
//        System.out.println(Arrays.toString(lists.toArray()));

    }

    public static List<Deque<Integer>> solution(int[] nums) {
        List<Deque<Integer>> result = new ArrayList<>();
        Deque<Integer> temp = new LinkedList<>();
        dfs(nums, temp, result);
        return result;
    }

    /**
     * 深度优先搜索实现排列，进行递归，不断进行下层探索。递归的次数就是目前探索到的高度
     * 注意两点：
     *   1. 需要知道已经探索过的路径，跳过这些路径
     *   2. 到达最底后要返回到之前进入的状态即回退到父节点
     *
     *  这里使用LinkList保存已经探索过的路径，但是在contains判断时，它的时间复杂度为O(n)
     *  可以使用HashSet进行优化，达到O(1)
     * @param nums 数组
     * @param chosen 保存搜索过的路径
     * @param result  排列结果
     */
    public static void dfs(int[] nums, Deque<Integer> chosen, List<Deque<Integer>> result) {
        if (chosen.size() == nums.length) {
            result.add(new LinkedList<>(chosen));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (chosen.contains(num))
               continue;
            chosen.push(num);
            dfs(nums, chosen, result); // 进行递归
            chosen.pop(); // 状态重置，保留进入子叶节点之前的现场
        }
    }

    public static void dfs(int[] nums, Deque<Integer> chosen, Set<Integer> used,List<Deque<Integer>> result) {
        if (chosen.size() == nums.length) {
            result.add(new LinkedList<>(chosen));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used.contains(i))
                continue;

            used.add(i);
            chosen.push(nums[i]);
            dfs(nums, chosen, result); // 进行递归
            chosen.pop(); // 状态重置，保留进入子叶节点之前的现场
            used.remove(i);
        }
    }


    private static void printPermuteList(List<Deque<Integer>> permutes) {
        for (Deque<Integer> permute : permutes) {
            System.out.println(Arrays.toString(permute.toArray()));
        }
    }
}
