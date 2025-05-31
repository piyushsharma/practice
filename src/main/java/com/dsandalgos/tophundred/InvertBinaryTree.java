package com.dsandalgos.tophundred;

import com.dsandalgos.util.DataStructureUtility;
import com.dsandalgos.util.PrintUtil;
import com.dsandalgos.util.TreeNode;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9

to

     4
   /   \
  7     2
 / \   / \
9   6 3   1

*/

public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if(root == null)
            return root;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new DataStructureUtility().sampleBinarySearchTree();
        PrintUtil.printInOrder(root);
        new InvertBinaryTree().invertTree(root);
        System.out.println("+++++++++++++++++++++");
        PrintUtil.printInOrder(root);
    }
}