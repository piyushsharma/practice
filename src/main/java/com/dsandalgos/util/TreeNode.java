package com.dsandalgos.util;

/**
 * Created by Piyush Sharma on 1/2/15.
 */
public class TreeNode {
    public int val;

    // this is to store the number of nodes in the left subtree
    // binary search tree can be augmented like this to find kth smallest element faster
    public int leftCount;

    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
        leftCount = 0;
    }
}
