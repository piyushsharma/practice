package com.tosort;

/**
 Reverse digits of an integer.
 Example1: x = 123, return 321
 Example2: x = -123, return -321

 */
public class ReverseIntegerTwo {

    public int reverse(int n) {

        double dn = (double)n;
        if(n < 0) {
            dn = -dn;
        }
        double reverseVal = 0;

        while(dn > 0) {
            double r = dn % 10;
            int q = ((int)dn)/10;

            reverseVal = reverseVal * 10 + r;
            dn = q;
        }

        /* If the sign changed, overflow occured, return 0 */
        if(reverseVal < 0)
            return 0;

        if(n < 0) {
            reverseVal = -reverseVal;
        }

        if(reverseVal > Integer.MAX_VALUE || reverseVal < Integer.MIN_VALUE) {
            reverseVal = 0;
        }

        return (int)reverseVal;
    }

    public static void main(String[] args) {
        System.out.println(new ReverseIntegerTwo().reverse(2));
        System.out.println(new ReverseIntegerTwo().reverse(20));
        System.out.println(new ReverseIntegerTwo().reverse(200));
        System.out.println(new ReverseIntegerTwo().reverse(-2));
        System.out.println(new ReverseIntegerTwo().reverse(43261596));
        System.out.println(new ReverseIntegerTwo().reverse(2147483647));
        System.out.println(new ReverseIntegerTwo().reverse(2147483646));
        System.out.println(new ReverseIntegerTwo().reverse(-2147483647));
        System.out.println(new ReverseIntegerTwo().reverse(-1000000003));
    }

}
