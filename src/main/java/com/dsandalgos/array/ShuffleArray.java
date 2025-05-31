package com.dsandalgos.array;

import java.util.Random;

/**
 * Created by Piyush Sharma
 */


public class ShuffleArray {



    /*
    Given an array with possible repeated numbers, randomly output the index of a given number.
    Example: given [1,2,3,3,3], 3 => output the indexes for the number 3 (2,3,or 4) with equal probability.
     */

    // Idea is that the probability should be = 1/count;
    // eg. for 4 occurrences => 1/4 => so if(nextdouble is <= 1/probability) return that index
    public int randomIndexOfNumber(int[] nums, int x) {
        int index = -1;
        Random r = new Random();
        int count = 0;

        for(int i = 0; i < nums.length; ++i) {

            if(nums[i] == x) {
                ++count;
                if(r.nextDouble() <= 1.0/count) {
                    index = i;
                }
            }
        }

        return index;
    }

    public static void main(String[] args) {

        ShuffleArray shuffleArray = new ShuffleArray();

        int[] a = new int[]{1,2,3,3,3,4,3};

        int p, q, r, s;
        p = q = r = s = 0;
        double sampleSize = 20000.0;
        for(int i = 0; i < (int)sampleSize; ++i) {
            if(shuffleArray.randomIndexOfNumber(a, 3) == 2) ++p;
            if(shuffleArray.randomIndexOfNumber(a, 3) == 3) ++q;
            if(shuffleArray.randomIndexOfNumber(a, 3) == 4) ++r;
            if(shuffleArray.randomIndexOfNumber(a, 3) == 6) ++s;
        }

        System.out.printf("%f %f %f %f\n", p/sampleSize, q/sampleSize, r/sampleSize, s/sampleSize);

    }


}
