package com.dsandalgos.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CustomSortString {


    public String customSortString(String order, String s) {

        Character[] arr = new Character[s.length()];
        for (int i = 0; i < s.length(); i++) {
            arr[i] = s.charAt(i);
        }

        Arrays.sort(arr, (c1, c2) -> order.indexOf(c1) - order.indexOf(c2));

        String result = "";
        for(char c : arr) {
            result += c;
        }
        return result;

    }

    public String customSortStringV2(String order, String s) {
        // Create a frequency table
        Map<Character, Integer> freq = new HashMap<>();

        // Initialize frequencies of letters
        // freq[c] = frequency of char c in s
        for (int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);
            freq.put(letter, freq.getOrDefault(letter, 0) + 1);
        }

        // Iterate order string to append to result
        int K = order.length();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < K; i++) {
            char letter = order.charAt(i);
            int count = freq.getOrDefault(letter, 0);
            while(count > 0) {
                result.append(letter);
                --count;
            }
            freq.remove(letter);
        }

        // Iterate through freq and append remaining letters
        // This is necessary because some letters may not appear in `order`
        for (char letter : freq.keySet()) {
            int count = freq.get(letter);
            while (count > 0) {
                result.append(letter);
                count--;
            }
        }

        // Return the result
        return result.toString();
    }
}
