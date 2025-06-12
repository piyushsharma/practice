package com.tosort;

class Pair<TreeNode, Integer> {
    TreeNode k;
    Integer v;
    Pair(TreeNode k, Integer v) {
        this.k = k;
        this.v = v;
    }

    public TreeNode k() {
        return k;
    }

    public Integer v() {
        return v;
    }

    public void k(TreeNode k) {
        this.k = k;
    }

    public void v(Integer v) {
        this.v = v;
    }
	
}

class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;
    TreeNode(int value) {
        this.value = value;
    }

    public TreeNode left() {
        return left;
    }

    public TreeNode right() {
        return right;
    }

    public int value() {
        return value;
    }

    public void left(TreeNode left) {
        this.left = left;
    }

    public void right(TreeNode right) {
        this.right = right;
    }
}

public class MostFrequentInBinaryTree 
{

	private static TreeNode getMostFrequent(TreeNode root)
	{
	    if(root == null) return null;
	    Pair<TreeNode, Integer> pair = new Pair<TreeNode, Integer>(null, 0);
	    inOrderWalk(0,null,root,pair);
	    return pair.k();
	}

	private static void inOrderWalk(int freq, TreeNode prev, TreeNode root, Pair<TreeNode,Integer> pair)
	{
	    if(root.left() != null)
	    	inOrderWalk(freq, prev, root.left(), pair);

	    if(prev == null)
	    {
	        freq++;
	        pair.k(root);
	        pair.v(freq);
	    }
	    
	    else if(prev.value() != root.value())
	    {
	        if(pair.v() < freq)
	        {
	            pair.v(freq);
	            pair.k(root);
	        }
	        freq = 1;
	    }
	    if(root.right() != null)
	    	inOrderWalk(freq, root, root.right(), pair);
	}
	
}
