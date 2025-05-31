package com.dsandalgos.tophundred;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given a set of candidate numbers (C) and a target number (T),
find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.
For example, given candidate set 2,3,6,7 and target 7, a solution set is:
[7]
[2, 2, 3]

http://n00tc0d3r.blogspot.com/2013/01/combination-sum.html
*/

public class CombinationSum {

    public static List<List<Integer>> combinationSumRecursion(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        combinationSum(candidates, 0, target, new ArrayList<>(), results);
        return results;
    }

    private static void combinationSum(int[] candidates, int start, int target, ArrayList<Integer> path,
                                List<List<Integer>> results) {

        if (target == 0) {
            List<Integer> res = new ArrayList<>(path);
            results.add(res);
            return;
        }

        if (start >= candidates.length || target < 0) return;

        // include
        path.add(candidates[start]);
        combinationSum(candidates, start, target - candidates[start], path, results);

        // exclude
        path.remove(path.size() - 1);
        combinationSum(candidates, start + 1, target, path, results);
    }

    /*
    Since a number can be reused, we can assume that the candidate set must not contain duplicates.

    We also assume that all candidates and target are positive integers.
    Why? We need to iterate all possibilities, if negatives are allowed,
    it may fall into infinite loop. E.g. {2, -2, 3, 4}, 7.

    Let's use the given example to show how to solve the problem.
    We start with the target 7, a set {2, 3, 6, 7} and an empty list to keep track of the "path".

        Path = {2},  new target 5=7-2,  {2, 3, 6, 7} (skip the large numbers from our candidate set);
        Path = {2, 2},  new target 3,  {2, 3};
        Path = {2, 2, 2},  new target 1,  {};
        Path = {2, 2, 3}, hit target! Store the resulting path in our res set and continue with the next iteration.
        Path = {2, 3},  new target 2,  {3, 6, 7}
        Path = {3},  new target 4,  {3, 6, 7}
        Path = {3, 3},  new target 1,  {}
        Path = {6},  new target 1,  {}
        Path = {7}, hit target! Store into results set.

        As shown above, in each iteration, we remove the prior ones from candidate set
        so as to avoid duplicate results, but leave the current one in the set so as to reuse it when possible.

        Also, notice that sorting is not necessary.
        We skip large candidates if they are bigger than target,
        but it's just an improvement of the running time performance and it won't even reduce big-O complexity.
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(candidates);
        addUp(candidates, 0, target, new ArrayList<Integer>(), results);
        return results;
    }

    private static void addUp(int[] candidates, int start, int target, ArrayList<Integer> path,
                              List<List<Integer>> results) {
        if (start < 0 || target < 0) return;

        if (0 == target) {
            ArrayList<Integer> res = new ArrayList<>(path);
            results.add(res);
        } else {
            for (int i = start; i < candidates.length && candidates[i] <= target; ++i) {
                // if (candidates[i] > target) continue; // if we don't sort the data at the beginning, we skip large numbers here
                path.add(candidates[i]);
                addUp(candidates, i, target - candidates[i], path, results);
                path.remove(path.size() - 1);
            }
        }
    }

    /*
    Given a collection of candidate numbers (C) and a target number (T),
    find all unique combinations in C where the candidate numbers sums to T.
    Each number in C may only be used once in the combination.
    For example, given candidate set 10,1,2,7,6,1,5 and target 8, a solution set is:
        [1, 7]
        [1, 2, 5]
        [2, 6]
        [1, 1, 6]


        Notice that the differences between the two variaties are:
        there may exist duplicate numbers in candidate set;
        each candidate can by used at most once.
        The former one tells us that we need to skip duplicates when we iterate through candidates;
        The latter means we should start with the next candidate during iterations.
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(candidates);
        addUp2(candidates, 0, target, new ArrayList<Integer>(), results);
        return results;
    }

    private void addUp2(int[] candidates, int start, int target, ArrayList<Integer> path,
                        List<List<Integer>> results) {
        if (start < 0 || target < 0) return;

        if (0 == target) {
            ArrayList<Integer> res = new ArrayList<>(path);
            results.add(res);
        } else {
            for (int i = start; i < candidates.length && candidates[i] <= target; ++i) {
                if (i > start && candidates[i] == candidates[i - 1]) continue; // skip duplicates
                path.add(candidates[i]);
                addUp2(candidates, i + 1, target - candidates[i], path, results);
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {2, 1, 5};
        Arrays.sort(array);
        int target = 8;
        List<List<Integer>> result = combinationSumRecursion(array, target);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }
}
