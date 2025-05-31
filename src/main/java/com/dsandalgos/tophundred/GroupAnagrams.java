package com.dsandalgos.tophundred;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*

Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:
- For the return value, each inner list's elements must follow the lexicographic order.
- All inputs will be in lower-case.
*/

public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> anagrams = new HashMap<>();
        for(int i = 0; i < strs.length; ++i) {
            char[] c = strs[i].toCharArray();
            Arrays.sort(c);
            String sortedStr = new String(c);
            anagrams.computeIfAbsent(sortedStr, (ignored) -> new ArrayList<>());
            anagrams.get(sortedStr).add(strs[i]);
        }
        return anagrams.values().stream().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        GroupAnagrams g = new GroupAnagrams();
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = g.groupAnagrams(strs);
        System.out.println("use breakpoint at this line to make sure res is as expected");
    }
}
