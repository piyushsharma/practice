package com.dsandalgos.math;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB
*/

public class ExcelSheetColumnTitle {

    public String convertToTitle(int n) {

        StringBuilder sb = new StringBuilder();
        while(n > 0) {
            char c = (char)((n - 1) % 26 + 'A');
            sb.append(c);
            n = (n - 1)/26;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        ExcelSheetColumnTitle e = new ExcelSheetColumnTitle();

        System.out.println(e.convertToTitle(1));
        System.out.println(e.convertToTitle(26));
        System.out.println(e.convertToTitle(28));
        System.out.println(e.convertToTitle(52));
        System.out.println(e.convertToTitle(53));
        System.out.println(e.convertToTitle(78));
        System.out.println(e.convertToTitle(130));
        System.out.println(e.convertToTitle(703));

    }

}
