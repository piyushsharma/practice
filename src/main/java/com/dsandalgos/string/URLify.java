package com.dsandalgos.string;

/**
 *  Write a method to replace all spaces in a string with %20 - in place
 *  string has sufficient space at the end to hold additional characters,
 *  and true length of the string is given to you
 *
 *  eg. input = "Mr John Smith    ", 13
 *      output = "Mr%20John%20Smith"
 */


public class URLify {

	private String urlify(char[] input, int trueLength) {

		int k = input.length - 1;
		// or we can iterate to find number of spaces
		// and calculate k using "trueLength - 1 + (2 * spacecount)

		for(int i = trueLength - 1; i >= 0; --i) {
			if(input[i] == ' ') {
				input[k--] = '0';
				input[k--] = '2';
				input[k--] = '%';

			} else {
				input[k] = input[i];
				--k;
			}
		}

		return String.valueOf(input);
	}

	private String urlify2(char[] input, int trueLength) {
		int j = trueLength - 1;
		int k = input.length - 1;

		for(int i = trueLength - 1; i >= 0; --i) {
			if(input[i] == ' ') {

				k = insertPercent20(input, i, j, k);
				j = i - 1;
			}
		}

		return String.valueOf(input);
	}

	private int insertPercent20(char[] input, int i, int j, int k) {

		while(j != i) {
			input[k] = input[j];
			--k;
			--j;
		}

		input[k--] = '0';
		input[k--] = '2';
		input[k--] = '%';

		return k;
	}


	public static void main(String[] args) {

		String input = " Mr John Smith      ";
		System.out.println(new URLify().urlify(input.toCharArray(), 14));

	}



}

