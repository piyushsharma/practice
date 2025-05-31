package com.dsandalgos.tophundred;

/**
 * Created by Piyush Sharma on 7/6/15.
 */


public class Trie {

    class TrieNode {

        public int value;
        public TrieNode[] children;

        public TrieNode(int value, TrieNode[] children) {
            /* used to mark leaf nodes */
            this.value = value;
            this.children = children;
        }
    }

    public static final int MAX_ALPHABET_SIZE = 26;
    private TrieNode root;
    private int count;

    public Trie() {
        root = getNewNode();
        count = 0;
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        int len = word.length();
        TrieNode temp = root;
        ++count;

        for(int i = 0; i < len; ++i) {
            int index = getCharToInt(word.charAt(i));
            TrieNode t = temp.children[index];
            if(t == null) {
                t = getNewNode();
                temp.children[index] = t;
            }
            temp = t;
        }
        temp.value = count;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode temp = root;
        int len = word.length();

        for(int i = 0; i < len; ++i) {
            int index = getCharToInt(word.charAt(i));
            TrieNode t = temp.children[index];
            if(t == null) {
                return false;
            }
            temp = t;
        }
        if(temp != null && temp.value > 0)
            return true;

        return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode temp = root;
        int len = prefix.length();

        for(int i = 0; i < len; ++i) {
            int index = getCharToInt(prefix.charAt(i));
            TrieNode t = temp.children[index];
            if(t == null) {
                return false;
            }
            temp = t;
        }
        if(temp != null)
            return true;

        return false;
    }

    public TrieNode getNewNode() {
        TrieNode[] children = new TrieNode[MAX_ALPHABET_SIZE];
        for(int i = 0; i < MAX_ALPHABET_SIZE; ++i) {
            children[i] = null;
        }
        return new TrieNode(0, children);
    }

    public int getCharToInt(Character c) {
        c = c.toLowerCase(c);
        int x = (int)c - 97;
        try {
            if(x > 25) throw new Exception("Incorrect character");
        } catch(Exception e) {
            System.out.print("char to int = ");
            System.out.println(x);
        }
        return x;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("somestring");
        trie.insert("somestring");
        trie.insert("somestring");
        trie.search("key");
    }
}
