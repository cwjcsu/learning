/*
 * author               date               comment
 * chenweijun@skybility.com   2016/8/3 9:45       Created
 * All rights reserved.
 */

package com.cwjcsu.thirdparty.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Atlas
 */
public class DynamicProgramming {

    static Map<Integer, Integer> stairCache = new HashMap<Integer, Integer>();

    static {
        stairCache.put(1, 1);
        stairCache.put(2, 2);
    }

    /**
     * 70
     * OK
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        Integer step = stairCache.get(n);
        if (step != null) {
            return step;
        }
        int s = climbStairs(n - 1) + climbStairs(n - 2);
        stairCache.put(n, s);
        return s;
    }

    /**
     * 376
     * <p>
     * 删掉一些元素之后（可能不删，可以删头尾中间的），获取最长wiggle子序列，
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int delta = nums[1] - nums[0];
        int lastLen = (delta != 0) ? 2 : 1;
        for (int i = 2; i < nums.length; i++) {
            int newDelta = nums[i] - nums[i - 1];
            if (newDelta != 0 && newDelta * delta <= 0) {
                lastLen++;
            }
            delta = newDelta;
        }
        return lastLen;
    }


    /**
     * 62,使用递归法比较耗时，使用循环遍历法节约时间
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        if (uniquePaths == null) {//题中，m，n不超过100
            uniquePaths = new int[101][101];
        }
        if (uniquePaths != null) {
            if (uniquePaths[m][n] != 0) {
                return uniquePaths[m][n];
            }
        }
        if (m == 1) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        int p = uniquePaths(m, n - 1) + uniquePaths(m - 1, n);
        uniquePaths[m][n] = p;
        return p;
    }

    /**
     * 使用缓存加快测试执行速度
     */
    private int[][] uniquePaths;

    /**
     * 62 单纯 使用循环法
     * http://blog.csdn.net/ljiabin/article/details/41805859
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths_1(int m, int n) {
        int[][] a = new int[m][n];
        for (int i = 0; i < m; i++) {
            a[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            a[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                a[i][j] = a[i - 1][j] + a[i][j - 1];
            }
        }
        return a[m - 1][n - 1];
    }

    /**
     * 63 递归法耗时太大，用遍历法
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] paths = new int[m][n];
        boolean found = false;
        for (int i = 0; i < m; i++) {
            if (found || obstacleGrid[i][0] > 0) {
                paths[i][0] = 0;
                found = true;
            } else {
                paths[i][0] = 1;
            }
        }
        found = false;
        for (int j = 0; j < n; j++) {
            if (found || obstacleGrid[0][j] > 0) {
                paths[0][j] = 0;
                found = true;
            } else {
                paths[0][j] = 1;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] > 0) {
                    paths[i][j] = 0;
                } else {
                    paths[i][j] = paths[i - 1][j] + paths[i][j - 1];
                }
            }
        }
        return paths[m - 1][n - 1];
    }

    /**
     * 64
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] sum = new int[m][n];
        sum[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            sum[i][0] = sum[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            sum[0][j] = sum[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                sum[i][j] = Math.min(sum[i - 1][j], sum[i][j - 1]) + grid[i][j];
            }
        }
        return sum[m - 1][n - 1];
    }

    /**
     * like 64，求最大值
     * 174
     *
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon.length == 0) {
            return 0;
        }
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] remainHp = new int[m][n];
        int[][] hp = new int[m][n];//hp[i][j]是走到底(i,j)位置必须要有的初始hp
        int d0 = dungeon[0][0];
        if (d0 <= 0) {
            hp[0][0] = 1 - d0;
            remainHp[0][0] = 1;
        } else {
            hp[0][0] = 1;
            remainHp[0][0] = d0 + 1;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                HpPair p1 = i > 0 ? calcHpPair(remainHp[i - 1][j], hp[i - 1][j], dungeon[i][j]) : null;
                HpPair p2 = j > 0 ? calcHpPair(remainHp[i][j - 1], hp[i][j - 1], dungeon[i][j]) : null;
                HpPair curr = p1;
                if (p1 != null && p2 != null) {
                    if (p1.hp < p2.hp) {
                        curr = p1;
                    } else {
                        curr = p2;
                    }
                } else {
                    curr = p1 != null ? p1 : p2;
                }
                if (curr == null) {
                    continue;
                }
                hp[i][j] = curr.hp;
                remainHp[i][j] = curr.remain;
            }
        }
        return hp[m - 1][n - 1];
    }

    /**
     * 根据上一个节点的信息（剩余HP：remain，达到这个点的最少开始hp：hp）和当前节点的dungeon值计算当前节点信息
     *
     * @param remain
     * @param hp
     * @param dungeon
     * @return
     */
    private HpPair calcHpPair(int remain, int hp, int dungeon) {
        int hp1;
        int remain1;
        int t1 = remain + dungeon;
        if (t1 < 1) {
            hp1 = hp - t1 + 1;
            remain1 = 1;//-remain+1
        } else {
            hp1 = hp;
            remain1 = t1;
        }
        return new HpPair(hp1, remain1);
    }

    private static class HpPair {
        int hp;
        int remain;

        public HpPair(int hp, int remain) {
            this.hp = hp;
            this.remain = remain;
        }
    }

}
