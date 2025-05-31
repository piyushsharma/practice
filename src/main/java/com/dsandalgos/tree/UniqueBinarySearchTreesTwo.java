package com.dsandalgos.tree;

/*
Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

*/


import com.dsandalgos.util.PrintUtil;
import com.dsandalgos.util.TreeNode;

import java.util.ArrayList;
import java.util.List;



/*

The idea is to maintain a list of roots of all BSTs.
Recursively construct all possible left and right subtrees.
Create a tree for every pair of left and right subtree and add the tree to list. Below is detailed algorithm.

1) Initialize list of BSTs as empty.
2) For every number i where i varies from 1 to N, do following
......a)  Create a new node with key as 'i', let this node be 'node'
......b)  Recursively construct list of all left subtrees.
......c)  Recursively construct list of all right subtrees.
3) Iterate for all left subtrees
   a) For current leftsubtree, iterate for all right subtrees
      Add current left and right subtrees to 'node' and add 'node' to list.
*/

// Refer http://www.geeksforgeeks.org/construct-all-possible-bsts-for-keys-1-to-n/

public class UniqueBinarySearchTreesTwo {

    public List<TreeNode> generateTrees(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {

        List<TreeNode> result = new ArrayList<>();

        /*  if start > end then subtree will be empty so returning null in the list */
        // this is important so that we can fill left and right as null
        if(start > end) {
            result.add(null);
            return result;
        }

        for(int i = start; i <= end; ++i) {


            List<TreeNode> leftSubtreeList = generateTrees(start, i - 1);
            List<TreeNode> rightSubtreeList = generateTrees(i + 1, end);

            for(int l = 0; l < leftSubtreeList.size(); ++l) {

                for(int r = 0; r < rightSubtreeList.size(); ++r) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftSubtreeList.get(l);
                    root.right = rightSubtreeList.get(r);
                    result.add(root);
                }
            }
        }

        return result;
    }


    public static void main(String[] args) {
        UniqueBinarySearchTreesTwo u = new UniqueBinarySearchTreesTwo();

        List<TreeNode> result = u.generateTrees(3);

        for(TreeNode t : result) {
            System.out.println("===================================");
            PrintUtil.printPreOrder(t);
            System.out.println("===================================");
        }
    }

}