package com.dsandalgos.tophundred;

import java.util.*;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a digit string, return all possible letter combinations that the number could represent.
A mapping of digit to letters (just like on the telephone buttons) is given below.
 2 - abc
 3 - def
 4 - ghi
 5 - jkl
 6 - mno
 7 - pqrs
 8 - tuv
 9 - wxyz

Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]

Although the above answer is in lexicographical order, your answer could be in any order you want.
*/

public class LetterCombinationsOfPhoneNumber {

    public List<String> letterCombinationsBackTrack(String digits) {
        if(digits == null || digits.length() == 0) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        letterCombinationsBackTrack("", digits, result);
        return result;
    }

    Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    private void letterCombinationsBackTrack(String curStr, String digits, List<String> result) {
        if(digits.length() == 0) {
            result.add(curStr);
            return;
        }

        String options = phone.get(digits.substring(0, 1));
        for(int i = 0; i < options.length(); ++i) {
            String letter = String.valueOf(options.charAt(i));
            letterCombinationsBackTrack(curStr + letter, digits.substring(1), result);
        }
    }


    public List<String> letterCombinationsBFS(String digits) {
        LinkedList<String> result = new LinkedList<>();
        if(digits == null || digits.isEmpty()) {
            return result;
        }

        String[] numberToLetter = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        result.add("");


        while(result.peek().length() != digits.length()) {
            String queueItem = result.remove();

            // note here - we are using the length of the item removed because and not an index
            // because we will need to read the same character for multiple entries in the list
            // eg input => "23", queue has "a", "b", "c" ; we want to read 3 for all three of them
            String options = numberToLetter[digits.charAt(queueItem.length()) - '0'];
            for(char c : options.toCharArray()) {
                result.addLast(queueItem + c);
            }
        }

        return result;
    }


    public List<String> letterCombinationsA(String digits) {

        List<String> result = new ArrayList<String>();
        // base conditions
        if(digits == null || digits.isEmpty())
            return result;

        digits = digits.replace("0", "");
        digits = digits.replace("1", "");
        String[] numberToLetter = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        StringBuilder sb = new StringBuilder("");

        int length = digits.length();
        makeCombinations(digits, 0, length, sb, result, numberToLetter);
        return result;
    }

    public void makeCombinations(String digits, int curIndex, int length, StringBuilder sb,
                                 List<String> result, String[] numberToLetter) {

        if(curIndex >= length) {
            result.add(sb.toString());
            return;
        }
        String val = numberToLetter[digits.charAt(curIndex) - '0'];

        for(int i = 0; i < val.length(); ++i) {
            makeCombinations(digits, curIndex + 1, length, sb.append(val.charAt(i)), result, numberToLetter);
        }
    }


    public List<String> letterCombinations(String digits) {
        String[] numberToLetter = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> result = new ArrayList<String>();

        if(digits == null || digits.isEmpty())
            return result;

        digits = digits.replace("0", "");
        digits = digits.replace("1", "");

        getCombinationFromDigit("", 0, digits, result, numberToLetter);
        return result;
    }

    public void getCombinationFromDigit(String str, int dIndex, String digits, List<String> result, String[] nToL) {
        if(str.length() == digits.length()) {
            result.add(str);
            return;
        }

        String s = nToL[digits.charAt(dIndex) - '0'];
        for(int j = 0; j < s.length(); ++j) {
            getCombinationFromDigit(str + s.charAt(j), dIndex + 1, digits, result, nToL);
        }
    }


    public static void main(String[] args) {
        LetterCombinationsOfPhoneNumber l = new LetterCombinationsOfPhoneNumber();

//        List<String> res = l.letterCombinationsBFS("27");
//        for(String s : res) {
//            System.out.println(s);
//        }
//
//

        List<String> result = l.letterCombinationsBackTrack("27");
        for(String s : result) {
            System.out.println(s);
        }
    }
}
