package com.dsandalgos.tree;

import com.dsandalgos.util.DataStructureUtility;
import com.dsandalgos.util.PrintUtil;
import com.dsandalgos.util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */


/*
Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled,
and all nodes in the last level are as far left as possible.
It can have between 1 and 2h nodes inclusive at the last level h.
*/

/*
1) get the height of left-most part
2) get the height of right-most part
3) when they are equal, the # of nodes = 2^h -1
4) when they are not equal, recursively get # of nodes from left&right sub-trees
 */

public class CountCompleteTreeNodes {

    // Time complexity is O(h^2).
    public int countNodes(TreeNode root) {
        if(root == null)
            return 0;

        int x = getLeftTreeHeight(root.left);
        int y = getRightTreeHeight(root.right);

        if(x == y) {
            // note that x and y are height values and we are doing 2^h
            // 10 << x  => simple way to move 2 raise to the power x
            return (2 << x) - 1; // since if they are equal, number of nodes = 2^h - 1;
        } else {
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }

    private int getRightTreeHeight(TreeNode root) {
        int height = 0;
        while(root != null) {
            height += 1;
            root = root.right;
        }
        return height;
    }

    private int getLeftTreeHeight(TreeNode root) {
        int height = 0;
        while(root != null) {
            height += 1;
            root = root.left;
        }
        return height;
    }

    public int countNodesLevelOrder(TreeNode root) {
        if(root == null)
            return 0;

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        int count = 1;

        while(!q.isEmpty()) {
            TreeNode r = q.remove();

            if(r.left != null) {
                q.add(r.left);
                ++count;
            }
            if(r.right != null) {
                q.add(r.right);
                ++count;
            }
        }

        return count;
    }

//     inefficient method
    public int countNodesRecur(TreeNode root) {
        if(root == null)
            return 0;
        return 1 + countNodesRecur(root.left) + countNodesRecur(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new DataStructureUtility().sampleBinaryTree(1);
        PrintUtil.printInOrder(root);
        System.out.println("++++++++++++++++");
        int x = new CountCompleteTreeNodes().countNodes(root);
        System.out.println(x);
    }
}