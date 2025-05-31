package com.dsandalgos.string;

import java.util.*;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given two words (beginWord and endWord), and a dictionary,
find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
*/


public class WordLadder {

    class Word {
        String word;
        int steps;
        public Word(String w, int s) {
            this.word = w;
            this.steps = s;
        }
    }

    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        int result = 0;
        Queue<Word> q = new LinkedList<Word>();
        q.add(new Word(beginWord, 1));

        while (!q.isEmpty()) {
            Word top = q.remove();

            char[] word = top.word.toCharArray();
            for (int i = 0; i < word.length; ++i) {
                char originalChar = word[i];

                // loop around all possible characters and check all transformations
                // if possible transformation present in dictionary, add that to the queue
                for (char curChar = 'a'; curChar <= 'z'; ++curChar) {

                    if (curChar != originalChar) {
                        word[i] = curChar;
                    }

                    String possibleNextWord = new String(word);
                    // end word might or might not be present in the dict
                    if (possibleNextWord.equals(endWord)) {
                        return top.steps + 1;
                    }
                    if (wordList.contains(possibleNextWord)) {
                        q.add(new Word(possibleNextWord, top.steps + 1));
                        wordList.remove(possibleNextWord);
                    }
                }

                word[i] = originalChar;
            }
        }

        return result;
    }


    // alternate implementation
    private int ladderLengthV2(String beginWord, String endWord, Set<String> wordList) {

        if(beginWord.equals(endWord)) return 0;

        Queue<String> wordQueue = new LinkedList<>();
        Queue<Integer> stepQueue = new LinkedList<>();

        wordQueue.add(beginWord);
        stepQueue.add(1);

        while(!wordQueue.isEmpty()) {

            String word = wordQueue.remove();
            int steps = stepQueue.remove();

            if(wordList.contains(word)) wordList.remove(word);

            char[] wordChars = word.toCharArray();
            for(int i = 0; i < wordChars.length; ++i) {

                char originalChar = wordChars[i];

                for(char curChar = 'a'; curChar <= 'z'; ++curChar) {

                    if(curChar == originalChar) continue;

                    wordChars[i] = curChar;
                    String nextWord = new String(wordChars);

                    if(nextWord.equals(endWord)) {
                        return steps + 1;
                    }

                    if(wordList.contains(nextWord)) {
                        wordQueue.add(nextWord);
                        stepQueue.add(steps + 1);
                    }
                }

                wordChars[i] = originalChar;
            }
        }

        return 0;
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
        wordDict.add("a");
        wordDict.add("c");

//        System.out.println(new WordLadder().ladderLength(bw, ew, wordDict));
        System.out.println(new WordLadder().ladderLengthV2(bw, ew, wordDict));
    }
}
