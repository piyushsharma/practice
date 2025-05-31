package com.dsandalgos.string;

import java.math.BigInteger;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */


/*
Given two numbers represented as strings, return multiplication of the numbers as a string.
Note: The numbers can be arbitrarily large and are non-negative.
*/


public class MultiplyStrings {

    public String multiplyV2(String num1, String num2) {
        BigInteger n1 = new BigInteger(num1);
        BigInteger n2 = new BigInteger(num2);
        return n1.multiply(n2).toString();
    }

//    http://www.programcreek.com/2014/05/leetcode-multiply-strings-java/
//    Solution without using BigInteger =>
    public String multiply(String num1, String num2) {

        int[] digits = new int[num1.length() + num2.length()];

        // We reverse so that it is easier later to traverse digits array
        // Work out a multiplication example by hand to understand what we are storing in digits if this is not clear
        String n1 = new StringBuilder(num1).reverse().toString();
        String n2 = new StringBuilder(num2).reverse().toString();

        for(int i = 0; i < n1.length(); ++i) {
            for(int j = 0; j < n2.length(); ++j) {
                digits[i+j] += (n1.charAt(i)-'0') * (n2.charAt(j)-'0');
            }
        }

        //calculate each digit
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < digits.length; ++i){
            int mod = digits[i] % 10;
            int carry = digits[i] / 10;
            if(i+1 < digits.length){
                digits[i+1] += carry;
            }
            // insert at 0 to make the final number
            sb.insert(0, mod);
        }

        //remove front 0's if any
        while(sb.charAt(0) == '0' && sb.length()> 1){
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    public String multiplyStr(String num1, String num2) {

        int dIndex = num1.length() + num2.length();
        int[] digits = new int[dIndex];

        int digitBuffer = 0;
        for(int j = num2.length() - 1; j >= 0; --j) {
            fillDigits(digits, num2.charAt(j), num1, digitBuffer);
            ++digitBuffer;
        }

        //calculate each digit
        StringBuilder sb = new StringBuilder();

        int carry = 0;
        for(int i = digits.length - 1; i >= 0; --i) {
            int mod = digits[i] % 10;
            carry = digits[i] / 10;
            if(i-1 >= 0){
                digits[i-1] += carry;
            }
            // insert at 0 to make the final number
            sb.insert(0, mod);
        }
        if(carry > 0) {
            sb.insert(0, carry);
        }

        //remove front 0's if any
        while(sb.charAt(0) == '0' && sb.length()> 1){
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    private void fillDigits(int[] digits, char num2Char, String num1, int digitBuffer) {
        int dIndex = digits.length - 1;
        for(int i = num1.length() - 1; i >= 0; --i) {
            digits[dIndex - digitBuffer] += (num1.charAt(i)-'0') * (num2Char-'0');
            --dIndex;
        }
    }


    public String multiplyBest(String num1, String num2) {

        int[] digits = new int[num1.length() + num2.length()];
        for(int j = num2.length() - 1; j >= 0; --j) {
            for(int i = num1.length() - 1; i >= 0; --i) {
                int mul = (num1.charAt(i)-'0') * (num2.charAt(j)-'0');
                int p1 = i + j;
                int p2 = i + j + 1;

                int sum = mul + digits[p2];

                digits[p1] += sum / 10;
                digits[p2] = sum % 10;
            }
        }

        //calculate each digit
        StringBuilder sb = new StringBuilder();
        for(int n : digits) {
            sb.append(n);
        }
        while(sb.charAt(0) == '0' && sb.length() > 1){
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        MultiplyStrings m = new MultiplyStrings();
        System.out.println(m.multiply("123", "45"));

        System.out.println(m.multiplyStr("123", "45"));
    }
}

