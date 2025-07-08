package com.dsandalgos.dp;

public class MaxPointtsFromCards {

    class Solution {
        public int maxScore(int[] cardPoints, int k) {
            int n = cardPoints.length;

            int[] frontSetOfCards = new int[k + 1];
            int[] rearSetOfCards = new int[k + 1];

            for (int i = 0; i < k; i++) {
                frontSetOfCards[i + 1] = cardPoints[i] + frontSetOfCards[i];
                rearSetOfCards[i + 1] = cardPoints[n - i - 1] + rearSetOfCards[i];
            }

            int maxScore = 0;
            // Each i represents the number of cards we take from the front.
            for (int i = 0; i <= k; i++) {
                int currentScore = frontSetOfCards[i] + rearSetOfCards[k - i];
                maxScore = Math.max(maxScore, currentScore);
            }

            return maxScore;
        }
    }

}
