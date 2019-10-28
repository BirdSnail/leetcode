package com.github.bridsnail.solution.arrays;

/**
 * @author BirdSnail
 * @date 2019/10/16
 */
public class FindCircleNum {

    public static void main(String[] args) {
        int[][] friends = {{1, 0, 0, 1},
                {0, 1, 1, 0},
                {0, 1, 1, 1},
                {1, 0, 1, 1}};
        
        System.out.println(solution(friends));
    }

    /**
     * 深度优先搜索进行标记，N 个朋友最多有 N 个圈子，即每一个人相互独立。
     * 这道题可以抽象成在一些点组成的图中找出连通图的个数。
     *
     * 1. 遍历所有的点，每遍历一个点就标记为已经遍历，遇到一个没有被标记的点就可以将圈子数count加1
     * 2. 对这个点的链接点进行深度递归搜索，同样标记为已遍历。
     * 3. 当遍历完完后count数就是连通图的个数
     */
    public static int solution(int[][] friends) {
        int[] visited = new int[friends.length];
        int count = 0;

        for (int i = 0; i < friends.length; i++) {
            if (visited[i] == 0){
                dfs(friends, visited, i);
                count++;
            }
        }

        return count;
    }

    /**深度优先搜索进行标记*/
    public static void dfs(int[][] friends, int[] visited, int i) {
        for (int j = 0; j < friends.length; j++) {
            if (friends[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1; // 标记为已经搜索过
                dfs(friends, visited, j);
            }
        }
    }
}
