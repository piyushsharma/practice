package com.dsandalgos.math;

import java.util.Random;

public class RandomPickWithWeight {

    int[] prefixSums;
    Random rand;
    int total;

    public RandomPickWithWeight(int[] w) {
        prefixSums = new int[w.length];
        total = 0;
        for(int i = 0; i < w.length; ++i) {
            total += w[i];
            prefixSums[i] = total;
        }
        rand = new Random();
    }

    public int pickIndex() {
        int position = rand.nextInt(total);
        int i = 0;
        for(; i < prefixSums.length; ++i) {
            int lower = i == 0 ? 0 : prefixSums[i-1];
            int upper = prefixSums[i];

            if(position >= lower && position < upper) {
                break;
            }
        }
        return i;
    }
}
