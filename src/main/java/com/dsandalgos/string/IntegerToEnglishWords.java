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


    public String numberToWords(int num) {
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
