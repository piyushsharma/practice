package com.tosort;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */
public class AddTwoNumbersBitwise {

    public int add(int x, int y) {
        while(y != 0) {
            int carry = x & y;
            x = x ^ y;
            y = carry << 1;
        }
        return x;
    }

    public static void main(String[] args) {
        System.out.println(new AddTwoNumbersBitwise().add(15, -10));
    }

}
