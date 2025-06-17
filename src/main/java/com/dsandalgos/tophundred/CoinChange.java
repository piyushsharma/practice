package com.dsandalgos.tophundred;

import java.util.Arrays;

/**
 * Created by Piyush Sharma
 */

/*
You are given coins of different denominations and a total amount of money amount.
Write a function to compute the fewest number of coins that you need to make up that amount.

If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

Note:
You may assume that you have an infinite number of each kind of coin.
*/

public class CoinChange {

    // for each coin: consider counting it towards the amount, or discarding it
    // if we make it to the amount, get the minimum and return
    public int coinChangeRecursive(int[] coins, int amount) {
        int result = coinChange(coins, coins.length - 1, amount, 0);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private int coinChange(int[] coins, int i, int amount, int count) {

        if(i < 0 || amount < 0) return Integer.MAX_VALUE;

        if(amount == 0) return count;

        int discardThisCoin = coinChange(coins, i - 1, amount, count);
        int useThisCoin = coinChange(coins, i, amount - coins[i], count + 1);

        return Math.min(discardThisCoin, useThisCoin);
    }

    // for each coin: consider counting it towards the amount, or discarding it
    // if we make it to the amount, get the minimum and return
    public int coinChangeDPTopDown(int[] coins, int amount) {
        if(amount <= 0) return 0;
        return coinChange(coins, amount, new int[amount + 1]);
    }

    private int coinChange(int[] coins, int amountRem, int[] cache) {
        // nothing more to do
        if(amountRem == 0) return 0;

        if(amountRem < 0) return -1;

        if(cache[amountRem] != 0) return cache[amountRem];
        int min = Integer.MAX_VALUE;
        for(int coin : coins) {
            int res = coinChange(coins, amountRem - coin, cache);
            if(res >= 0 && res < min) {
                // use this coin
                min = 1 + res;
            }
        }
        cache[amountRem] = min == Integer.MAX_VALUE ? -1 : min;
        return cache[amountRem];
    }



    // let dp[i] denote the minimum number of coins required to make amount i
    // dp[i + coin] = min (dp[i + coin], dp[i] + 1)

    // O(number of coins * amount)
    public int coinChangeDP(int[] coins, int amount) {
        // initialize an array with something > amount
        // Note: we didn't use INT_MAX, as we may do INT_MAX + 1 causing overflow
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);

        // for 0 amount, you need 0 coins
        dp[0] = 0;
        for(int i = 1; i <= amount; ++i) {
            for(int j = 0; j < coins.length; ++j) {

                if(coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i-coins[j]] + 1);
                }
            }
        }

        return dp[amount] >= max ? -1 : dp[amount];
    }

    public int changeR(int amount, int[] coins) {
        if(amount == 0) {
            return 1;
        }
        int[] result = new int[1];
        changeR(coins, 0, 0, amount, result);
        return result[0];
    }

    private void changeR(int[] coins, int index, int curSum, int amount, int[] result) {

        if(curSum > amount || index >= coins.length) {
            return;
        }

        if(curSum == amount) {
            result[0] += 1;
            return;
        }
        changeR(coins, index, curSum + coins[index], amount, result);
        changeR(coins, index + 1, curSum, amount, result);
    }


    public static void main(String[] args) {
        CoinChange c = new CoinChange();
//        System.out.println(c.coinChange(new int[]{3,4,5}, 11));
//        System.out.println(c.coinChange(new int[]{3,7,405,436}, 8839));
//        System.out.println(c.coinChange(new int[]{1,2147483647}, 2));
        System.out.println(c.changeR(500, new int[]{3,5,7,8,9,10,11}));
    }

    /*
    Too many repeat computations in the recursive solution, must use DP!!
    f(c, 2, 11, 0)

    -> f(c, 1, 11, 0)
        -> f(c, 0, 11, 0)
            -> END, END, f(c, 0, 8, 1) -> f(c, 0, 5, 2) -> f(c, 0, 2, 3) -> END

        -> f(c, 1, 6, 1)
            -> f(c, 0, 2, 2) -> END
            -> f(c, 1, 2, 2) -> f(c, 0, 2, 2) -> END

    -> f(c, 2, 6, 1)

     */

}


