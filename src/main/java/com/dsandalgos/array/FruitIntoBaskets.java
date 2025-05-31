package com.dsandalgos.array;

import java.util.HashMap;
import java.util.Map;

/**
 * In a row of trees, the i-th tree produces fruit with type tree[i].
 *
 * You start at any tree of your choice, then repeatedly perform the following steps:
 *
 * Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
 * Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
 * Note that you do not have any choice after the initial choice of starting tree: you must perform step 1,
 * then step 2, then back to step 1, then step 2, and so on until you stop.
 *
 * You have two baskets, and each basket can carry any quantity of fruit,
 * but you want each basket to only carry one type of fruit each.
 *
 * What is the total amount of fruit you can collect with this procedure?
 * Example 1:
 *
 * Input: [1,2,1]
 * Output: 3
 * Explanation: We can collect [1,2,1].
 * Example 2:
 *
 * Input: [0,1,2,2]
 * Output: 3
 * Explanation: We can collect [1,2,2].
 * If we started at the first tree, we would only collect [0, 1].
 * Example 3:
 *
 * Input: [1,2,3,2,2]
 * Output: 4
 * Explanation: We can collect [2,3,2,2].
 * If we started at the first tree, we would only collect [1, 2].
 * Example 4:
 *
 * Input: [3,3,3,1,2,1,1,2,3,3,4]
 * Output: 5
 * Explanation: We can collect [1,2,1,1,2].
 * If we started at the first tree or the eighth tree, we would only collect 4 fruits.
 *
 *
 * Note:
 *
 * 1 <= tree.length <= 40000
 * 0 <= tree[i] < tree.length
 */

public class FruitIntoBaskets {

    // sliding window
    public int totalFruit(int[] tree) {

        if(tree == null) {
            return 0;
        }

        if(tree.length <= 2) {
            return tree.length;
        }

        int max = 0;
        Map<Integer, Integer> count = new HashMap<>();
        int start = 0;

        for(int i = 0; i < tree.length; ++i) {

            int tc = count.getOrDefault(tree[i], 0);
            count.put(tree[i], tc + 1);

            // note the while loop -- we keep incrementing left pointer until only 2 types are left
            while(count.size() >= 3) {

                int existingCount = count.get(tree[start]);
                if(existingCount == 1) {
                    count.remove(tree[start]);
                } else {
                    count.put(tree[start], existingCount - 1);
                }

                ++start;
            }

            max = Math.max(max, i - start + 1);
        }
        return max;

    }

    public int totalFruitWacky(int[] tree) {

        if(tree == null) {
            return 0;
        }

        if(tree.length <= 2) {
            return tree.length;
        }

        int max = 0;
        int type1 = -1;
        int type2 = -1;

        int type1End = -1;
        int type2End = -1;

        int count = 0;


        for(int i = 0; i < tree.length; ++i) {

            int type = tree[i];

            if(type1 == -1 || type1 == type) {
                type1 = type;
                type1End = i;

            } else if(type2 == -1 || type2 == type) {
                type2 = type;
                type2End = i;

            } else {

                if(type1End > type2End) {
                    count = type1End - type2End ;
                    type2 = type;
                    type2End = i;
                } else {
                    count = type2End - type1End ;
                    type1 = type;
                    type1End = i;
                }
            }
            ++count;
            max = Math.max(count, max);
        }
        return max;

    }




}
