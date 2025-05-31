package com.dsandalgos.string;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".
 */

public class AddBinary {

    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder("");

        int carry = 0;
        int aLen = a.length();
        int bLen = b.length();
        int i = aLen - 1;
        int j = bLen - 1;

        while(i >= 0 || j >= 0) {
            int x = 0;
            if(i >= 0)
                x = a.charAt(i) - '0';

            int y = 0;
            if(j >= 0)
                y = b.charAt(j) - '0';

            if(x + y + carry == 0 ) {
                sb.append('0');
                carry = 0;
            }
            else if(x + y + carry == 1 ) {
                sb.append('1');
                carry = 0;
            }
            else if(x + y + carry == 2 ) {
                sb.append('0');
                carry = 1;
            }
            else if(x + y + carry > 2 ) {
                sb.append('1');
                carry = 1;
            }
            --i;
            --j;
        }
        if(carry != 0) {
            sb.append('1');
        }

        return sb.reverse().toString();
    }
    
    // alternate implementation - easier logic for carry
    // efficient also !!!
    public String addBinaryV3(String a, String b) {

        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();

        while(i >= 0 || j >=0) {
            int intA = i >= 0 ? a.charAt(i) - '0' : 0;
            int intB = j >= 0 ? b.charAt(j) - '0' : 0;

            // add three numbers
            int res = intA + intB + carry;
            sb.append((char)((res % 2) + '0'));
            carry = res / 2;
            --i;
            --j;
        }

        if(carry == 1) {
            sb.append('1');
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new AddBinary().addBinary("1", "0"));
        System.out.println(new AddBinary().addBinary("1", "111"));
        System.out.println(new AddBinary().addBinary("11", "1"));

        System.out.println("===================================");
        System.out.println(new AddBinary().addBinaryV3("1", "0"));
        System.out.println(new AddBinary().addBinaryV3("1", "111"));
        System.out.println(new AddBinary().addBinaryV3("11", "1"));
    }

}