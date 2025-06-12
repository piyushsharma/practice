package com.tosort;

import java.util.*;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */
public class Anagram {

    public void printAnagrams(List<String> input) {

        Map<String, List<String>> anagramMap = new HashMap<String, List<String>>();

        for(String str : input) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedStr = new String(chars);
            List<String> anagrams = anagramMap.get(sortedStr);
            if(anagrams == null) {
                anagrams = new ArrayList<String>();
                anagramMap.put(sortedStr, anagrams);
            }
            anagrams.add(str);
        }

        for(String sortedStr : anagramMap.keySet()) {
            List<String> anagrams = anagramMap.get(sortedStr);
            for(String anagram : anagrams)
                System.out.print(anagram + " ");
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        List<String> input = new ArrayList<String>();
        input.add("fat");
        input.add("atf");
        input.add("fta");
        input.add("aft");
        input.add("gsg");

        new Anagram().printAnagrams(input);
    }
}
