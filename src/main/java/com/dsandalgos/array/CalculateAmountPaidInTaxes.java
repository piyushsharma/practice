package com.dsandalgos.array;

import java.math.BigDecimal;

/**
 * You are given a 0-indexed 2D integer array brackets where brackets[i] = [upperi, percenti]
 * means that the ith tax bracket has an upper bound of upperi and is taxed at a rate of percenti.
 * The brackets are sorted by upper bound (i.e. upperi-1 < upperi for 0 < i < brackets.length).
 *
 * Tax is calculated as follows:
 *
 * The first upper0 dollars earned are taxed at a rate of percent0.
 * The next upper1 - upper0 dollars earned are taxed at a rate of percent1.
 * The next upper2 - upper1 dollars earned are taxed at a rate of percent2.
 * And so on.
 * You are given an integer income representing the amount of money you earned. Return the amount of money that you have to pay in taxes. Answers within 10-5 of the actual answer will be accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: brackets = [[3,50],[7,10],[12,25]], income = 10
 * Output: 2.65000
 * Explanation:
 * Based on your income, you have 3 dollars in the 1st tax bracket, 4 dollars in the 2nd tax bracket, and 3 dollars in the 3rd tax bracket.
 * The tax rate for the three tax brackets is 50%, 10%, and 25%, respectively.
 * In total, you pay $3 * 50% + $4 * 10% + $3 * 25% = $2.65 in taxes.
 * Example 2:
 *
 * Input: brackets = [[1,0],[4,25],[5,50]], income = 2
 * Output: 0.25000
 * Explanation:
 * Based on your income, you have 1 dollar in the 1st tax bracket and 1 dollar in the 2nd tax bracket.
 * The tax rate for the two tax brackets is 0% and 25%, respectively.
 * In total, you pay $1 * 0% + $1 * 25% = $0.25 in taxes.
 * Example 3:
 *
 * Input: brackets = [[2,50]], income = 0
 * Output: 0.00000
 * Explanation:
 * You have no income to tax, so you have to pay a total of $0 in taxes.
 */

public class CalculateAmountPaidInTaxes {

    public double calculateTax(int[][] brackets, int income) {
        double taxes = 0.0;
        int prev = 0;

        for(int[] bracket : brackets) {
            if(income <= 0) break;

            int taxedAmount = Math.min(income, bracket[0] - prev);
            taxes += (taxedAmount * (bracket[1] / 100d));

            income -= taxedAmount;
            prev = bracket[0];
        }
        return taxes;
    }

    public double calculateTaxV2(int[][] brackets, int income) {
        double taxes = 0.0;

        int i = 0;
        while(income > 0 && i < brackets.length) {
            int val = i == 0 ? brackets[i][0] : brackets[i][0] - brackets[i-1][0];
            double percent = brackets[i][1];
            int taxedAmount = Math.min(income, val);
            taxes += (taxedAmount * (percent / 100d));
            ++i;
            income -= val;
        }
        return roundWithMathRound(taxes, 5);
    }



    /**
     * Method 2: Using Math.round() with multiplication
     * Simple but may have precision issues with very large numbers
     */
    public static double roundWithMathRound(double value, int decimalPlaces) {
        double scale = Math.pow(10, decimalPlaces);
        return Math.round(value * scale) / scale;
    }

    public static double round(double value, int decimalPlaces) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }



}
