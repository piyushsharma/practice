package com.dsandalgos.segmenttree;

/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 *
 * The update(i, val) function modifies nums by updating the element at index i to val.
 *
 * Example:
 *
 * Given nums = [1, 3, 5]
 *
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * Note:
 *
 * The array is only modifiable by the update function.
 * You may assume the number of calls to update and sumRange function is distributed evenly.
 *
 */

public class RangeSumQuery {

    int[] segtree;
    int high;

    public RangeSumQuery(int[] nums) {
        this.high = nums.length - 1;
        segtree = new int[nums.length * 4];
        buildSegTree(nums, 0, 0, high);
    }

    private void buildSegTree(int[] nums, int treeIndex, int low, int high) {
        if(low == high) {
            segtree[treeIndex] = nums[low];
            return;
        }

        int mid = low + (high - low)/2;

        int firstChildIndex = 2 * treeIndex + 1;
        int secondChildIndex = 2 * treeIndex + 2;
        buildSegTree(nums, firstChildIndex, low, mid);
        buildSegTree(nums, secondChildIndex, mid + 1, high);

        segtree[treeIndex] = merge(segtree[firstChildIndex], segtree[secondChildIndex]);
    }

    // this varies depending on the problem you are trying to solve
    // for this problem we want the sum of a range, so we will simply add the two children
    private int merge(int item1, int item2) {
        return item1 + item2;
    }

    public void update(int i, int val) {
        updateSegTree(0, 0, high, i, val);
    }

    private void updateSegTree(int treeIndex, int low, int high, int arrIndex, int val) {
        if(low == high) {
            segtree[treeIndex] = val;
            return;
        }

        int mid = low + (high - low)/2;
        if(arrIndex <= mid) {
            updateSegTree(2*treeIndex + 1, low, mid, arrIndex, val);
        } else {
            updateSegTree(2*treeIndex + 2, mid+1, high, arrIndex, val);
        }

        // also update other parents to reflect the udpate
        segtree[treeIndex] = merge(segtree[2*treeIndex + 1], segtree[2*treeIndex + 2]);
    }

    public int sumRange(int i, int j) {
        // query the segtree for sum of items between i and j
        return querySegTree(0,0, high, i, j);
    }

    private int querySegTree(int treeIndex, int low, int high, int i, int j) {

        if(low > j || high < i) {
            return 0;
        }

        if(i <= low && j >= high) {
            return segtree[treeIndex];
        }

        int mid = low + (high - low)/2;

        if(i > mid) {
            // entire range is on right side, query right tree
            return querySegTree(2*treeIndex + 2, mid + 1, high, i, j);
        } else if (j <= mid) {
            // entire range is on left side, query left tree
            return querySegTree(2*treeIndex + 1, low, mid, i, j);
        }

        int leftQuery = querySegTree(2*treeIndex + 1, low, mid, i, j);
        int rightQuery = querySegTree(2*treeIndex + 2, mid + 1, high, i, j);

        return merge(leftQuery, rightQuery);
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5};
        RangeSumQuery obj = new RangeSumQuery(nums);
        int param_1 = obj.sumRange(0,2); // 9
        obj.update(1, 2);
        int param_2 = obj.sumRange(0,2); // 8

        System.out.println(param_1);
        System.out.println(param_2);
    }
}
