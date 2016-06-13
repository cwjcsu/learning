/*$Id: $
 * author                     date    comment
 * cwjcsu@gmail.com  2015年6月2日  Created
*/ 
package com.cwjcsu.learning.algorithm;
/**
 * 
 * @author atlas
 *
 */
public class Hanoi {

    /**
    * 
    * @param n 盘子的数目
    * @param origin 源座
    * @param assist 辅助座
    * @param destination 目的座
    */
    public void hanoi(int n, char origin, char assist, char destination) {
        if (n == 1) {
            move(origin, destination);
        } else {
            hanoi(n - 1, origin, destination, assist);
            move(origin, destination);
            hanoi(n - 1, assist, origin, destination);
        }
    }
 
    // Print the route of the movement
    private void move(char origin, char destination) {
        System.out.println("Direction:" + origin + "--->" + destination);
    }
 
    public static void main(String[] args) {
        Hanoi hanoi = new Hanoi();
        hanoi.hanoi(3, 'A', 'B', 'C');
    }
}
