package com.tosort;

/* Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression
             string containing only letters a-z or .. A . means it can represent any one letter.

For example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.
*/


public class WordDictionary {
    public static final int MAX_ALPHABET_SIZE = 26;
    private TrieNode root;
    private int count;

    public WordDictionary() {
        root = getNewNode();
        count = 0;
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
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

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        TrieNode temp = root;
        return searchAllChildren(temp, word);
    }

    private boolean searchAllChildren(TrieNode temp, String word) {
        int len = word.length();

        boolean findValidWildChar = false;
        for(int i = 0; i < len; ++i) {
            if(word.charAt(i) == '.') {
                /* If the character is not a wildcard, search for all children
                 * If one of the children has the remaining characters, we can return true */
                for(int j = 0; j < MAX_ALPHABET_SIZE; ++j) {
                    TrieNode t = temp.children[j];
                    if(t != null) {
                        findValidWildChar = searchAllChildren(t, word.substring(i+1));
                    }
                    if(findValidWildChar) {
                        return true;
                    }
                }
                /* If all the children are null, return false */
                return findValidWildChar;
            } else {
                /* When the character is not a wildcard */
                int index = getCharToInt(word.charAt(i));
                TrieNode t = temp.children[index];
                if (t == null) {
                    return false;
                }
                temp = t;
            }
        }

        if(temp != null && temp.value > 0)
            return true;

        return false;
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

    public TrieNode getNewNode() {
        TrieNode[] children = new TrieNode[MAX_ALPHABET_SIZE];
        for(int i = 0; i < MAX_ALPHABET_SIZE; ++i) {
            children[i] = null;
        }
        return new TrieNode(0, children);
    }

    class TrieNode {
        int value;
        TrieNode[] children;

        public TrieNode(int value, TrieNode[] children) {
        /* used to mark leaf nodes */
            this.value = value;
            this.children = children;
        }
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("word");
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        wordDictionary.addWord("a");

        System.out.println(wordDictionary.search("pad"));
        System.out.println(wordDictionary.search("bad"));
        System.out.println(wordDictionary.search(".ad"));
        System.out.println(wordDictionary.search("b.."));
        System.out.println(wordDictionary.search("..."));
        System.out.println(wordDictionary.search(".."));
        System.out.println(wordDictionary.search("d.d"));

        System.out.println(wordDictionary.search("a."));
        System.out.println(wordDictionary.search(".a"));
    }
}
