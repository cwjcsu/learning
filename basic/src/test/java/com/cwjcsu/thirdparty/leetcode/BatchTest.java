package com.cwjcsu.thirdparty.leetcode;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Atlas on 2016/7/17.
 */
public class BatchTest {

    @Test
    public void testIntersect() {
        int[] nums1 = new int[]{1, 2, 2, 1};
        int[] nums2 = new int[]{2};
        int[] num = new Batch().intersect(nums1, nums2);
        print(num);
    }

    public static void print(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
            if (i < data.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println();
    }

    @Test
    public void testIsPowerOfThree() {
        System.out.println(b.isPowerOfThree(0));
        System.out.println(b.isPowerOfThree(3));
        System.out.println(b.isPowerOfThree(3 * 3 * 3 * 3));
        System.out.println(b.isPowerOfThree(3 * 3 * 3 * 3 + 1));
        System.out.println(b.isPowerOfThree(3 * 3 * 3 * 3 - 1));
    }

    private Batch b;

    private DynamicProgramming dp = null;

    @Before
    public void setUp() {
        b = new Batch();
        dp = new DynamicProgramming();
    }


    @Test
    public void testHammingWeight() {
        System.out.println(b.hammingWeight(11));

        System.out.println(b.hammingWeight(2147483648L));
    }

    @Test
    public void testReverseBits() {
        System.out.println(b.reverseBits(43261596));
    }

    @Test
    public void testClimbStairs() {
        System.out.println(dp.climbStairs(3));
    }

    @Test
    public void testCountBits() {
        //[0,1,1,2,1,2,2,3,1,2,2,3,2,3,3,4,1]
        print(b.countBits(16));
    }

    @Test
    public void testMaxProfit() {
//        System.out.println(b.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));//5

//        System.out.println(b.maxProfit(new int[]{1,2}));

//        System.out.println(b.maxProfit(new int[]{1,2,4}));

//        System.out.println(b.maxProfit(new int[]{2,1,2,0,1}));
        System.out.println(b.maxProfit(new int[]{2, 7, 1, 4, 11}));
    }

    @Test
    public void testConnectTreeLinkNode() {
        TreeLinkNode root = new TreeLinkNode(0, new TreeLinkNode(1, new TreeLinkNode(3), new TreeLinkNode(4)), new TreeLinkNode(2, new TreeLinkNode(5), new TreeLinkNode(6)));
        b.connect(root);
    }

    @Test
    public void testGuessNumber() {
        GuessGame gg = new GuessGame(6);
        System.out.println(gg.guessNumber(10));
    }


    @Test
    public void testIsValidSudou() {
        char[][] borad = new char[][]{
                ".87654321".toCharArray(),
                "2........".toCharArray(),
                "3........".toCharArray(),
                "4........".toCharArray(),
                "5........".toCharArray(),
                "6........".toCharArray(),
                "7........".toCharArray(),
                "8........".toCharArray(),
                "9........".toCharArray()};
        System.out.println(b.isValidSudoku(borad));//should true
    }


    @Test
    public void testIsValidSudou1() {
        char[][] borad = new char[][]{
                "..4...63.".toCharArray(),
                ".........".toCharArray(),
                "5......9.".toCharArray(),
                "...56....".toCharArray(),
                "4.3.....1".toCharArray(),
                "...7.....".toCharArray(),
                "...5.....".toCharArray(),
                ".........".toCharArray(),
                ".........".toCharArray()
        };
        System.out.println(b.isValidSudoku(borad));//should false
    }

    @Test
    public void testCompareVersion() {
//        System.out.println(b.compareVersion("1", "0"));
        System.out.println(b.compareVersion("1.0", "1.1"));
        System.out.println(b.compareVersion("1.0", "1"));
    }

    public void testMinDepth() {
        TreeNode node = new TreeNode(1, new TreeNode(2), null);
        System.out.println(b.minDepth(node));
    }

    @Test
    public void testCountAndSay() {
        System.out.println(b.countAndSay(2));
    }


    @Test
    public void testWiggleMaxLength() {
        int[] nums = new int[]{3, 3, 3, 2, 5};
        System.out.println(dp.wiggleMaxLength(nums));
    }

    @Test
    public void testUniquePaths() {
        System.out.println(dp.uniquePaths(2, 2));
        System.out.println(dp.uniquePaths(3, 3));
        System.out.println(dp.uniquePaths(3, 7));
    }

    @Test
    public void testUniquePathsWithObstacles() {
        int[][] obstacleGrid = new int[][]{
                new int[]{0, 0, 0},
                new int[]{0, 1, 0},
                new int[]{0, 0, 0},
        };
        obstacleGrid = new int[][]{
                {0},
                {0}
        };

        obstacleGrid = new int[][]{
                {1},
        };
        obstacleGrid = new int[][]{
                {1},
                {0}
        };

        System.out.println(dp.uniquePathsWithObstacles(obstacleGrid));
    }


    @Test
    public void testMinPathSum() {
        int[][] grid =
                {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(dp.minPathSum(grid));
    }

    @Test
    public void testCalculateMinimumHP() {
        int[][] grid =
                {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        System.out.println(dp.calculateMinimumHP(grid));//7

        grid = new int[][]{{3, -20, 30}, {-3, 4, 0}};
        System.out.println(dp.calculateMinimumHP(grid));//1
        grid = new int[][]{{1, -3, 3}, {0, -2, 0}, {-3, -3, -3}};
        System.out.println(dp.calculateMinimumHP(grid));//3
    }

    @Test
    public void testLengthOfLIS() {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(dp.lengthOfLIS(nums));//4
        nums = new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6};
        System.out.println(dp.lengthOfLIS(nums));//6
    }

    @Test
    public void testNumDecodings() {
        System.out.println(dp.numDecodings("12"));//2
        System.out.println(dp.numDecodings("12423"));//2
        System.out.println(dp.numDecodings("0"));//0
        System.out.println(dp.numDecodings("10"));//1
        System.out.println(dp.numDecodings("230"));//0
        System.out.println(dp.numDecodings("17"));//0
    }

}
