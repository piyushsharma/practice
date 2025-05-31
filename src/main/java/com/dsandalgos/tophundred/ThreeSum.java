package com.dsandalgos.tophundred;

import java.util.*;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
Find all unique triplets in the array which gives the sum of zero.

Note:
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
The solution set must not contain duplicate triplets.
    For example, given array S = {-1 0 1 2 -1 -4},

    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)
*/

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums.length < 3)
            return result;

        // sort the array
        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 2; ++i) {

            // this is to avoid running the loop when nums[i-1] == nums[i]
            // in sorted array nums[i-1] should be always < nums[i] except when they are equal
            // we do not want to run the whole loop when they are equal, since nums[i-1] has already
            // added those solutions
            if (i == 0 || nums[i-1] < nums[i]) {

                int start = i + 1;
                int end = nums.length - 1;

                while (start < end) {
                    int sum = nums[i] + nums[start] + nums[end];
                    if (sum == 0) {
                        result.add(Arrays.asList(nums[i], nums[start], nums[end]));

                        // we already considered given start and end, now look for other solutions
                        // with the same nums[i]
                        ++start;
                        --end;

                        //avoid duplicate solutions
                        // do not add to solution for all same values when moving the left index
                        while (start < end && nums[start] == nums[start - 1])
                            start++;

                        // do not add to solution for all same values when moving the right index inwards
                        while (start < end && nums[end] == nums[end + 1])
                            end--;

                    } else if (sum < 0) {
                        ++start;
                    } else {
                        --end;
                    }
                }
            }
        }

        return result;
    }

    public List<List<Integer>> threeSumV2(int[] nums, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length < 3) {
            return result;
        }

        Set<List<Integer>> uniqueResult = new HashSet<>();
        // sort the array
        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 2; ++i) {

            int start = i + 1;
            int end = nums.length - 1;

            while (start < end) {
                if(nums[start] + nums[end] + nums[i] < k) {
                    ++start;
                } else if (nums[start] + nums[end] + nums[i]  > k) {
                    --end;
                } else {
                    uniqueResult.add(Arrays.asList(nums[i], nums[start], nums[end]));
                    ++start;
                    --end;
                }
            }
        }
        for(List<Integer> r : uniqueResult) {
            result.add(r);
        }
        return result;
    }

    // using hashmap, times out on leetcode, not that efficient
    public List<List<Integer>> threeSumA(int[] nums) {
        Set<List<Integer>> result = new HashSet<List<Integer>>();
        List<List<Integer>> listResult = new ArrayList<List<Integer>>();
        if(nums.length < 3)
            return listResult;

        Arrays.sort(nums);
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();

        for(int i = 0; i < nums.length; ++i) {
            m.put(nums[i], i);
        }

        for(int i = 0; i < nums.length - 1; ++i) {
            for(int j = i + 1; j < nums.length; ++j) {
                int s = -(nums[i] + nums[j]);

                Integer v = m.get(s);
                if (v != null && i != v && j != v) {
                    int v1 = nums[i];
                    int v2 = nums[j];
                    int v3 = nums[v];
                    int temp = 0;
                    // sort ascending order
                    if (v1 > v2) {
                        temp = v1;
                        v1 = v2;
                        v2 = temp;
                    }
                    if (v2 > v3) {
                        temp = v2;
                        v2 = v3;
                        v3 = temp;
                    }
                    // v3 now is the largest element
                    // just compare v1 and v2
                    if (v1 > v2) {
                        temp = v1;
                        v1 = v2;
                        v2 = temp;
                    }
                    result.add(Arrays.asList(v1, v2, v3));
                }
            }
        }

        for (List<Integer> l : result) {
            listResult.add(l);
        }
        return listResult;
    }

    public static void main(String[] args) {
        ThreeSum s = new ThreeSum();

//        int[] test = {-1, 0, 1, 2, -1, -4};
//        int[] test = {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
//        int[] test = {0,0,0};
//        int[] test = {-2,0,1,1,2};
//        List<List<Integer>> res = s.threeSum(test);
//        for (List<Integer> l : res) {
//            for(int i : l) {
//                System.out.printf("%d   ", i);
//            }
//            System.out.println();
//        }


        int[] test = {-2,0,1,1,2};
        List<List<Integer>> result = s.threeSumV2(test, 2);
        for (List<Integer> l : result) {
            for(int i : l) {
                System.out.printf("%d   ", i);
            }
            System.out.println();
        }
    }

}
