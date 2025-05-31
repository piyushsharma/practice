package com.dsandalgos.string;

import java.util.*;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note:
All words have the same length.
All words contain only lowercase alphabetic characters.
 */


public class WordLadderTwo {

    class Word {
        String word;
        int steps;
        Word previous;
        public Word(String w, int s, Word previous) {
            this.word = w;
            this.steps = s;
            this.previous = null;
        }
    }

    public List<List<String>> findLadders(String start, String end, Set<String> wordList) {
        List<List<String>> result = new ArrayList<List<String>>();

        // add end word in dict if not present
        wordList.add(end);

        Queue<Word> q = new LinkedList<Word>();
        q.add(new Word(start, 1, null));

        List<String> step = new ArrayList<String>();
        while (!q.isEmpty()) {
            Word top = q.remove();

            step.add(top.word);
            if(top.word.equals(end)) {
                result.add(step);
                step = new ArrayList<String>();
            }

            char[] word = top.word.toCharArray();
            for (int i = 0; i < word.length; ++i) {
                char x = word[i];

                for (char c = 'a'; c <= 'z'; ++c) {
                    if (c != x)
                        word[i] = c;


                    String possibleNextWord = new String(word);
                    if (wordList.contains(possibleNextWord)) {
                        q.add(new Word(possibleNextWord, top.steps + 1, top));
                        wordList.remove(possibleNextWord);
                    }
                }
                word[i] = x;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String bw = "hit";
        String ew = "cog";

        Set<String> wordDict = new HashSet<String>();
        wordDict.add("hot");
        wordDict.add("dot");
        wordDict.add("dog");
        wordDict.add("lot");
        wordDict.add("log");

//        bw = "a";
//        ew = "c";
//        wordDict.add("a");
//        wordDict.add("c");

        List<List<String>> result = new WordLadderTwo().findLadders(bw, ew, wordDict);
        for(List<String> l : result) {
            for (String s : l) {
                System.out.printf(s);
            }
            System.out.printf("\n");
        }

    }
}
