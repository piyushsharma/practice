package com.tosort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a sorted integer array without duplicates, return the summary of its ranges.
For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 */

public class SummaryRanges {

    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<String>();

        int i = 0;
        while(i < nums.length) {
            int start = i;
            int startVal = nums[i];

            while(i < nums.length && nums[i] == startVal) {
                ++i;
                ++startVal;
            }
            if(i-1 != start) {
                result.add(Integer.toString(nums[start]) + "->" + Integer.toString(nums[i - 1]));
            } else {
                result.add(Integer.toString(nums[start]));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] x = {1,2,3,4,5,6,7,9,11};
//        int[] x = {0,1,2,4,5,7};

        List<String> result = new SummaryRanges().summaryRanges(x);
        for(String s : result) {
            System.out.println(s);
        }

    }

}
