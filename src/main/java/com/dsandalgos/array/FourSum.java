package com.dsandalgos.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
Find all unique quadruplets in the array which gives the sum of target.

Note: The solution set must not contain duplicate quadruplets.
For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]

*/

public class FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums.length < 4)
            return result;

        // sort the array
        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 3; ++i) {
            if(i != 0 && nums[i-1] == nums[i]) {
                continue;
            }

            for(int j = i+1; j < nums.length - 2; ++j) {

                if(j != i+1 && nums[j-1] == nums[j]) {
                    continue;
                }

                int start = j + 1;
                int end = nums.length - 1;

                while (start < end) {
                    int sum = nums[i] + nums[start] + nums[end] + nums[j];

                    if(sum < target) {
                        ++start;

                    } else if (sum > target) {
                        --end;

                    } else {

                        result.add(Arrays.asList(nums[i], nums[start], nums[end], nums[j]));

                        // we already considered given start and end, now look for other solutions
                        // with the same nums[j]
                        ++start;
                        --end;

                        //avoid duplicate solutions
                        // do not add to solution for all same values when moving the left index
                        while (start < end && nums[start - 1] == nums[start])
                            start++;

                        // do not add to solution for all same values when moving the right index inwards
                        while (start < end && nums[end] == nums[end + 1])
                            end--;

                    }
                }
            }
        }

        return result;
    }


    public static void main(String[] args) {
        FourSum s = new FourSum();

        int[] test = {-1, 0, 1, 0, 2, -2};
        List<List<Integer>> result = s.fourSum(test, 1);
        for (List<Integer> l : result) {
            for (int i : l) {
                System.out.printf("%d   ", i);
            }
            System.out.println();
        }
    }
}
