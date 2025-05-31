package com.dsandalgos.tophundred;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/* Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target,
where index1 must be less than index2.
Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
*/

public class TwoSum {

    // O(n) runtime, O(n) space
    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] result = new int[2];

        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                int index = map.get(target - numbers[i]);
                result[0] = index;
                result[1] = i;
                break;
            }
            map.put(numbers[i], i);
        }

        return result;
    }


    // better than calling binary search n times, move two pointers
    // O(n) time, O(1) space complexity
    public int[] twoSumSortedArray(int[] numbers, int target) {
        int high = numbers.length - 1;
        int low = 0;

        while(low <= high) {
            if((numbers[low] + numbers[high]) == target) {
                return new int[]{low, high};
            }

            if((numbers[low] + numbers[high]) < target) {
                ++low;
            } else {
                --high;
            }
        }
        return null;
    }

    // two sum when the given array is sorted
    // we could again use the hash table approach, but it takes O(n) extra space
    // since the array is already sorted -> we can do binary search instead
    // this is O(nlogn) => call binary search for each number
    public static int[] twoSumSortedArrayV2(int[] numbers, int target) {
        int len = numbers.length - 1;

        for(int i = 0; i < len; ++i) {
            int otherIndex = binarySearchTwoSum(numbers, target - numbers[i], i+1);
            if(otherIndex != -1) {
                return new int[]{i, otherIndex};
            }
        }
        return null;
    }

    public static int binarySearchTwoSum(int[] numbers, int key, int start) {
        int low = start;
        int high = numbers.length - 1;

        while(low <= high) {
            int mid = low + (high - low)/2;

            if(numbers[mid] == key) {
                return mid;
            }

            if(numbers[mid] < key)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }

    /*
    Design and implement a TwoSum class. It should support the following operations: add and find.
            add(input) – Add the number input to an internal data structure.
            find(value) – Find if there exists any pair of numbers which sum is equal to the value.
            For example,
            add(1); add(3); add(5); find(4) true; find(7) false
    */

    private Map<Integer, Integer> m = new HashMap<>();

    private void twoSumAdd(int key) {
        m.put(key, m.getOrDefault(key, 0) + 1);
    }

    private boolean twoSumFind(int key) {
        for(Map.Entry<Integer, Integer> entry : m.entrySet()) {
            int numberInMap = entry.getKey();
            int count = entry.getValue();

            int secondNumber = key - numberInMap;
            if(m.containsKey(secondNumber)) {
                // the second number is present, need to make sure it is not a duplicate
                if(secondNumber == numberInMap && count >= 2) {
                    return true;
                }
                // if they are different, we have found the solution
                if(secondNumber != numberInMap) {
                    return true;
                }
            }
        }
        return false;
    }



    // in this case hashmap is better because we need to copy before sorting anyways to get the actual indexes
    // we are not really saving on space
    public static int[] twoSumArray(int[] nums, int target) {
        int len = nums.length;
        if(len <= 1) {
            return null;
        }
        int[] result = new int[2];
        int[] copyArr = new int[nums.length];
        // save copy to get indexes later
        for (int i = 0; i < nums.length; ++i) {
            copyArr[i] = nums[i];
        }

        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;

        while(left < right) {
            if (nums[left] + nums[right] == target) {
                result[0] = nums[left];
                result[1] = nums[right];
                break; // since only one solution
            } else if (nums[left] + nums[right] < target){
                ++left;
            } else {
                --right;
            }
        }

        // since given that input will have exactly one solution
        for(int i = 0; i < copyArr.length; ++i) {
            if (copyArr[i] == result[0]) {
                result[0] = i;
            }
            if (copyArr[i] == result[1]) {
                result[1] = i;
            }
        }

        return result;
    }

    public static void main(String args[])  {
        TwoSum t = new TwoSum();
        int[] retVal = new int[]{2,2,3,4};
//        int[] x = t.twoSum(retVal, 6);
        int[] x = t.twoSumSortedArray(retVal, 4);
        System.out.println(x[0]);
        System.out.println(x[1]);
    }

}
