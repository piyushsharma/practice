package com.dsandalgos.tree;

import com.dsandalgos.util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
*/

public class MinimumDepthOfBinaryTree {

    // breadth first (level order) traversal
    // good in cases of highly unbalanced trees (as we break as soon as we see the first leaf node)
    // bad in cases of full binary trees
    public int minDepth(TreeNode root) {
        if(root == null)
            return 0;

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);

        // Maintain the depth of nodes in a queue as well
        int depth = 1;
        TreeNode rightMost = root;

        // do a level order traversal
        // when you reach a node with both left and right == null, return the count
        while(!q.isEmpty()) {
            TreeNode cur = q.remove();

            if(cur.left == null && cur.right == null) {
                break;
            }

            if(cur.left != null ) q.add(cur.left);
            if(cur.right != null) q.add(cur.right);

            if(cur == rightMost) {
                ++depth;
                rightMost = cur.right != null ? cur.right : cur.left;
            }
        }
        return depth;
    }


    public int minDepthRecursion(TreeNode root) {
        if(root == null)
            return 0;

        /* these base cases have to added to handle =>
                       6___                  ____6
                           \       or        |
                           8                 8

        */
        if(root.left == null) {
            return 1 + minDepthRecursion(root.right);
        }

        if(root.right == null) {
            return 1 + minDepthRecursion(root.left);
        }

        return 1 + Math.min(minDepthRecursion(root.left), minDepthRecursion(root.right));
    }


    /* This is done using pre-order traversal,
        where we recurse through the whole tree and then determine what the minimum depth would be.
     */
    public int minDepthA(TreeNode root) {
        if (root == null)
            return 0;

        int left = minDepth(root.left);
        int right = minDepth(root.right);

        // leaf
        if (left == 0 && right == 0) {
            return 1;
        } else if (left == 0) {

            /* // to handle =>
                       6___
                           \
                           8

             */

            return 1 + right;
        } else if (right == 0) {

            /* // to handle =>
                ____6
               /
              2
             */

            return 1 + left;
        } else {
            return 1 + Math.min(left, right);
        }
    }
}
