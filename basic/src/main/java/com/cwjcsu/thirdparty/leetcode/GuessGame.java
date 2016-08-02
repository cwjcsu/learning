package com.cwjcsu.thirdparty.leetcode;

/**
 * Created by Atlas on 2016/7/23.
 */
public class GuessGame {

    int number ;

    public GuessGame(int number) {
        this.number = number;
    }

    /**
     * @param guess
     * @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
     */
    int guess(int guess) {
        if (guess < number) {
            return -1;
        }
        if (guess > number) {
            return 1;
        }
        return 0;
    }

    public int guessNumber(int n) {
        return guessNumber(1, n);
    }
    private int guessNumber(int a,int b) {
        int guess = (a+b)/2;
        int ret = guess(guess);
        if (ret == 0) {
            return guess;
        }
        if (ret ==-1) {
            return guessNumber(guess+1, b);
        }
        return guessNumber(a, guess-1);
    }
}
