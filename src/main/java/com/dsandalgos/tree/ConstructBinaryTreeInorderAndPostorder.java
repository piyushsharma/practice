package com.dsandalgos.tree;

import com.dsandalgos.util.PrintUtil;
import com.dsandalgos.util.TreeNode;

/*
Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
 */

public class ConstructBinaryTreeInorderAndPostorder {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int inStart = 0;
        int inEnd = inorder.length - 1;
        int postStart = 0;
        int postEnd = postorder.length - 1;

        return buildTreePostIn(inorder, inStart, inEnd, postorder, postStart, postEnd);
    }

    /* Algo:
    Last element in the postorder [] will be the root of the tree, here it is 1.

    1. Now the search element 1 in inorder[], say you find it at position i, once you find it,
    make note of elements which are left to i (this will construct the leftsubtree)
    and elements which are right to i (this will construct the rightSubtree).

    2. Suppose in previous step, there are X number of elements which are left of ‘i’
    (which will construct the left subtree), take first X elements from the postorder[] traversal,
    this will be the post order traversal for elements which are left to i.
    Similarly if there are Y number of elements which are right of ‘i’ (which will construct the rightsubtree),
    take next Y elements, after X elements from the postorder[] traversal,
    this will be the post order traversal for elements which are right to i

    From previous two steps construct the left and right subtree and link it to root.left and root.right respectively.
    */
    public TreeNode buildTreePostIn(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd)
            return null;

        TreeNode root = new TreeNode(postorder[postEnd]);

        int k = 0;
        for (int i = inStart; i <= inEnd; ++i) {
            if (inorder[i] == postorder[postEnd]) {
                k = i;
                break;
            }
        }
        int leftTreeLength = k - inStart;

        // postEnd = postStart + leftTreeLength - 1
        // because index starts from 0, eg. 0 + 2 (length) - 1 gives the right index to end
        root.left = buildTreePostIn(inorder, inStart, k - 1, postorder, postStart, postStart + leftTreeLength - 1);
        // postStart = postStart + leftTreeLength
        // because index starts from 0, eg. 0 + 2 (length), you want to start from index 2 not 0 + 2 -1
        // since 0 and 1 are being used to build the left tree
        // Or you can just think of it as previous preEnd (as in the preEnd used to construct the left subtree) + 1
        // so previous preEnd + 1 => postStart + leftTreeLength - 1 + 1 => postStart + leftTreeLength
        root.right = buildTreePostIn(inorder, k + 1, inEnd, postorder, postStart + leftTreeLength, postEnd - 1);

        return root;
    }

    public static void main(String[] args) {
        int inorder[] = new int[]{4, 2, 5, 1, 6, 7, 3, 8};
        int postorder[] = new int[]{4, 5, 2, 6, 7, 8, 3, 1};
        TreeNode root = new ConstructBinaryTreeInorderAndPostorder().buildTree(inorder, postorder);
        PrintUtil.printPostOrder(root);
        System.out.println("+++++++++++++++++++++");
        PrintUtil.printInOrder(root);
    }
}