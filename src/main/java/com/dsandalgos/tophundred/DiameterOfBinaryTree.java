package com.dsandalgos.tophundred;

public class DiameterOfBinaryTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    int diameter = 1;
    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return diameter - 1;
    }

    private int depth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int left = depth(root.left);
        int right = depth(root.right);

        diameter = Math.max(diameter, left + right + 1);

        return 1 + Math.max(left, right);
    }


}
