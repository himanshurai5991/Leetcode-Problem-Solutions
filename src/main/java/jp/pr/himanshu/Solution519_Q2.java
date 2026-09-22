package jp.pr.himanshu;

import java.util.ArrayList;
import java.util.List;

public class Solution519_Q2 {

    // 3003
    // 3156 - 1000*3 + 1*100 + 10*5 + 6  3113 , 3223 3003 , 2992 , 4004
    //
    // 3000 + 11 + 3
    // 35465 - 35453, 3553
    // [11,21,31.... 101,111,121,202,212,222,232, 1001,1111,1221, 1331, 1441, 1551, 4114,4224,4334]
    // [1,2.......................................100 , 110*10 +1 , 120
    // 1000*4 + 100*2+ 10*1+ 1  = 4211
    List<Integer> palindrome;
    public long minOperations(int[] nums) {
        int len = nums.length;
        return len;
    }

    Solution519_Q2() {

        palindrome = new ArrayList<>();
        palindrome.add(1);
        palindrome.add(2);
        palindrome.add(3);
        palindrome.add(4);
        palindrome.add(5);
        palindrome.add(6);
        palindrome.add(7);
        palindrome.add(8);
        palindrome.add(9);
        int start = 10;

    }

    //

    //static void BuidPalindrome() {
       // int len = nums.length;
}


