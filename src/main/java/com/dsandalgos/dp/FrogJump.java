package com.dsandalgos.dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone.
 * The frog can jump on a stone, but it must not jump into the water.
 *
 * Given a list of stones' positions (in units) in sorted ascending order,
 * determine if the frog is able to cross the river by landing on the last stone.
 * Initially, the frog is on the first stone and assume the first jump must be 1 unit.
 *
 * If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units.
 * Note that the frog can only jump in the forward direction.
 *
 * Note:
 *
 * The number of stones is ≥ 2 and is < 1,100.
 * Each stone's position will be a non-negative integer < 231.
 * The first stone's position is always 0.
 * Example 1:
 *
 * [0,1,3,5,6,8,12,17]
 *
 * There are a total of 8 stones.
 * The first stone at the 0th unit, second stone at the 1st unit,
 * third stone at the 3rd unit, and so on...
 * The last stone at the 17th unit.
 *
 * Return true. The frog can jump to the last stone by jumping
 * 1 unit to the 2nd stone, then 2 units to the 3rd stone, then
 * 2 units to the 4th stone, then 3 units to the 6th stone,
 * 4 units to the 7th stone, and 5 units to the 8th stone.
 * Example 2:
 *
 * [0,1,2,3,4,8,9,11]
 *
 * Return false. There is no way to jump to the last stone as
 * the gap between the 5th and 6th stone is too large.
 */

public class FrogJump {


    public boolean canCross(int[] stones) {

        if(stones == null) {
            return false;
        }
        if(stones.length <= 1) {
            return true;
        }

        // store val to -> all jumps that lead to this val
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(Integer in : stones) {
            map.put(in, new HashSet<>());
        }

        // reach first stone with 0 step -- starting point
        map.get(stones[0]).add(0);

        for(int i = 0; i < stones.length; ++i) {

            // jumps to get to this index
            Set<Integer> jumps = map.get(stones[i]);

            for(int prevJump : jumps) {
                // next possible jumps
                for(int k = prevJump - 1; k <= prevJump + 1; ++k) {

                    // note k > 0 and not 0, as 0 unit jump will cause ConcurrentModificationException
                    // works with problem definition
                    if(k > 0 && map.containsKey(stones[i] + k)) {
                        map.get(stones[i] + k).add(k);
                    }
                }
            }
        }

        return map.get(stones[stones.length - 1]).size() > 0;
    }




    public boolean canCrossBT(int[] stones) {

        if(stones == null) {
            return false;
        }
        if(stones.length <= 1) {
            return true;
        }

        // first jump must be 1
        if(stones[0] + 1 != stones[1]) {
            return false;
        }

        return canCrossBT(stones, 1, 1);
    }

    public boolean canCrossBT(int[] stones, int curIndex, int prevJump) {
        if(curIndex == stones.length - 1) {
            return true;
        }

        int[] possibleJumpUnits = {prevJump - 1, prevJump, prevJump + 1};

        int nextIndex = curIndex + 1;
        int curVal = stones[curIndex];

        while(nextIndex < stones.length) {

            if(curVal + possibleJumpUnits[2] < stones[nextIndex]) {
                break;
            }

            for(int jumpUnit : possibleJumpUnits) {
                if(curVal + jumpUnit == stones[nextIndex]) {
                    if(canCrossBT(stones, nextIndex, jumpUnit)) {
                        return true;
                    }
                }
            }

            ++nextIndex;
        }

        return false;
    }

    public boolean canCrossMemoization(int[] stones) {

        if(stones == null) {
            return false;
        }
        if(stones.length <= 1) {
            return true;
        }

        // first jump must be 1
        if(stones[0] + 1 != stones[1]) {
            return false;
        }

        int n = stones.length;
        int[][] dp = new int[n][n];

        return canCrossMemoization(stones, 1, 1, dp) == 2;
    }

    public int canCrossMemoization(int[] stones, int curIndex, int prevJump, int[][] dp) {
        if(dp[curIndex][prevJump] > 0) {
            return dp[curIndex][prevJump];
        }

        int[] possibleJumpUnits = {prevJump - 1, prevJump, prevJump + 1};

        int nextIndex = curIndex + 1;
        int curVal = stones[curIndex];

        while(nextIndex < stones.length) {

            if(curVal + possibleJumpUnits[2] < stones[nextIndex]) {
                break;
            }

            for(int jumpUnit : possibleJumpUnits) {
                if(curVal + jumpUnit == stones[nextIndex]) {
                    if(canCrossMemoization(stones, nextIndex, jumpUnit, dp) == 2) {
                        dp[nextIndex][jumpUnit] = 2;
                        return 2;
                    }
                }
            }

            ++nextIndex;
        }

        if(curIndex == stones.length - 1) {
            dp[curIndex][prevJump] = 2;
        } else {
            dp[curIndex][prevJump] = 1;
        }
        return dp[curIndex][prevJump];
    }


}

