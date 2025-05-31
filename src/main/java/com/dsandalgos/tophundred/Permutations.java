package com.dsandalgos.tophundred;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piyush Sharma
 */

/*

Given a collection of distinct numbers, return all possible permutations.
For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
                                [1,                          2,                           3]
                                /                            |                               \
                               /                             |                                \
          Swap 1 with 1       /                              |Swap 1 with 2                    \ Swap 1 with 3
                             /                               |                                  \
             1 is fixed [1, 2, 3]             2 is fixed [2, 1, 3]                  3 is fixed [3, 2, 1]
                         /    \                         /       \                              /        \
       Swap 2 with 2    /      \ Swap 2 with 3         /         \             Swap 2 with 2  /          \ Swap 2 with 1
                       /        \                     /           \                          /            \
                    [1,2,3]    [1,3,2]             [2,1,3]      [2,3,1]                  [3,2,1]       [3,1,2]
                (Two positions fixed, so the third slot filled with the remaining number, no more permutations)
*/


public class Permutations {


    // O(n * n!)
    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        permute(nums, 0, nums.length - 1, result);
        return result;
    }

    private void permute(int[] nums, int start, int end, List<List<Integer>> result) {

        if(start == end) {
            List<Integer> l = new ArrayList<Integer>();
            for(int val : nums) l.add(val);
            result.add(l);
            return;
        }

        // Everything before start is fixed. Start swapping start with every element
        // in each new array formed, everything until start (including start) is fixed
        // then call recursively for the other elements
        for(int i = start; i <= end; ++i) {
            // swap start with i (all the remaining elements)
            swap(nums, i, start);

            // now start is considered fixed, call recursively to permute elements from start + 1 to end
            permute(nums, start + 1, end, result);

            // undo the swap so that the loop starts from the original array
            swap(nums, start, i);
        }
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // Permutation by creating substrings and forming
    // new strings. This one eats lots of space.
    private List<String> permute(String a) {
        List<String> result = new ArrayList<>();
        permute("", a, result);
        return result;
    }

    private void permute(String prefix, String suffix, List<String> result) {
        if(suffix.length() == 0) {
            result.add(prefix);
            return;
        }
        for(int i = 0; i < suffix.length(); ++i) {
            char ch = suffix.charAt(i);
            permute(prefix + ch, suffix.substring(0,i) + suffix.substring(i+1), result);
        }
    }

    private List<String> permuteStr(String str) {
        char[] buffer = new char[str.length()];
        boolean[] used = new boolean[str.length()];
        List<String> result = new ArrayList<>();
        permuteStr(0, str, buffer, used, result);
        return result;
    }

    private void permuteStr(int index, String str, char[] buffer, boolean[] used, List<String> result) {
        if(index == buffer.length) {
            String perm = new String(buffer);
            result.add(perm);
            return;
        }
        for(int i = 0; i < str.length(); i++) {
            if(!used[i]) {
                buffer[index] = str.charAt(i);
                used[i] = true;
                permuteStr(index + 1, str, buffer, used, result);
                used[i] = false;
            }
        }
    }


    // won't work if we have duplicates
    public List<List<Integer>> getPermutations(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return result;
        }
        // could sort for pretty print
        //  Arrays.sort(nums);
        dfs(nums, new ArrayList<>(), result);
        return result;
    }


    private void dfs(int[] nums, List<Integer> path, List<List<Integer>> result) {
        if(path.size() == nums.length){
            result.add(new ArrayList<>(path));
            return;
        }

        for(int num : nums){
            if(!path.contains(num)){
                path.add(num);
                dfs(nums, path, result);
                path.remove(path.size()-1);
            }
        }
    }

    public static void main(String[] args) {

        int[] nums = new int[]{1,1,3};

        List<List<Integer>> result = new Permutations().permute(nums);
//        List<List<Integer>> res = new Permutations().getPermutations(nums);
        for(List<Integer> l : result) {
            for(Integer i : l) {
                System.out.printf("%d ", i);
            }
            System.out.println();
        }


        System.out.println("============================================");
        List<String> strP = new Permutations().permute("ABC");
        for(String s : strP) {
            System.out.println(s);
        }
    }


    /**
     *
     // Given a collection of distinct numbers, return all possible permutation

     For example,
     [1,2,3] have the following permutations:
     [
     [1,1,3],
     [1,3,2],
     [2,1,3],
     [2,3,1],
     [3,1,2],
     [3,2,1]
     ]

     [1,1,2] have the following permutations:
     [
     [1,1,2],
     [1,2,1],
     [2,1,1]
     ]


    class Permutation{

     public List<List<Integer>> getPermutations2(int[] nums){

            List<List<Integer>> res = new ArrayList<>();

            if(nums == null || nums.length == 0){
                return res;
            }

            Arrays.sort(nums);

            Map<Integer, Integer> map = new HashMap<>();

            for(int num: nums){
                map.put(num, map.getOrDefault(num, 0) + 1);
            }




            dfs2(nums, 0, new ArrayList<>(), map, res);

            return res;

        }


        private void dfs2(int[] nums, int index, List<Integer> path,
                          Map<Integer, Integer> map,
                          List<List<Integer>> res){

            if(path.size() == nums.length){

                res.add(new ArrayList<>(path));
                return;
            }


            for(int j = 0; j < nums.length; j++){

                int count = 0, num = nums[j];

                if(j > 0 && nums[j] == nums[j-1]){
                    continue;
                }


                for(int i = 0 ; i < index; i++){
                    if(path.get(i) == num){
                        ++count;
                    }
                }

                if(count >= map.get(num)){
                    continue;
                }

                path.add(num);
                dfs2(nums, index + 1, path, map, res);
                path.remove(path.size()-1);

            }

        }

        public static void main(String[] args){
            Permutation p = new Permutation();
            int[] nums = {2, 2, 1};

            List<List<Integer>> res = p.getPermutations2(nums);

            for(List<Integer> aList: res){
                for(int num : aList){
                    System.out.print(Integer.toString(num));
                }

                System.out.println();
            }

        }

     }

     */


}
