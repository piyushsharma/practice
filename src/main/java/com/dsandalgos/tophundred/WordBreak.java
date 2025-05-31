package com.dsandalgos.tophundred;

import java.util.*;

/**
 * Created by Piyush Sharma
 */

/*
Given a string s and a dictionary of words dict, determine if s can be segmented
into a space-separated sequence of one or more dictionary words.

For example, given
s = "helloworld",
dict = ["hello", "world"].

Return true because "leetcode" can be segmented as "leet code".

*/


public class WordBreak {

    public boolean wordBreakBFS(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int[] visited = new int[s.length()];

        while(!queue.isEmpty()) {

            int checkFromIndex = queue.remove();
            if(visited[checkFromIndex] == 0) {
                for (int endingAtIndex = checkFromIndex + 1; endingAtIndex <= s.length(); ++endingAtIndex) {
                    if(dict.contains(s.substring(checkFromIndex, endingAtIndex))) {
                        queue.add(endingAtIndex);
                        if(endingAtIndex == s.length()) {
                            return true;
                        }
                    }
                }
                visited[checkFromIndex] = 1;
            }
        }
        return false;
    }


    public boolean wordBreakBruteForce(String s, Set<String> wordDict) {

        if(s.isEmpty() || wordDict.contains(s)) return true;

        // O(n^2) because you need to print all possibilities
        for(int i = 0; i < s.length(); ++i) {

            // note that here, i is exclusive and 0 is inclusive
            String partialString = s.substring(0, i);
            if(wordDict.contains(partialString) && wordBreakBruteForce(s.substring(i), wordDict)) {
                return true;
            }
        }

        return false;
    }

    public int wordBreakCount(String s, Set<String> wordDict) {
        if(s.isEmpty()) return 1;
        int count = 0;
        // O(n^2) because you need to print all possibilities
        // note the loop will also run for i == s.length, so that we get the base condition s.isEmpty
        for(int i = 0; i <= s.length(); ++i) {

            // note that here, i is exclusive and 0 is inclusive
            String partialString = s.substring(0, i);
            if(wordDict.contains(partialString)) {
                count += wordBreakCount(s.substring(i), wordDict);
            }
        }

        return count;
    }


    public boolean wordBreakDP(String s, Set<String> wordDict) {

        if(s.isEmpty() || wordDict.contains(s)) return true;

        // Create the DP table to store results of subproblems. The value dpCache[i]
        // will be true if str[0..i-1] can be segmented into dictionary words, otherwise false.
        boolean[] dpCache = new boolean[s.length() + 1];
        dpCache[0] = true; // empty string

        for (int i = 1; i <= s.length(); ++i) {
            for (int j = 0; j < i; ++j) {

                if (dpCache[j] && wordDict.contains(s.substring(j, i))) {
                    dpCache[i] = true;
                    break;
                }
            }
        }
        return dpCache[s.length()];
    }


    public List<String> wordBreakList(String s, Set<String> wordDict) {
        List<String> result = new ArrayList<>();
        wordBreak(s, wordDict, "", result);
        return result;
    }

    private void wordBreak(String s, Set<String> wordDict, String r, List<String> result) {

        // O(n^2) because you need to print all possibilities
        for(int i = 0; i <= s.length(); ++i) {

            // note that here, i is exclusive and 0 is inclusive
            String partialString = s.substring(0, i);
            if (wordDict.contains(partialString)) {

                if (i == s.length()) {
                    r += partialString + "\n";
                    result.add(r);
                    return;
                }
                wordBreak(s.substring(i), wordDict, r + partialString + " ", result);
            }
        }
    }


    public static void main(String[] args) {

        WordBreak w = new WordBreak();

        Set<String> stringList = new HashSet<>();
        stringList.add("leet");
        stringList.add("code");
        stringList.add("aaaa");
        stringList.add("aa");
        stringList.add("mobile");
        stringList.add("sam");
        stringList.add("sung");
        stringList.add("man");
        stringList.add("mango");
        stringList.add("icecream");
        stringList.add("and");
        stringList.add("go");
        stringList.add("i");
        stringList.add("like");
        stringList.add("likei");
        stringList.add("ice");
        stringList.add("cream");
        stringList.add("take");
        stringList.add("bath");
        stringList.add("and");
        stringList.add("come");
        stringList.add("bat");
        stringList.add("hand");
        stringList.add("likei");


        String[] testCases = new String[]{"ilikesamsung", "iiiiiiii",
                "samsungandmango", "ilikelikeimangoiii", "samsungandmangok",
                "ilikelikeimangoiii", "takebathandcome",  ""};

        for(String s : testCases) {
            System.out.printf("String: %s, bruteForce: %s, dp: %s, count: %d",
                    s,
                    w.wordBreakBruteForce(s, stringList),
                    w.wordBreakDP(s, stringList),
                    w.wordBreakCount(s, stringList));

            List<String> result = w.wordBreakList(s, stringList);
            System.out.printf("\n");
            for(String i : result) {
                System.out.printf("%s ", i);
            }
            System.out.printf("\n");
        }
    }

}
