package com.tosort;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */


public class BinarySearch {

    //Basic
    public int binarySearch(int[] nums, int target) {
        if(nums.length == 0)
            return -1;

        int low = 0;
        int high = nums.length - 1;

        while(low <= high) {
            int mid = low + (high - low)/2;
            if(target == nums[mid]) {
                return mid;
            }

            if(nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public int findFirstOcc(int[] nums, int target) {
        if(nums.length == 0)
            return -1;


        int low = 0;
        int high = nums.length - 1;
        int result = -1;

        while(low <= high) {
            int mid = low + (high - low)/2;
            if(target == nums[mid]) {
                result = mid;
                high = mid -1;
            } else if(nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    public int findLastOcc(int[] nums, int target) {
        if(nums.length == 0)
            return -1;

        int low = 0;
        int high = nums.length - 1;
        int result = -1;

        while(low <= high) {
            int mid = low + (high - low)/2;
            if(target == nums[mid]) {
                result = mid;
                low = mid + 1;
            } else if(nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // efficient binary search
    public int reducedComparisonBinarySearch(int[] nums, int target) {
        if(nums.length == 0)
            return -1;

        int low = 0;
        int high = nums.length - 1;

        while((high - low) > 1) {
            int mid = low + (high - low)/2;

            if(nums[mid] <= target) {
                low = mid;
            } else {
                high = mid;
            }
        }
        if(nums[low] != target)
            return -1;

        return low;
    }


    public int findFirstOccurence(int[] nums, int target) {
        if(nums.length == 0)
            return -1;

        int low = 0;
        int high = nums.length - 1;

        if(nums[low] == target)
            return low;

        while((high - low) > 1) {
            int mid = low + (high - low)/2;

            if(nums[mid] >= target) {
                high = mid;
            } else {
                low = mid;
            }
        }

        if(nums[high] != target)
            return -1;

        return high;
    }

    public int findLastOccurence(int[] nums, int target) {
        if(nums.length == 0)
            return -1;

        int low = 0;
        int high = nums.length - 1;

        if(nums[high] == target)
            return high;

        while((high - low) > 1) {
            int mid = low + (high - low)/2;

            if(nums[mid] <= target) {
                low = mid;
            } else {
                high = mid;
            }
        }

        if(nums[low] != target)
            return -1;

        return low;
    }



    public static void main(String[] args) {
        int[] array = {1,1,2,2,3,4,4,4,5,6,6,6,6,6,6,6};
        System.out.println(new BinarySearch().findFirstOccurence(array, 1));
        System.out.println(new BinarySearch().findFirstOccurence(array, 2));
        System.out.println(new BinarySearch().findFirstOccurence(array, 3));
        System.out.println(new BinarySearch().findFirstOccurence(array, 4));
        System.out.println(new BinarySearch().findFirstOccurence(array, 5));
        System.out.println(new BinarySearch().findFirstOccurence(array, 6));
        System.out.println(new BinarySearch().findFirstOccurence(array, 10));

        System.out.println("==============================================");

        System.out.println(new BinarySearch().findFirstOcc(array, 1));
        System.out.println(new BinarySearch().findFirstOcc(array, 2));
        System.out.println(new BinarySearch().findFirstOcc(array, 3));
        System.out.println(new BinarySearch().findFirstOcc(array, 4));
        System.out.println(new BinarySearch().findFirstOcc(array, 5));
        System.out.println(new BinarySearch().findFirstOcc(array, 6));
        System.out.println(new BinarySearch().findFirstOcc(array, 10));

        System.out.println("==============================================");

        System.out.println(new BinarySearch().findLastOccurence(array, 1));
        System.out.println(new BinarySearch().findLastOccurence(array, 2));
        System.out.println(new BinarySearch().findLastOccurence(array, 3));
        System.out.println(new BinarySearch().findLastOccurence(array, 4));
        System.out.println(new BinarySearch().findLastOccurence(array, 5));
        System.out.println(new BinarySearch().findLastOccurence(array, 6));
        System.out.println(new BinarySearch().findLastOccurence(array, 10));

        System.out.println("==============================================");

        System.out.println(new BinarySearch().findLastOcc(array, 1));
        System.out.println(new BinarySearch().findLastOcc(array, 2));
        System.out.println(new BinarySearch().findLastOcc(array, 3));
        System.out.println(new BinarySearch().findLastOcc(array, 4));
        System.out.println(new BinarySearch().findLastOcc(array, 5));
        System.out.println(new BinarySearch().findLastOcc(array, 6));
        System.out.println(new BinarySearch().findLastOcc(array, 10));

    }
}
