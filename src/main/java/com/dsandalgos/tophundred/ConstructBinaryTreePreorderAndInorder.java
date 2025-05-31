package com.dsandalgos.tophundred;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
 */

public class ConstructBinaryTreePreorderAndInorder {

    class TreeNode {
        public int val;

        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int inStart = 0;
        int inEnd = inorder.length - 1;
        int preStart = 0;
        int preEnd = preorder.length - 1;

        return buildTreePreIn(inorder, inStart, inEnd, preorder, preStart, preEnd);
    }

    public TreeNode buildTreePreIn(int[] inorder, int inStart, int inEnd, int[] preorder, int preStart, int preEnd) {
        if(inStart > inEnd || preStart > preEnd)
            return null;

        TreeNode root = new TreeNode(preorder[preStart]);
        int k = 0;
        for(int i = inStart; i <= inEnd; ++i) {
            if(inorder[i] == preorder[preStart]) {
                k = i;
                break;
            }
        }

        int leftTreeLength = k - inStart;

        // preEnd =  (preStart + 1) + (leftTreeLength - 1)
        // i.e. starting index + number of nodes in left subtree - 1
        root.left = buildTreePreIn(inorder, inStart, k - 1, preorder, preStart + 1, preStart + leftTreeLength);
        // preStart = (preStart + 1) + (leftTreeLength - 1) + 1
        // also think of it as ending index to construct left subtree + 1
        // so preStart = preStart + leftTreeLength + 1
        root.right = buildTreePreIn(inorder, k + 1, inEnd, preorder, preStart + 1 + leftTreeLength, preEnd);
        return root;
    }


    int curIndex = 0;
    Map<Integer, Integer> map;

    public TreeNode bstFromPreorder(int[] preorder) {

        int [] inorder = Arrays.copyOf(preorder, preorder.length);
        Arrays.sort(inorder);

        map = new HashMap<>();
        for(int i = 0; i < preorder.length; ++i) {
            map.put(inorder[i], i);
        }

        return buildTree(preorder, 0, preorder.length - 1);
    }


    private TreeNode buildTree(int[] n, int low, int high) {
        if(low > high) {
            return null;
        }

        int value = n[curIndex];
        TreeNode root = new TreeNode(value);

        int index = map.get(value);
        curIndex += 1;

        root.left = buildTree(n, low, index - 1);
        root.right = buildTree(n, index + 1, high);

        return root;
    }



    int preIndex = 0;

    public TreeNode bstFromPreorderON(int[] preorder) {
        return buildTreeFromPre(preorder, Integer.MAX_VALUE);
    }

    private TreeNode buildTreeFromPre(int[] preorder, int maxValue) {
        if(preIndex >= preorder.length || preorder[preIndex] > maxValue) return null;

        int val = preorder[preIndex];
        ++preIndex;
        TreeNode root = new TreeNode(val);
        root.left = buildTreeFromPre(preorder, val);
        root.right = buildTreeFromPre(preorder, maxValue);

        return root;
    }

    public static void main(String[] args) {
        int preorder[] = new int[]{8,5,1,7,10,12};
        TreeNode root = new ConstructBinaryTreePreorderAndInorder().bstFromPreorderON(preorder);


//        int inorder2[] = new int[]{4, 2, 5, 1, 6, 7, 3, 8};
//        int preorder[] = new int[]{1 ,2, 4, 5, 3, 7, 6, 8};
//        TreeNode root = new ConstructBinaryTreePreorderAndInorder().buildTree(preorder, inorder2);
        System.out.println("+++++++++++++++++++++");
    }
}
