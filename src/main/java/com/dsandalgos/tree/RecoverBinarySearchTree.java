package com.dsandalgos.tree;

/*
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
*/

/* There is an efficient way of doing this, with lesser space complexity =>
Morris Inorder Traversal =>
http://stackoverflow.com/questions/5502916/explain-morris-inorder-tree-traversal-without-using-stacks-or-recursion
*/

import com.dsandalgos.util.TreeNode;

import java.util.Stack;

public class RecoverBinarySearchTree {

    public void recoverTree(TreeNode root) {
        if(root == null)
            return;

        Stack<TreeNode> s = new Stack<TreeNode>();

        TreeNode prev = null;
        TreeNode node1 = null;
        TreeNode node2 = null;
        while (true) {

            while(root != null) {
                s.add(root);
                root = root.left;
            }

            if(s.isEmpty()) break;

            root = s.pop();
            if(prev != null && root.val < prev.val) {
                if(node1 == null) {
                    // make node 1 prev because this is the bigger node between the two
                    // which should be moved down the tree
                    node1 = prev;
                }
                // this also handles the case when there are only two nodes
                // the second node involved in the swap, the smaller one, which should be
                // moved up the tree
                node2 = root;
            }
            prev = root;
            root = root.right;
        }

        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);

        new RecoverBinarySearchTree().recoverTree(root);
        System.out.println("Done!");
    }
}