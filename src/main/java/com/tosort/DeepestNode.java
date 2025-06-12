package com.tosort;

import java.util.ArrayList;
import java.util.List;
import com.dsandalgos.util.Pair;
import com.dsandalgos.util.TreeNode;

public class DeepestNode {

    private TreeNode deepestNode = null;
    private int deepestlevel = 0;

    private TreeNode deepestNode(TreeNode root) {
        if(root == null) return null;

        findDeepestNode(root, 0);
        return deepestNode;
    }

    private void findDeepestNode(TreeNode root, int level) {
        if(root == null)
            return;

        findDeepestNode(root.left, ++level);
        if(level > deepestlevel) {
            deepestlevel = level;
            deepestNode = root;
        }
        findDeepestNode(root.right, ++level);
    }


    private static TreeNode findDeepest(TreeNode root)
	{
	    if(root == null) return null;
	    Pair<TreeNode, Integer> pair = new Pair<TreeNode, Integer>(null, 0);
	    findDeepest(0,root,pair);
	    return pair.k();
	}

	private static void findDeepest(int curDepth, TreeNode root, Pair<TreeNode,Integer> pair)
	{
	    if(root == null)
	        return;
	    
	    findDeepest(curDepth+1, root.left, pair);
	    if(curDepth >= pair.v())
	    {
	        pair.k(root);
	        pair.v(curDepth);
	    }
	    findDeepest(curDepth+1, root.right, pair);
	}

}
