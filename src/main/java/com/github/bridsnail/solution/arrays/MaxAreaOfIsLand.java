package com.github.bridsnail.solution.arrays;

/**
 * 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。
 * 你可以假设二维矩阵的四个边缘都被水包围着。找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
 *
 * 示例：
 *  [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 *
 *  答案是：6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。
 */
public class MaxAreaOfIsLand {

    public static void main(String[] args) {
        int[][] grid = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                        {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                        {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};

        System.out.println(solution(grid));
    }

    /**
     * 思路：
     *  深度优先搜索。
     *  当前点grid[i,j]==1,就向前后左右四个方向递归的进行搜索，找出是岛屿的点。
     *      up = grid[i-1,j]
     *      down = grid[i+1,j]
     *      left = grid[i,j-1]
     *      right = grid[i,j+1]
     *  同时已经去确定是“1”的点要标记成“0”，防止重复搜索
     *
     * @param grid 二维数组组成的岛屿
     * @return maxArea
     */
    public static int solution(int[][] grid) {
        int maxArea = 0;
        int weight = grid[0].length;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < weight; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(getAreaOfIsLand(grid, i, j), maxArea);
                }
            }
        }

        return maxArea;
    }

    private static int getAreaOfIsLand(int[][] grid, int row, int weight) {
        // 防止数组下标越界
        if (row < 0 || row >= grid.length) {
            return 0;
        } else if (weight < 0 || weight >= grid[0].length) {
            return 0;
        }

        if (grid[row][weight] == 1) {
            grid[row][weight] = 0; // 标记为已搜索过
            return 1 + getAreaOfIsLand(grid, row - 1, weight) + getAreaOfIsLand(grid, row + 1, weight) +
                    getAreaOfIsLand(grid, row, weight - 1) + getAreaOfIsLand(grid, row, weight + 1);

        }
        return 0;
    }
}
