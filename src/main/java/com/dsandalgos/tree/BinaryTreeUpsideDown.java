package com.dsandalgos.tree;

import com.dsandalgos.util.TreeNode;

/**
 * Created by Piyush Sharma
 */

/*
Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left
 node that shares the same parent node) or empty, flip it upside down and turn it into a tree
 where the original right nodes turned into left leaf nodes. Return the new root.

 Given a binary tree {1,2,3,4,5},

     1
   / \
  2   3
 / \
4   5

return the root of the binary tree [4,5,2,#,#,3,1].

    4
  / \
 5   2
    / \
   3   1

*/

public class BinaryTreeUpsideDown {

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        return dfsBottomUp(root, null);
    }


    // At each node we want to assign (algo):
    // root.left = parent.right
    // root.right = parent

    // Reassign the bottom-level nodes before the upper ones, we won't have to make copies
    // and worry about overwriting something.
    // We know the new root will be the left-most leaf node, so we begin the reassignment here.
    // Keep calling recursively until we reach the left most leaf node, return parent there
    public TreeNode dfsBottomUp(TreeNode root, TreeNode parent) {

        // note we return parent
        if(root == null) return parent;

        TreeNode newRoot = dfsBottomUp(root.left, root);
        root.left = parent == null ? null : parent.right;
        root.right = parent;

        return newRoot;
    }



}
