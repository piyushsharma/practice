package com.dsandalgos.tophundred;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given an array of size n, find the majority element.
The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.
*/

public class MajorityElement {

    public int majorityElement(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }

        Arrays.sort(nums);
        // since it appears >= n/2 + 1 times
        return nums[nums.length / 2];
    }


    /*
    Moore’s Voting Algorithm:

    This is a two step process.
    1. Get an element occurring most of the time in the array.
       This phase will make sure that if there is a majority element then it will return that only.
    2. Check if the element obtained from above step is majority element.

    In our case, it is assumed there exists a majority element, so no need of step 2.

    Finding a Candidate:
        Basic idea of the algorithm is if we cancel out each occurrence of an element e
        with all the other elements that are different from e then e will exist till end if it is a majority element.

    findCandidate(a[], size)
        1.  Initialize index and count of majority element
             maj_index = 0, count = 1
        2.  Loop for i = 1 to size – 1
            (a)If a[maj_index] == a[i]
                count++
            (b)Else
                count--;
            (c)If count == 0
                maj_index = i;
                count = 1
        3.  Return a[maj_index]

    Example:
        A[] = 2, 2, 3, 5, 2, 2, 6
        Initialize:
        maj_index = 0, count = 1 –> candidate ‘2?
        2, 2, 3, 5, 2, 2, 6

        Same as a[maj_index] => count = 2
        2, 2, 3, 5, 2, 2, 6

        Different from a[maj_index] => count = 1
        2, 2, 3, 5, 2, 2, 6

        Different from a[maj_index] => count = 0
        Since count = 0, change candidate for majority element to 5 => maj_index = 3, count = 1
        2, 2, 3, 5, 2, 2, 6

        Different from a[maj_index] => count = 0
        Since count = 0, change candidate for majority element to 2 => maj_index = 4
        2, 2, 3, 5, 2, 2, 6

        Same as a[maj_index] => count = 2
        2, 2, 3, 5, 2, 2, 6
     */

    public int majorityElementMoore(int[] nums) {
        int majElementVal = nums[0];
        int count = 1;

        for(int i = 1; i < nums.length; ++i) {
            if (nums[i] == majElementVal) {
                ++count;
            } else {
                --count;
            }

            if (count == 0) {
                majElementVal = nums[i];
                count = 1;
            }
        }

        return majElementVal;
    }

}
