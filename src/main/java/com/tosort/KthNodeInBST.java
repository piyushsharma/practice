package com.tosort;

import com.dsandalgos.util.TreeNode;

public class KthNodeInBST
{

	private static TreeNode kthNode(int k, TreeNode root)
	{
	    if(root == null)
	        return null;
	    
	    while(root != null)
	    {
	        int leftCount = (root.left == null) ? 0 : root.left.leftCount;
	        if(leftCount+1 == k)
	            return root;
	        else if(leftCount+1 > k)
	            root = root.left;
	        else if(leftCount+1 < k)
	        {
	            root = root.right;
	            k -= 1+leftCount;
	        }
	    }
	    return null;
	}
}
