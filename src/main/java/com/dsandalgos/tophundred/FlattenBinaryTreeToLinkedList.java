package com.dsandalgos.tophundred;

import com.dsandalgos.util.DataStructureUtility;
import com.dsandalgos.util.PrintUtil;
import com.dsandalgos.util.TreeNode;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6

If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.
*/


public class FlattenBinaryTreeToLinkedList {

    public void flatten(TreeNode root) {

        if(root == null)
            return;

        flattenTree(root);
    }

    public TreeNode flattenTree(TreeNode root) {

        if(root == null)
            return root;

        // recursion gives us the flattened left and right list
        TreeNode leftList = flattenTree(root.left);
        TreeNode rightList = flattenTree(root.right);

        // hook the left list to root
        root.left = null;
        root.right = leftList;

        // handle case when left tree is null
        if(leftList == null) {
            root.right = rightList;
            return root;
        }

        // go to the end of left list and hook right list
        TreeNode cur = leftList;
        while(cur.right != null) {
            cur = cur.right;
        }
        cur.right = rightList;
        return root;
    }


    public static void main(String[] args) {
        TreeNode root = new DataStructureUtility().sampleBinarySearchTree();
        new FlattenBinaryTreeToLinkedList().flatten(root);
        System.out.println("+++++++++++++++++++++");
        while (root != null) {
            System.out.println(root.val);
            root = root.right;
        }
    }
}
