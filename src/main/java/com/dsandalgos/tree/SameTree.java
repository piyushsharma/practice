package com.dsandalgos.tree;

/*
Given two binary trees, write a function to check if they are equal or not.
Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
*/

import com.dsandalgos.util.TreeNode;

public class SameTree { 

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null)
            return true;

        // because at least one of them is not null
        if(p == null || q == null || p.val != q.val)
            return false;

        return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }
}