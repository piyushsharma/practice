package com.dsandalgos.tree;

import com.dsandalgos.util.DataStructureUtility;
import com.dsandalgos.util.PrintUtil;
import com.dsandalgos.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]
*/

public class BinaryTreePaths {


    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if(root == null)
            return result;

        binaryTreePaths(root, result, "");
        return result;
    }

    private void binaryTreePaths(TreeNode root, List<String> result, String path) {
        if (root == null) {
            return;
        }

        String curNodeStr = Integer.toString(root.val);
        if(path.length() != 0) {
            curNodeStr = "->" + curNodeStr;
        }
        String nPath = path + curNodeStr;

        if (root.left == null && root.right == null) {
            result.add(nPath);
        } else {
            binaryTreePaths(root.left, result, nPath);
            binaryTreePaths(root.right, result, nPath);
        }
    }

    private void binaryTreePaths(TreeNode root, List<String> path, List<List<String>> result) {
        if (root == null) {
            return;
        }

        path.add(Integer.toString(root.val));

        if(root.left == null && root.right == null) {
            List<String> clonePath = new ArrayList<>();
            clonePath.addAll(path);
            result.add(clonePath);

        } else {

            binaryTreePaths(root.left, path, result);
            binaryTreePaths(root.right, path, result);
        }
        path.remove(path.size() - 1);
    }

    private static void printPathToLeaves(TreeNode node, List<TreeNode> path) {
        if(node == null)
            return;
        path.add(node);

        if(node.left == null && node.right == null) {
            for(TreeNode n : path) {
                System.out.print(n.val + " ");
            }
            System.out.println("");
            path.remove(path.size()-1);
            return;
        }

        printPathToLeaves(node.left, path);
        printPathToLeaves(node.right, path);
        path.remove(path.size()-1);
    }

    public static void main(String[] args) {
        DataStructureUtility p = new DataStructureUtility();
        TreeNode r = p.sampleBinaryTree(6);

        List<String> result = new BinaryTreePaths().binaryTreePaths(r);

        PrintUtil.printStringList(result);

        List<List<String>> paths = new ArrayList<>();
        new BinaryTreePaths().binaryTreePaths(r, new ArrayList<>(), paths);

        System.out.println("Done");

    }


}

