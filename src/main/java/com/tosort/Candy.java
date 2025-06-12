package com.tosort;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
There are N children standing in a line. Each child is assigned a rating value.

        You are giving candies to these children subjected to the following requirements:

        Each child must have at least one candy.
        Children with a higher rating get more candies than their neighbors.
        What is the minimum candies you must give?
*/

public class Candy {

    public int candy(int[] ratings) {
        if(ratings.length == 0)
            return 0;

        if(ratings.length == 1)
            return 1;

        int[] lToR = new int[ratings.length];
        int[] rToL = new int[ratings.length];

        for(int i = 0; i < ratings.length; ++i) {
            lToR[i] = 1;
            rToL[i] = 1;
        }

        for(int i = 1; i < ratings.length; ++i) {
            if(ratings[i] > ratings[i-1])
                lToR[i] = 1 + lToR[i-1];
        }

        for(int i = ratings.length - 2; i >= 0; --i) {
            if(ratings[i] > ratings[i+1])
                rToL[i] = 1 + rToL[i+1];
        }

        int sum = 0;
        for(int i = 0; i < ratings.length; ++i) {
            sum += Math.max(lToR[i], rToL[i]);
        }
        return sum;
    }
}
