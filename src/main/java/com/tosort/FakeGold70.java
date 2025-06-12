package com.tosort;

/**
 * Created by Piyush Sharma on 10/3/14.
 */
public class FakeGold70 {

    static int numberOfWeighingDone = 0;

    private static void findFakeCoin(int[] coinArray, int startIndex, int endIndex) {
//        System.out.format("Find Fake Coin in array from %d to %d\n", startIndex, endIndex);
        int arrSize = endIndex - startIndex + 1;
        int subSize = arrSize/3;

        /* Special case of number of coins == 8,
           You can find out the fake coin in two times using the following logic =>
            Divide 8 coins into 3 subsets => 3, 3, 2
            => Weigh the two sets of 3 coins
              if(equal) {
               - means that fake coin is in the remaining 2
                => Compare the 2 coins and find out the fake coin
               }
               if(one 3 coin set is lighter) {
                    - means that fake coin is one of these 3 coins
                    => Choose any two coins from this set and compare
                    - if they are equal that means the remaining one is the
                      fake coin, if they are not equal then you anyways have the fake coin with you
               }
         */
        if(arrSize == 8) {
            /* In this case make the subSet size + 1, so that we divide the sets into 3, 3, 2 instead
               of 2, 2, 3
             */
            subSize += 1;
        }

        /* Return condition, last measurement becasue if there are only 3 or less coins
         * in the subset, we just need to do one more measure to know which is the fake coin */
        int diff = endIndex - startIndex;
        if(diff <= 2) {
            if(diff != 0) {
                /* Do not increase the counter becasue when diff = 0, that means there is no
                    need to do any more weighing, we are already at the index which has the
                    fake coin
                 */
                numberOfWeighingDone += 1;
            }
            return;
        }

        int sumFirstSubset = getSum(coinArray, startIndex, startIndex + subSize - 1);
        int sumSecondSubset = getSum(coinArray, startIndex + subSize, startIndex + subSize*2 - 1);
        int subsetStartIndex;
        int subsetEndIndex;

        /* Weight first subset and second subset and select the required subset */
        if(sumFirstSubset < sumSecondSubset) {
            subsetStartIndex = startIndex;
            subsetEndIndex = startIndex + subSize - 1;
        } else if(sumFirstSubset > sumSecondSubset) {
            subsetStartIndex = startIndex + subSize;
            subsetEndIndex = startIndex + subSize*2 - 1;
        } else {
            subsetStartIndex = startIndex + subSize*2;
            subsetEndIndex = startIndex + arrSize - 1;
        }
        numberOfWeighingDone += 1;
        findFakeCoin(coinArray, subsetStartIndex, subsetEndIndex);
    }

    private static int getSum(int[] coinArray, int startIndex, int endIndex) {
//        System.out.format("Sum from index %d to %d\n", startIndex, endIndex);
        int sum = 0;
        for(int i = startIndex; i <= endIndex; ++i) {
            sum += coinArray[i];
        }
        return sum;
    }

    public static void main(String args[]) {
        /* You could potentially read the number of coins as argument to the program
           and then initialize the array to that size
        */
        int[] coinArray = new int[70];
        /* Initialize with array */
        for(int i = 0; i < 70; ++i) {
            coinArray[i] = 0;
        }

        /* Change the fake coin position and calculate the step */
        for(int i = 0; i < 70; ++i) {
            if(i > 0) {
                coinArray[i-1] = 0;
            }
            coinArray[i] = -1;

            numberOfWeighingDone = 0;
            findFakeCoin(coinArray, 0, i);
            System.out.format("Number of weighing required to find the fake coin is at position %d ==> %d\n",
                                        i, numberOfWeighingDone);
        }
    }
}
