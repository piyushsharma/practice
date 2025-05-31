package com.dsandalgos.misc;

import java.math.BigInteger;

/**
 * Implement a regular expression function isMatch that supports the '.' and '*' symbols.
 * The function receives two strings - text and pattern -
 * and should return true if the text matches the pattern as a regular expression.
 *
 * For simplicity, assume that the actual symbols '.' and '*' do not appear in the text string
 * and are used as special symbols only in the pattern string.
 *
 * In case you aren’t familiar with regular expressions, the function determines if the text and pattern are the equal,
 * where the '.' is treated as a single a character wildcard (see third example),
 * and '*' is matched for a zero or more sequence of the previous letter (see fourth and fifth examples).
 * For more information on regular expression matching, see the Regular Expression Wikipedia page.
 *
 * Explain your algorithm, and analyze its time and space complexities.
 *
 * Examples:
 *
 * input:  text = "aa", pattern = "a"
 * output: false
 *
 * input:  text = "aa", pattern = "aa"
 * output: true
 *
 * input:  text = "abc", pattern = "a.c"
 * output: true
 *
 * input:  text = "abbb", pattern = "ab*"
 * output: true
 *
 * input:  text = "acd", pattern = "ab*c."
 * output: true
 *
 * Constraints:
 *
 * [time limit] 5000ms
 * [input] string text
 * [input] string pattern
 * [output] boolean
 */

public class BasicRegexParser {

	static boolean isMatch(String text, String pattern) {
		return isMatchHelper(text, pattern, 0, 0);
	}

	static boolean isMatchHelper(String text, String pattern, int textIndex, int patternIndex) {

		if(textIndex == text.length()) {
			if(patternIndex == pattern.length()) {
				return true;
			}

			if(patternIndex + 1 < pattern.length() && pattern.charAt(patternIndex + 1) == '*') {
				return isMatchHelper(text, pattern, textIndex, patternIndex + 2);
			} else {
				return false;
			}

		} else if(patternIndex == pattern.length()) {
			// end of pattern but not end of text
			return false;

		} else if (patternIndex + 1 < pattern.length() && pattern.charAt(patternIndex + 1) == '*') {

			if (text.charAt(textIndex) == pattern.charAt(patternIndex)
				|| pattern.charAt(patternIndex) == '.') {
				return isMatchHelper(text, pattern, textIndex + 1, patternIndex + 2);
			}

//			else {
//				return isMatchHelper(text, pattern, textIndex, patternIndex + 2);
//			}

		} else if (text.charAt(textIndex) == pattern.charAt(patternIndex)
				|| pattern.charAt(patternIndex) == '.') {

			return isMatchHelper(text, pattern, textIndex + 1, patternIndex + 1);
		}

		return false;
	}

	static boolean isMatchHelper2(String text, String pattern, int textIndex, int patIndex) {
		// base cases - one of the indexes reached the end of text or pattern
		if (textIndex >= text.length()) {
			if (patIndex >= pattern.length()) {
				return true;
			} else {
				if (patIndex + 1 < pattern.length() && pattern.charAt(patIndex + 1) == '*') {
					return isMatchHelper2(text, pattern, textIndex, patIndex + 2);
				} else {
					return false;
				}
			}

		} else if (patIndex >= pattern.length() && textIndex < text.length()) {
			return false;

		} else if (patIndex + 1 < pattern.length() && pattern.charAt(patIndex + 1) == '*') {
			// string matching for character followed by '*'
			if(pattern.charAt(patIndex) == '.') {
				return isMatchHelper2(text, pattern, textIndex, patIndex + 2);
			}
			if(text.charAt(textIndex) == pattern.charAt(patIndex)) {
				return isMatchHelper2(text, pattern, textIndex + 1, patIndex);
			} else {
				return isMatchHelper2(text, pattern, textIndex, patIndex + 2);
			}

		} else if(pattern.charAt(patIndex) == '.' || text.charAt(textIndex) == pattern.charAt(patIndex)) {
			// string matching for '.' or ordinary char.
			return isMatchHelper2(text, pattern, textIndex + 1, patIndex + 1);

		} else {
			return false;
		}

	}


	public static boolean isMatchLeetcode(String s, String p) {
		// p is empty
		if(p.isEmpty()) return s.isEmpty();

		// p is not empty, if length is 1 or the second character is _not_ *
		if(p.length() == 1 || p.charAt(1) != '*') {

			if(s.length() == 0) {
				return false;
			}

			if(p.charAt(0) == s.charAt(0) || p.charAt(0) == '.') {
				return isMatchLeetcode(s.substring(1), p.substring(1));
			} else {
				return false;
			}
		}

		// p.length() > 1, and the second character is *
		for(int i = 0; i < s.length(); ++i) {

			if(p.charAt(0) == '.' || s.charAt(i) == p.charAt(0)) {

				if(isMatchLeetcode(s.substring(i+1), p.substring(2))) {
					return true;
				}

			} else {
				break;
			}
		}

		return isMatchLeetcode(s, p.substring(2));
	}


	public static void main(String[] args) {

//		System.out.println(isMatchLeetcode("aaaq", "a*aq")); // true
//		System.out.println(isMatchLeetcode("aa", "aa")); // true
//		System.out.println(isMatchLeetcode("aa", "a")); // false
//		System.out.println(isMatchLeetcode("abc", "a.c")); // true
//		System.out.println(isMatchLeetcode("abbb", "ab*")); // true
//		System.out.println(isMatchLeetcode("acd", "ab*c.")); // true
//		System.out.println(isMatchLeetcode("abbbcc", "ab*.*")); // true
//		System.out.println(isMatchLeetcode("abaa", "a.*a*")); // true


		System.out.println(isMatch("aaaq", "a*aq")); // true
		System.out.println(isMatch("aa", "aa")); // true
		System.out.println(isMatch("aa", "a")); // false
		System.out.println(isMatch("abc", "a.c")); // true
		System.out.println(isMatch("abbb", "ab*")); // true
		System.out.println(isMatch("acd", "ab*c.")); // true
		System.out.println(isMatch("abbbcc", "ab*.*")); // true
		System.out.println(isMatch("abaa", "a.*a*")); // true

		BigInteger b = new BigInteger("13");
		boolean ans = b.isProbablePrime(Integer.MAX_VALUE);
		System.out.println(ans);
	}

}
