package com.dsandalgos.tophundred;

/**
 * Created by Piyush Sharma
 */

/*

Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatchV2(const char *s, const char *p)

Some examples:
isMatchV2("aa","a") → false
isMatchV2("aa","aa") → true
isMatchV2("aaa","aa") → false
isMatchV2("aa", "a*") → true
isMatchV2("aa", ".*") → true
isMatchV2("ab", ".*") → true
isMatchV2("aab", "c*a*b") → true

*/



public class RegularExpressionMatching {


	/* cases for pattern:
		1. pattern is empty
		2. pattern has no * (second character is not *)
		3. pattern == [a-z.]*[a-z.] (second character is *)
			 3.1 => * counts for 0, 1 or more than 1 characters
	*/
	private static boolean isMatchWithRecursion(String text, String pattern) {

		// if pattern has been consumed, text must also be consumed
		if (pattern.length() == 0) {
			return text.length() == 0;
		}

		// if pattern just has one character left
		// OR   if pattern has > 1 character and the second char is "not" *
		//      we can use recursion to move ahead
		if (pattern.length() == 1 || pattern.charAt(1) != '*') {

			// make sure text is not empty
			if (text.isEmpty()) {
				return false;
			}

			if (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.') {
				return isMatchWithRecursion(text.substring(1), pattern.substring(1));
			} else {
				return false;
			}

		}

		// if we reach here:
		// pattern.length() > 1, and the second character is *
		for (int i = 0; i < text.length(); ++i) {

			if (pattern.charAt(0) == '.' || text.charAt(i) == pattern.charAt(0)) {

				// we try every combination - the for loop moves the text pointer
				// and tries to consume 0, 1 or more characters with the "*"
				// the point where we consumed x characters, and the recursion returned true,
				// we can return as we found the number of characters to match with "*"
				if (isMatchWithRecursion(text.substring(i + 1), pattern.substring(2))) {

					//eg.  s = "aaaabsds", p = "a*bsds"
					// called ->
					//          aaabsds, bsds
					//          aabsds, bsds
					//          absds, bsds
					//          bsds, bsds
					return true;
				}
			} else {
				break;
			}
		}

		// if we reach here, we could not match anything in text with (.|w)* in the pattern
		return isMatchWithRecursion(text, pattern.substring(2));
	}

	// same as above, instead of for loop, we try with a while loop

	public boolean isMatch(String text, String pattern) {
		if (pattern.length() == 0) {
			return text.length() == 0;
		}

		if (pattern.length() == 1 || pattern.charAt(1) != '*') {
			if (text.isEmpty()) {
				return false;
			}
			if (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.') {
				return isMatch(text.substring(1), pattern.substring(1));
			} else {
				return false;
			}
		} else {
			// assume we don't want to match any character with "X*" pattern
			if (isMatch(text, pattern.substring(2)))
				return true;

			// try matching 0, 1 or more characters from text to the "X*" pattern
			int i = 0;
			while (i < text.length() && (pattern.charAt(0) == '.' || pattern.charAt(0) == text.charAt(i))) {
				if (isMatch(text.substring(i + 1), pattern.substring(2)))
					return true;
				i++;
			}
			return false;
		}
	}


	// dp solution
    /*
    1. If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
    2. If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
    3. If p.charAt(j) == '*':  here are two sub conditions:
               1.   if p.charAt(j-1) != s.charAt(i): dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
               2.   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
                              dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a
                           or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
                           or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
     */

	public boolean isMatchDP(String s, String p) {

		// if pattern length == 0, simply return true/false as per s is empty/not empty
		if (p.isEmpty()) return s.isEmpty();

		boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
		dp[0][0] = true; // both empty

		// pattern is non-empty, initialize dp table for 0 length string
		for (int j = 0; j < p.length(); ++j) {
			// j-1 is sage beacuse first char will not be '*' (assumed no malformed input)
			if (p.charAt(j) == '*' && dp[0][j - 1]) {
				dp[0][j + 1] = true;
			}
		}

		for (int i = 1; i <= s.length(); ++i) {
			for (int j = 1; j <= p.length(); ++j) {

				if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				}

				if (p.charAt(j - 1) == '*') {
					if (j > 0 && p.charAt(j - 2) != s.charAt(i - 1) && p.charAt(j - 2) != '.') {
						// 0 counts for the *
						dp[i][j] = dp[i][j - 2];
					} else {
						// 0 counts, 1 count or more than 1 count
						dp[i][j] = dp[i][j - 2] || dp[i][j - 1] || dp[i - 1][j];
					}
				}
			}
		}
		return dp[s.length()][p.length()];
	}


   /* First of all, this is one of the most difficulty problems.
        It is hard to think through all different cases. The problem should be simplified to handle 2 basic cases:
        1) the second char of pattern is "*"
        2) the second char of pattern is not "*"

        For the 1st case, if the first char of pattern is not ".",
        the first char of pattern and string should be the same. Then continue to match the remaining part.

        For the 2nd case, if the first char of pattern is "." or
        first char of pattern == the first i char of string, continue to match the remaining part.
        */

	public boolean isMatchV2(String s, String p) {
		// base case
		if (p.length() == 0) {
			return s.length() == 0;
		}

		// special case
		if (p.length() == 1) {

			// if the length of s is 0, return false
			if (s.length() < 1) {
				return false;
			}

			//if the first does not match, return false
			else if ((p.charAt(0) != s.charAt(0)) && (p.charAt(0) != '.')) {
				return false;
			}

			// otherwise, compare the rest of the string of s and p.
			else {
				return isMatchV2(s.substring(1), p.substring(1));
			}
		}

		// case 1: when the second char of p is not '*'
		if (p.charAt(1) != '*') {
			if (s.length() < 1) {
				return false;
			}
			if ((p.charAt(0) != s.charAt(0)) && (p.charAt(0) != '.')) {
				return false;
			} else {
				return isMatchV2(s.substring(1), p.substring(1));
			}
		}

		// case 2: when the second char of p is '*', complex case.
		else {
			//case 2.1: a char & '*' can stand for 0 element
			if (isMatchV2(s, p.substring(2))) {
				return true;
			}

			//case 2.2: a char & '*' can stand for 1 or more preceding element,
			//so try every sub string
			int i = 0;
			while (i < s.length() && (s.charAt(i) == p.charAt(0) || p.charAt(0) == '.')) {
				if (isMatchV2(s.substring(i + 1), p.substring(2))) {
					return true;
				}
				i++;
			}
			return false;
		}
	}

	public static void main(String[] args) {
		RegularExpressionMatching r = new RegularExpressionMatching();

		System.out.println(r.isMatchV2("aa", "aa")); // → true
		System.out.println(r.isMatchV2("aaa", "a*a")); // → true
		System.out.println(r.isMatchV2("aa", "a*")); // → true
		System.out.println(r.isMatchV2("aa", ".*")); // → true
		System.out.println(r.isMatchV2("ab", ".*")); // → true
		System.out.println(r.isMatchV2("aab", "c*aab")); // → true
		System.out.println(r.isMatchV2("aab", "c*a*b")); // → true
		System.out.println(r.isMatchV2("a", "ab*")); // → true
		System.out.println(r.isMatchV2("", ".*")); // → true

		System.out.println("===========================");

		System.out.println(r.isMatchV2("ab", ".*c")); // → false
		System.out.println(r.isMatchV2("aa", "a")); // → false
		System.out.println(r.isMatchV2("aab", "a*")); // → false
		System.out.println(r.isMatchV2("aaa", "aa")); // → false
		System.out.println(r.isMatchV2("aaaa", "aaa")); // → false
	}
}
