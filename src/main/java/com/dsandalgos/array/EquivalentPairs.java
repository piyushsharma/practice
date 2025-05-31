package com.dsandalgos.array;

import java.util.*;

/**
 * Created by Piyush Sharma
 */

/*
Given an array A of integers, find the index of values that satisfy A + B = C + D,
where A,B,C & D are integers values in the array. Find all combinations of quadruples.

output all indexes of quadruple into format List<List<Integer>> indexofQuadruples
*/


public class EquivalentPairs {


    class Pair {
        int x;
        int y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // O(n^2), space => O(n^2)?
    // assumed no repetition of numbers
    public List<List<Integer>> indexOfQuadruples(int[] arr) {

        List<List<Integer>> result = new ArrayList<>();
        if(arr.length < 4) return result;

        Map<Integer, List<Pair>> map = new HashMap<>();

        // go through all sums and store in a Map
        for(int i = 0; i < arr.length; ++i) {
            for(int j = i + 1; j < arr.length; ++j) {

                int sum = arr[i] + arr[j];

                if(!map.containsKey(sum)) {
                    map.put(sum, new ArrayList<>());
                }

                List<Pair> existingPairs = map.get(sum);
                if(existingPairs.size() > 0) {
                    // get all previous pairs; add the new pair and the existing ones as solutions
                    for(Pair prev : existingPairs) {
                        result.add(Arrays.asList(prev.x, prev.y, i, j));
                    }
                }
                existingPairs.add(new Pair(i, j));
            }
        }

        return result;
    }

    public static void main(String[] args) {

        EquivalentPairs equivalentPairs = new EquivalentPairs();

        int[] test = new int[]{0,1,2,3,4,5};
        List<List<Integer>> result = equivalentPairs.indexOfQuadruples(test);

        System.out.println(result.toString());

    }


}


