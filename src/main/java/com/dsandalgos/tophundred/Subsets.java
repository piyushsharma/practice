package com.dsandalgos.tophundred;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: nums = [1,2,3]
 * Output:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */

public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        subsets(result, new ArrayList<>(), 0, nums);
        result.add(new ArrayList<>());
        return result;
    }

    private void subsets(List<List<Integer>> result, ArrayList<Integer> cur, int index, int[] nums) {
        if(index == nums.length)
            return;

        // include element at index
        cur.add(nums[index]);
        result.add(new ArrayList<>(cur));
        subsets(result, cur, index + 1, nums);

        // exclude element at index and call again
        cur.remove(cur.size() - 1);
        subsets(result, cur, index + 1, nums);
    }

    private static void getAllSubsets(List<List<Integer>> result, List<Integer> current, int index, int[] array) {
        for (int i = index; i < array.length; i++) {
            // Remove this 'if' block if duplicates are needed
            if (i > index && array[i] == array[i - 1])
                continue;
            ArrayList<Integer> newSubset = new ArrayList<>(current);
            newSubset.add(array[i]);
            result.add(newSubset);
            getAllSubsets(result, newSubset, i + 1, array);
        }
    }

    private static List<List<Integer>> getAllSubsets(int[] array) {
        List<List<Integer>> result = new ArrayList<>();
        getAllSubsets(result, new ArrayList<>(), 0, array);
        result.add(new ArrayList<>());
        return result;
    }

    public static void main(String[] args) {
//        int[] array = {1, 2, 2};
//        Arrays.sort(array);
//        List<List<Integer>> res = getAllSubsets(array);
//        for (List<Integer> list : res) {
//            System.out.println(list);
//        }


        int[] array = {1, 2, 3};
        List<List<Integer>> result = new Subsets().subsets(array);
        for (List<Integer> list : result) {
            System.out.println(list);
        }


    }
}
