package com.dsandalgos.tophundred;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
You are climbing a stair case. It takes n steps to reach to the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
*/

public class ClimbingStairs {


    public int climbStairsRecursion(int n) {
        // just to handle the base case
        if(n <= 0)
            return 0;

        if(n <= 2) return n;

        return climbStairsRecursion(n - 1) + climbStairsRecursion(n - 2);
    }

    // Using DP
    public int climbStairs(int n) {
        if(n <= 2) return n;

        // make sure n > 2
        int[] cache = new int[n + 1];
        // 0 index => 0 stair;
        cache[0] = 0;
        // 1 index => 1 stair
        cache[1] = 1;
        // Note the exception cache[2] != cache[0] + cache[1] because 0 is a base condition, we have to handle it before
        cache[2] = 2;

        // note that loop runs till i == n
        for(int i = 3; i <= n; ++i)
            cache[i] = cache[i-1] + cache[i-2];

        return cache[n];
    }


    // f(n) = f(n-1) + f(n-2)
    // exactly like fibonacci
    public int climbStairsFibonacci(int n) {
        if (n <= 2) return n;
        int secondLast = 1;
        int last = 2;
        int next = 0;
        // note that loop runs till i == n
        for(int i = 3; i <= n; ++i) {
            next = last + secondLast;
            secondLast = last;
            last = next;
        }
        return next;
    }

    public static void main(String[] args) {
        ClimbingStairs c = new ClimbingStairs();
        for(int i = 0; i < 10; ++i) {
            System.out.println(c.climbStairs(i));
            System.out.println(c.climbStairsRecursion(i));
            System.out.println(c.climbStairsFibonacci(i));
        }
    }

}
