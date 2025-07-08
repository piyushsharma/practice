package com.dsandalgos.binarysearch;

public class KokoBananas {

    public int minEatingSpeed(int[] piles, int h) {
        int low = 1;

        int high = 0;
        for(int p : piles) {
            high = Math.max(p, high);
        }

        while(low < high) {

            int mid = low + (high - low)/2;
            int hourspent = 0;

            for(int p : piles) {
                hourspent += Math.ceil( (double) p / mid);
            }
            if(hourspent <= h) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return high;

    }
}
