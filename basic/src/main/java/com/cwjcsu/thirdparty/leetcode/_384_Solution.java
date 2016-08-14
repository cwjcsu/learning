package com.cwjcsu.thirdparty.leetcode;

import java.util.Random;

/**
 * 384
 * Created by Atlas on 2016/8/14.
 */
public class _384_Solution {

    int[] origin = null;
    int[] result = null;

    public _384_Solution(int[] nums) {
        this.origin = nums;
        this.result = new int[this.origin.length];
        this.reset();
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        System.arraycopy(this.origin, 0, this.result, 0, origin.length);
        return result;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        shuffle(this.result);
        return result;
    }

    public void shuffle(int[] array) {
        Random random = new Random();
        for (int j = 0; j < array.length; j++) {
            int index = j + random.nextInt(array.length - j);// 生成一个[j,n)之间的随机数，作为数组下标
            // 交换array[j]和array[index]，那么array[0..j]为已经获取到的随机数
            int temp = array[index];
            array[index] = array[j];
            array[j] = temp;
        }
    }
}
