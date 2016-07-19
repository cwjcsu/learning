package com.cwjcsu.thirdparty.leetcode;

import org.junit.Before;
import org.junit.Test;

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
        Batch b = new Batch();
        System.out.println(b.isPowerOfThree(0));
        System.out.println(b.isPowerOfThree(3));
        System.out.println(b.isPowerOfThree(3 * 3 * 3 * 3));
        System.out.println(b.isPowerOfThree(3 * 3 * 3 * 3 + 1));
        System.out.println(b.isPowerOfThree(3 * 3 * 3 * 3 - 1));
    }

    private Batch b;

    @Before
    public void setUp() {
        b = new Batch();
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
        System.out.println(b.climbStairs(3));
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
}
