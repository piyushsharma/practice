package com.dsandalgos.string;

public class AtoI {

    public int myAtoi(String str) {
        if(str == null) {
            return 0;
        }
        str = str.trim();
        if(str.isEmpty()) {
            return 0;
        }

        int i = 0;
        int sign = 1;
        long result = 0;
        long max = (long)(Integer.MAX_VALUE) + 1l;

        if(str.charAt(0) == '-') {
            sign = -1;
            ++i;
        } if(str.charAt(0) == '+') {
            ++i;
        }

        while(i < str.length()) {

            if(str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                result = (result * 10) + (str.charAt(i) - '0');

                if(sign == 1 && result > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }

                if(sign == -1 && result > max) {
                    return Integer.MIN_VALUE;
                }

            } else {
                break;
            }
            ++i;
        }

        return (int) (sign * result);
    }

    public static void main(String[] args) {

        AtoI a = new AtoI();

        System.out.println(a.myAtoi("42"));
        System.out.println(a.myAtoi("    -42"));
        System.out.println(a.myAtoi("    +42"));
        System.out.println(a.myAtoi("w42"));
        System.out.println(a.myAtoi("4213 with words"));
        System.out.println(a.myAtoi("-91283472332"));
        System.out.println(a.myAtoi("91283472332"));
        System.out.println(a.myAtoi(Integer.toString(Integer.MIN_VALUE)));
        System.out.println(a.myAtoi(Integer.toString(Integer.MAX_VALUE)));
    }

}
