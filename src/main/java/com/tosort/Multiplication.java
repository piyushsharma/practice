package com.tosort;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */
public class Multiplication {

    public static void main(String[] args) {
        int start = 1;
        int end = 16;
        printTables(start, end);
    }

    private static void printTables(int start, int end) {
        System.out.printf("   | ");
        for(int i = start; i <= end; ++i) {
            System.out.printf("%s ", pad(i));
        }
        System.out.println();
        System.out.printf("---+");
        for(int i = start; i <= end; ++i) {
            System.out.printf("----");
        }
        System.out.println();
        for(int i = start; i <= end; ++i) {
            System.out.printf("%s| ", pad(i));
            for(int j = start; j <= end; ++j) {
                System.out.printf("%s ", pad(i * j));
            }
            System.out.println();
        }
    }

    // pad: add blanks to make it 3 digit long
    public static String pad(int x) {
        String s = new String();
        if (x < 10) s = "  " + x;
        else if (x < 100) s = " " + x;
        else s = "" + x;
        return s;
    }
}
