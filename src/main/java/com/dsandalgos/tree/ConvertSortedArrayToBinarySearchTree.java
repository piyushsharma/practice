package com.dsandalgos.tree;

import com.dsandalgos.util.PrintUtil;
import com.dsandalgos.util.TreeNode;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
*/

public class ConvertSortedArrayToBinarySearchTree {

    public TreeNode sortedArrayToBST(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        return sortedArrayToBST(nums, start, end);
    }

    private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start > end)
            return null;

        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, start, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, end);

        return root;
    }

    public static void main(String[] args) {
        int inorder3[] = new int[]{1, 2, 3, 4, 5, 6, 7};
        TreeNode root = new ConvertSortedArrayToBinarySearchTree().sortedArrayToBST(inorder3);
        PrintUtil.printInOrder(root);
        System.out.println("+++++++++++++++++++++");
    }
}
