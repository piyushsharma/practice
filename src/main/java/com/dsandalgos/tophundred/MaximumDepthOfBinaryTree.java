package com.dsandalgos.tophundred;

import com.dsandalgos.util.TreeNode;

/**
 * Created by Piyush Sharma on 1/2/15.
 */

/* Problem Statement:
    Given a binary tree, find its maximum depth.
    The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
*/


public class MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public int maxDepthB(TreeNode root) {
        int ldepth;
        int rdepth;

        if(root == null)
            return 0;

        ldepth = 1 + maxDepth(root.left);
        rdepth = 1 + maxDepth(root.right);

        return ldepth > rdepth ? ldepth : rdepth;
    }

    public int maxDepthA(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int depth = 1;
        int ldepth = 0;
        int rdepth = 0;
        if(root.left != null) {
            ldepth += maxDepth(root.left);
        }
        if(root.right != null) {
            rdepth += maxDepth(root.right);
        }
        depth += ldepth > rdepth ? ldepth : rdepth;

        return depth;
    }


}
