package com.tosort;



/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
We break the problem in 3 parts:
1. Print the left boundary in top-down manner.
2. Print all leaf nodes from left to right, which can again be sub-divided into two sub-parts:
    2.1 Print all leaf nodes of left sub-tree from left to right.
    2.2 Print all leaf nodes of right subtree from left to right.
3. Print the right boundary in bottom-up manner.

We need to take care of one thing that nodes are not printed again. e.g.
the left most node is also the leaf node of the tree.
*/

public class BoundaryTraversalBinaryTree {

    class TreeNode {
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

    public void printBoundary(TreeNode root) {
        if(root == null)
            return;

        System.out.println(root.val);

        printBoundaryLeft(root.left);
        printLeaves(root.left);
        printLeaves(root.right);
        printBoundaryRight(root.right);
    }

    private void printBoundaryRight(TreeNode root) {
        if(root == null)
            return;

        if(root.right != null) {
            // to ensure bottom up order, first call for right
            //  subtree, then print this node
            printBoundaryRight(root.right);
            System.out.println(root.val);
        } else if(root.left != null) {
            printBoundaryRight(root.left);
            System.out.println(root.val);
        }
        /* If both left and right are null, printLeaves will print that */

    }

    private void printLeaves(TreeNode root) {
        if(root == null)
            return;

        printLeaves(root.left);
        if(root.left == null && root.right == null) {
            System.out.println(root.val);
        }
        printLeaves(root.right);
    }

    private void printBoundaryLeft(TreeNode root) {
        if(root == null)
            return;

        if(root.left != null) {
            System.out.println(root.val);
            printBoundaryLeft(root.left);
        } else if(root.right != null) {
            System.out.println(root.val);
            printBoundaryLeft(root.right);
        }
        /* If both left and right are null, printLeaves will print that */
    }

    private TreeNode createTrees() {
        TreeNode n1 = new TreeNode(10);
        TreeNode n2 = new TreeNode(20);
        TreeNode n3 = new TreeNode(30);
        TreeNode n4 = new TreeNode(40);
        TreeNode n5 = new TreeNode(50);
        TreeNode n6 = new TreeNode(60);
        TreeNode n7 = new TreeNode(70);
        n2.left = n1;
        n2.right = n3;
        n6.left = n5;
        n6.right = n7;
        n4.left = n2;
        n4.right = n6;
        return n4;
    }

    public static void main(String[] args) {
        BoundaryTraversalBinaryTree b = new BoundaryTraversalBinaryTree();
        b.printBoundary(b.createTrees());
    }
}
