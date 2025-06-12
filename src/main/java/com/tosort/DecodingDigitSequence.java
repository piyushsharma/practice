package com.tosort;

import java.util.ArrayList;
import java.util.List;


/**
 * Given a digit sequence, count the number of possible decodings of the given digit sequence.
 * 
 * @author Kavi
 */
public class DecodingDigitSequence  {

	private static void printDecodings(String arr, List<Character> decodedStr, int digitIndex)
	{
		if(digitIndex >= arr.length())
		{
			for(Character ch : decodedStr)
				System.out.print(ch);
			System.out.println("");
			return;
		}

		char curDigit = arr.charAt(digitIndex);

		// BUG: Forgot to handle zero case
		if(curDigit != '0')
		{
			decodedStr.add((char)(curDigit+16));
			printDecodings(arr, decodedStr, digitIndex+1);
			decodedStr.remove(decodedStr.size()-1);
		}

		if(digitIndex < arr.length()-1)
		{
			int tens = curDigit - 48;
			int ones = arr.charAt(digitIndex+1) - 48;
			int number = ones + (tens * 10);
			if(number <= 26)
			{
				decodedStr.add((char)('A' + number-1));
				printDecodings(arr, decodedStr, digitIndex+2);

				//BUG: Forgot to remove this.
				decodedStr.remove(decodedStr.size()-1);
			}
		}
	}


    //incorrect
    public static void printDecodedStrings(String prefix, String s) {
        if(s.length() <= 0) {
            System.out.println(prefix);
            return;
        }

        for(int i = 1; i<= s.length(); i++) {
            String p=s.substring(0,i);
            String cur="";int b=1;
            if(i<s.length()){
                cur=s.substring(i,i+1);
                b=(Integer.parseInt(cur));}
            int a=(Integer.parseInt(p));

            if(a>0 && a<=26 && (b>0 && b<=26))
            {
                p=""+(char)(a+64);
                printDecodedStrings(prefix + p, s.substring(i));
            }
        }
    }

	private static int countDecodings(String s) {
		int len = s.length();
        if(len == 0)
            return 0;

		int[] count = new int[len+1];  
		count[0] = 1;
        count[1] = 1;

		for (int i = 2 ; i<= len; ++i) {
            count[i] = 0;
			char c = s.charAt(i-1);

            // If the last digit is not 0, then last digit must add to
            // the number of words
			if ( c != '0' )  
				count[i] = count[i-1];

            // If second last digit is smaller than 2 and last digit is
            // smaller than 7, then last two digits form a valid character
			if (s.charAt(i-2) < '2' || (s.charAt(i-2) == '2' && s.charAt(i-1) < '7'))
				count[i] += count[i-2];
		}  
		return count[len];  
	}

	private static boolean isTwoDigitCode(char a, char b) 
	{  
		return (a=='1' || (a=='2' && b<='6'));  
	}

	public static void main(String[] args) {

        System.out.println(countDecodings("201"));

		List<Character> decoded = new ArrayList<Character>();
		printDecodings("201", decoded, 0);

        System.out.println("++++++++++++++");
        printDecodedStrings("", "201");
	}
}
