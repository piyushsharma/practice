package com.dsandalgos.math;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a column title as appear in an Excel sheet, return its corresponding column number.
For example:
    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28
*/

public class ExcelSheetColumnNumber {

    public int titleToNumber(String s) {
        if(s == null || s.isEmpty())
            return 0;

        char[] ls = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        Map<Character, Integer> m = new HashMap<Character, Integer>();
        int j = 1;
        for(char c : ls) {
            m.put(c, j);
            ++j;
        }

        int columnNumber = 0;
        int alphabetPlace  = 0;
        for(int i = s.length() - 1; i >=0; --i) {
            int charDigit = m.get(s.charAt(i));
            columnNumber += charDigit * Math.pow(26, alphabetPlace);
            ++alphabetPlace;
        }
        return columnNumber;
    }


    public int titleToNumberNoMap(String s) {
        if(s == null || s.isEmpty())
            return 0;

        int columnNumber = 0;
        int alphabetPlace  = 0;
        for(int i = s.length() - 1; i >=0; --i) {
            char excelCol = s.charAt(i);
            columnNumber += (excelCol - 'A' + 1) * Math.pow(26, alphabetPlace);
            ++alphabetPlace;
        }
        return columnNumber;
    }


    public static void main(String[] args) {
        ExcelSheetColumnNumber e = new ExcelSheetColumnNumber();

        System.out.println(e.titleToNumber("A"));
        System.out.println(e.titleToNumber("Z"));
        System.out.println(e.titleToNumber("AB"));
        System.out.println(e.titleToNumber("AZ"));
        System.out.println(e.titleToNumber("BA"));
        System.out.println(e.titleToNumber("BZ"));
        System.out.println(e.titleToNumber("DZ"));
        System.out.println(e.titleToNumber("AAA"));

    }

}
