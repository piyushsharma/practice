package com.dsandalgos.string;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given an integer, convert it to a roman numeral.
Input is guaranteed to be within the range from 1 to 3999.
 */

public class IntegerToRoman {

    public String intToRoman(int num) {

        StringBuilder sb = new StringBuilder();

        String[] huns = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        while(num >= 1000) {
            sb.append("M");
            num -= 1000;
        }

        sb.append(huns[num/100]);
        num = num % 100;

        sb.append(tens[num/10]);
        num = num % 10;

        sb.append(ones[num]);

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new IntegerToRoman().intToRoman(1996));
    }
}
