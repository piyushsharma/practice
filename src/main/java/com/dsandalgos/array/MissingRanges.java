package com.dsandalgos.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piyush Sharma
 */

/*
Given a sorted integer array where the range of elements are [0, 99] inclusive, return its missing ranges.
For example, given [0, 1, 3, 50, 75], return [“2”, “4->49”, “51->74”, “76->99”]
*/

public class MissingRanges {

    // long required for all edge cases of INT_MAX and INT_MIN
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        long prev = (long)lower - 1; // int -> long
        long curr = 0;
        for(int i = 0; i <= nums.length; i++) {
            curr = i == nums.length ? (long)upper + 1: (long)nums[i]; // int -> long
            if(prev + 2 == curr) {
                res.add((prev + 1) + "");
            } else if(prev + 2 < curr) {
                res.add((prev + 1) + "->" + (curr - 1));
            }
            prev = curr;
        }
        return res;
    }

    public static void main(String[] args) {
        MissingRanges m = new MissingRanges();

        int[] a = new int[]{1, 3, 4, 10, 20, 50};
        List<String> r = m.findMissingRanges(a, 0, 99);
        for(String s : r) {
            System.out.println(s);
        }
    }


}
