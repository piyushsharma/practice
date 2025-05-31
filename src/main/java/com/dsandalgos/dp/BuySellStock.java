package com.dsandalgos.dp;

/**
 * Created by Piyush Sharma
 */

/*

Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction
(ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Example 1:
Input: [7, 1, 5, 3, 6, 4]
Output: 5
max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)

Example 2:
Input: [7, 6, 4, 3, 1]
Output: 0
In this case, no transaction is done, i.e. max profit = 0.

*/


public class BuySellStock {

    // dp
    // At each level continue to update the minimum buying price
    // also, keep updating he max profit, in case the buy price has changed and the
    // future higher price gives larger profit
    public int maxProfit(int[] prices) {
        if(prices.length == 0) return 0;

        int minBuyPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        // running min and max
        for(int i = 0; i < prices.length; ++i) {
            minBuyPrice = Math.min(minBuyPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minBuyPrice);
        }

        return maxProfit;
    }


    // Brute force
    public int maxProfitBF(int[] prices) {
        if(prices.length == 0) return 0;

        int end = prices.length;
        int profitBuyHere, maxProfit = 0;

        for(int i = 0; i < end; ++i) {
            profitBuyHere = maxProfit(prices, i + 1, end, prices[i]);
            if(profitBuyHere > maxProfit) maxProfit = profitBuyHere;
        }
        return maxProfit;
    }

    public int maxProfit(int[] prices, int start, int end, int buyPrice) {
        int profit = 0;
        for(int i = start; i < end; ++i) {
            if(prices[i] - buyPrice > profit) {
                profit = prices[i] - buyPrice;
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        BuySellStock b = new BuySellStock();
        System.out.println(b.maxProfit(new int[]{7,1,5,6,3,4}));

    }

}
