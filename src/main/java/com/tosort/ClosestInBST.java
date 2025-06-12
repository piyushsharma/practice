package com.tosort;



public class ClosestInBST
{

	private static TreeNode getClosestNode(int key, TreeNode root)
	{
	    int minDiff = Integer.MAX_VALUE;
	    TreeNode closestNode = null;
	    
	    while(root != null)
	    {
	        int diff = Math.abs(key - root.value);
	        if(root.value == key)
	            return root;
	        else if(root.value > key)
	        {
	            if(diff < minDiff)
	            {
	                minDiff = diff;
	                closestNode = root;
	            }
	            root = root.left;
	        }
	        else if(root.value < key)
	        {
	            if(diff < minDiff)
	            {
	                minDiff = diff;
	                closestNode = root;
	            }
	            root = root.right;
	        }
	    }
	    return closestNode;
	}
}
