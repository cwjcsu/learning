/*
 * author               date               comment
 * chenweijun@skybility.com   2016/8/3 9:45       Created
 * All rights reserved.
 */

package com.cwjcsu.thirdparty.leetcode;

import com.cwjcsu.projecteuler.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
//    public int calculateMinimumHP(int[][] dungeon) {
//        if (dungeon.length == 0) {
//            return 0;
//        }
//        int m = dungeon.length;
//        int n = dungeon[0].length;
//        int[][] remainHp = new int[m][n];
//        int[][] hp = new int[m][n];//hp[i][j]是走到底(i,j)位置必须要有的初始hp
//        int d0 = dungeon[0][0];
//        if (d0 <= 0) {
//            hp[0][0] = 1 - d0;
//            remainHp[0][0] = 1;
//        } else {
//            hp[0][0] = 1;
//            remainHp[0][0] = d0 + 1;
//        }
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                HpPair p1 = i > 0 ? calcHpPair(remainHp[i - 1][j], hp[i - 1][j], dungeon[i][j]) : null;
//                HpPair p2 = j > 0 ? calcHpPair(remainHp[i][j - 1], hp[i][j - 1], dungeon[i][j]) : null;
//                HpPair curr = p1;
//                if (p1 != null && p2 != null) {
//                    if (p1.hp < p2.hp) {
//                        curr = p1;
//                    } else {
//                        curr = p2;
//                    }
//                } else {
//                    curr = p1 != null ? p1 : p2;
//                }
//                if (curr == null) {
//                    continue;
//                }
//                hp[i][j] = curr.hp;
//                remainHp[i][j] = curr.remain;
//            }
//        }
//        return hp[m - 1][n - 1];
//    }
//
//    /**
//     * 根据上一个节点的信息（剩余HP：remain，达到这个点的最少开始hp：hp）和当前节点的dungeon值计算当前节点信息
//     *
//     * @param remain
//     * @param hp
//     * @param dungeon
//     * @return
//     */
//    private HpPair calcHpPair(int remain, int hp, int dungeon) {
//        int hp1;
//        int remain1;
//        int t1 = remain + dungeon;
//        if (t1 < 1) {
//            hp1 = hp - t1 + 1;
//            remain1 = 1;//-remain+1
//        } else {
//            hp1 = hp;
//            remain1 = t1;
//        }
//        return new HpPair(hp1, remain1);
//    }
//
//    private static class HpPair {
//        int hp;
//        int remain;
//
//        public HpPair(int hp, int remain) {
//            this.hp = hp;
//            this.remain = remain;
//        }
//    }


    /**
     * 174，从右下到左上进行dp迭代
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
        int[][] hp = new int[m][n];//hp[i][j]是走到底(i,j)位置必须要有的初始hp
        hp[m - 1][n - 1] = Math.max(1, 1 - dungeon[m - 1][n - 1]);
        for (int i = m - 2; i >= 0; i--) {
            hp[i][n - 1] = Math.max(1, hp[i + 1][n - 1] - dungeon[i][n - 1]);
        }
        for (int j = n - 2; j >= 0; j--) {
            hp[m - 1][j] = Math.max(1, hp[m - 1][j + 1] - dungeon[m - 1][j]);
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                hp[i][j] = Math.max(1, Math.min(hp[i + 1][j], hp[i][j + 1]) - dungeon[i][j]);
            }
        }
        return hp[0][0];
    }


    /**
     * 300
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        /**
         * lens[i]表示迭代到第i步之后，以nums[i]结尾的最长子序列的长度
         */
        int[] lens = new int[nums.length];
        lens[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            int maxLen = -1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    maxLen = Math.max(maxLen, lens[j]);
                }
            }
            if (maxLen > 0) {
                lens[i] = maxLen + 1;
            } else {
                lens[i] = 1;//比前面所有数据都小，上升序列从这里开始，所以取1
            }
        }
        return Util.max(lens);
    }

    /**
     * 91. Decode Ways
     * 算法描述
     *
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if (s.length() == 0) {
            return 0;
        }
        /**
         * w[i]表示子问题[0~i]时最后一个数字是单数字编码的方法数；
         */
        int[] w = new int[s.length()];
        /**
         * v[i]表示子问题[0~i]时最后一个数字是与前面数字合并进行编码的方法数；
         */
        int[] v = new int[s.length()];
        w[0] = s.charAt(0) != '0' ? 1 : 0;
        v[0] = 0;
        for (int i = 1; i < s.length(); i++) {
            int ch = s.charAt(i) - '0';
            int prev = s.charAt(i - 1) - '0';
            if (ch == 0) {//当前为'0'
                w[i] = 0;//'0'无法单独编码,所以w[i]取0
                v[i] = (prev == 1 || prev == 2) ? w[i - 1] : 0;//'0'与前面合并编码只有当prev为'1'或者'2'
                continue;
            }
            w[i] = v[i - 1] + w[i - 1];//前面两种类型均可连接单独编码数字
            if (prev == 1 || (prev == 2 && ch <= 6)) {//满足这个条件，才可以合并编码
                v[i] = w[i - 1];
            } else {
                v[i] = 0;
            }
        }
        return w[s.length() - 1] + v[s.length() - 1];
    }

    /**
     * 120 Triangle
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int depth = triangle.get(triangle.size() - 1).size();
        int[] sum = new int[depth];
        int[] temp = new int[depth];
        sum[0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> row = triangle.get(i);
            for (int j = 0; j < row.size(); j++) {
                temp[j] = row.get(j) + Math.min(j > 0 ? sum[j - 1] : Integer.MAX_VALUE, j < i ? sum[j] : Integer.MAX_VALUE);
            }
            int[] t = sum;
            sum = temp;
            temp = t;
        }
        return Util.min(sum);
    }


    /**
     * 264
     * <p>
     * 设想一下，所有丑陋数都是由因子2,3,5组成，对于一个乘5得到的丑陋数U，设u*5=U，则u也比如是丑陋数，假设是第l个丑陋数。
     * 同理可得，所有的丑陋数，都可以由它们的前任们乘以2,3或者5得到。那么可以用一个下标l3记录它乘以5以后得到的最大丑陋数。
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        /**
         * 按顺序不重不漏记录丑陋数
         */
        List<Integer> list = new ArrayList<Integer>(n);
        list.add(1);
        /**
         *  l1,l2,l3分别记录到目前为止，跟2,3,5相乘得到当前最大丑陋数的下标，即在list中的下标。
         */
        int l1 = 0, l2 = 0, l3 = 0;
        while (list.size() < n) {
            int u1 = list.get(l1) * 2;
            int u2 = list.get(l2) * 3;
            int u3 = list.get(l3) * 5;
            int u = Math.min(u1, Math.min(u2, u3));
            list.add(u);
            if (u1 == u) l1++;
            if (u2 == u) l2++;
            if (u3 == u) l3++;
        }
        return list.get(n - 1);
    }

    /**
     * 221. Maximal Square
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] maxSqure = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int d = matrix[i][j] - '0';
                if (i == 0 || j == 0) {
                    maxSqure[i][j] = d;
                    continue;
                }
                if (d == 0) {
                    maxSqure[i][j] = 0;
                } else {
                    int p = Math.min(maxSqure[i - 1][j], Math.min(maxSqure[i - 1][j - 1], maxSqure[i][j - 1]));
                    maxSqure[i][j] = p + 1;
                }
            }
        }
        int len = Util.max(maxSqure);
        return len * len;
    }

    public static int max(int[][] d) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[i].length; j++) {
                max = Math.max(max, d[i][j]);
            }
        }
        return max;
    }

    /**
     * 85. Maximal Rectangle
     *
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        return 0;
    }

    class Rect {
        int row;
        int col;
        int area;

        public Rect(int row, int col) {
            this.row = row;
            this.col = col;
            this.area = row * col;
        }
    }


    /**
     * 53. Maximum Subarray
     * <p>
     * Time limit exceed
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int[] sum = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sum[i] = i == 0 ? nums[i] : nums[i] + sum[i - 1];
        }
        //s,t分别是当前步骤所求数组的下标的起始点，max是所求的最大和
        int s = 0, t = 0;
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            for (int j = s; j <= i; j++) {
                int v = j == 0 ? sum[i] : sum[i] - sum[j - 1];
                if (v > max) {
                    s = j;
                    t = i;
                    max = v;
                }
            }
        }
        return max;
    }
}
