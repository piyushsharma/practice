package com.dsandalgos.string;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2^31 - 1.

For example,
123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
*/

public class IntegerToEnglishWords {

    // Arrays to store words for numbers less than 10, 20, and 100
    private static final String[] belowTen = { "", "One", "Two", "Three", "Four", "Five", "Six",
            "Seven", "Eight", "Nine" };
    private static final String[] belowTwenty = { "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
            "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };

    private static final String[] belowHundred = { "", "Ten", "Twenty", "Thirty", "Forty",
            "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };

    public String numberToWords(int num) {
        // Handle the special case where the number is zero
        if (num == 0) {
            return "Zero";
        }
        // Call the helper function to start the conversion
        return convertToWords(num);
    }

    String convertToWords(int num) {

        if(num < 10) {
            return belowTen[num];
        }

        if(num < 20) {
            return belowTwenty[num - 10];
        }

        if(num < 100) {
            return belowHundred[num/10] + (num % 10 == 0 ? "" : " " + convertToWords(num % 10));
        }

        if(num < 1000) {
            return belowTen[num/100] + " Hundred" + (num % 100 == 0 ? "" : " " + convertToWords(num % 100));
        }

        if(num < 1000000) {
            return convertToWords(num/1000) + " Thousand"
                    + (num % 1000 == 0 ? "" : " " + convertToWords(num % 1000));
        }

        if(num < 1000000000) {
            return convertToWords(num/1000000) + " Million"
                    + (num % 1000000 == 0 ? "" : " " + convertToWords(num % 1000000));
        }


        return convertToWords(num/1000000000) + " Billion"
                + (num % 1000000000 == 0 ? "" : " " + convertToWords(num % 1000000000));

    }

    /**
     *  V2 BELOW:
     *
     */


    private static final String[] TENS_NAMES = { "", " Ten", " Twenty",
            " Thirty", " Forty", " Fifty", " Sixty", " Seventy", " Eighty",
            " Ninety" };

    private static final String[] NUM_NAMES = { "", " One", " Two", " Three",
            " Four", " Five", " Six", " Seven", " Eight", " Nine", " Ten",
            " Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen",
            " Sixteen", " Seventeen", " Eighteen", " Nineteen" };


    private static final int BILLION = 1000000000;
    private static final int MILLION = 1000000;
    private static final int THOUSAND = 1000;

    private static final String S_BILLION = "Billion";
    private static final String S_MILLION = "Million";
    private static final String S_THOUSAND = "Thousand";
    private static final String S_HUNDRED = "Hundred";


    public String numberToWordsV2(int num) {
        if(num == 0)
            return "Zero";

        // 2147483647 => Integer.MAX_VALUE => 2^31 -1
        StringBuilder sb = new StringBuilder();

        num = processAboveThousand(num, BILLION, S_BILLION, sb);
        num = processAboveThousand(num, MILLION, S_MILLION, sb);
        num = processAboveThousand(num, THOUSAND, S_THOUSAND, sb);

        // val will be < 1000
        sb.append(processLessThanThousand(num));

        return sb.toString().substring(1);
    }


    public int processAboveThousand(int input, int divisor, String divString, StringBuilder sb) {
        int val = input / divisor;
        if(val > 0) {
            sb.append(processLessThanThousand(val));
            sb.append(" ");
            sb.append(divString);
        }
        return input % divisor;
    }


    public String processLessThanThousand(int num) {
        if(num == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        int val = num / 100;
        if(val > 0) {
            sb.append(NUM_NAMES[val]);
            sb.append(" ");
            sb.append(S_HUNDRED);
        }
        num = num % 100;

        // if num is less than 20, use the NUM_NAMES array
        if(num < 20) {
            sb.append(NUM_NAMES[num]);
        } else {
            // if greater than 20, get the number in tens place first
            val = num / 10;
            if (val > 0) {
                sb.append(TENS_NAMES[val]);
            }
            num = num % 10;
            // add word for units place
            sb.append(NUM_NAMES[num]);
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        IntegerToEnglishWords integerToEnglishWords = new IntegerToEnglishWords();
        System.out.println(integerToEnglishWords.numberToWords(Integer.MAX_VALUE));
        System.out.println(integerToEnglishWords.numberToWords(0));
        System.out.println(integerToEnglishWords.numberToWords(22));
        System.out.println(integerToEnglishWords.numberToWords(1223333344));
        System.out.println(integerToEnglishWords.numberToWords(1));
    }

}
