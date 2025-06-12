package com.tosort;

/**
 * Created by Piyush Sharma on 7/29/15.
 */
public class TrieNode {

    public int value;
    public TrieNode[] children;

    public TrieNode(int value, TrieNode[] children) {
    /* used to mark leaf nodes */
        this.value = value;
        this.children = children;
    }

}
