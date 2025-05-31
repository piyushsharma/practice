package com.dsandalgos.tophundred;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string S and a string T, find the minimum window in S
 * which will contain all the characters in T in complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 *
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */

public class MinimumWindowSubstring {

	public String minWindow(String s, String t) {

		Map<Character, Integer> mT = new HashMap<>();
		for(char c : t.toCharArray()) {
			mT.put(c, mT.getOrDefault(c, 0) + 1);
		}

		int left = 0;
		int right = 0;

		int resultLeft = -1;
		int resultRight = -1;
		Map<Character, Integer> curMap = new HashMap<>();
		int found = 0;

		while (right < s.length()) {

			char c = s.charAt(right);
			curMap.put(c, curMap.getOrDefault(c, 0) + 1);

			// note we must have an == here and not >=, as we don't want to increment
			// found when we find keys that are already present and we just found another instance
			if(mT.containsKey(c) && curMap.get(c).intValue() == mT.get(c).intValue()) {
				++found;
			}

			// if we found all characters in mT, increment left as long
			// as the required characters are still in the window
			while(left <= right && found == mT.size()) {

				if(resultLeft == -1 || (resultRight - resultLeft) > (right - left)) {
					resultLeft = left;
					resultRight = right;
				}

				// increment left
				char leftChar = s.charAt(left);
				curMap.put(leftChar, curMap.getOrDefault(leftChar, 0) - 1);

				if(mT.containsKey(leftChar) && curMap.get(leftChar).intValue() < mT.get(leftChar).intValue()) {
					--found;
				}

				++left;
			}

			++right;
		}

		return resultLeft == -1 ? "" : s.substring(resultLeft, resultRight + 1);
	}


	public String minWindowWithMapCheck(String s, String t) {

		Map<Character, Integer> mT = new HashMap<>();
		for(char c : t.toCharArray()) {
			mT.put(c, mT.getOrDefault(c, 0) + 1);
		}

		int left = 0;
		int right = 0;

		int resultLeft = -1;
		int resultRight = -1;
		Map<Character, Integer> curMap = new HashMap<>();

		while (left <= right) {

			if(curMap.keySet().containsAll(mT.keySet()) && checkValueCount(curMap, mT)) {
				if(resultLeft == -1 || (resultRight - resultLeft) > (right - left)) {
					resultLeft = left;
					resultRight = right;
				}

				int count = curMap.get(s.charAt(left));
				if(count == 1) {
					curMap.remove(s.charAt(left));
				} else {
					curMap.put(s.charAt(left), count - 1);
				}
				++left;
			} else {

				if(right < s.length()) {
					char c = s.charAt(right);
					curMap.put(c, curMap.getOrDefault(c, 0) + 1);
					++right;
				} else {
					++left;
				}
			}
		}

		return resultLeft == -1 ? "" : s.substring(resultLeft, resultRight);
	}


	private boolean checkValueCount(Map<Character, Integer> curMap, Map<Character, Integer> mT) {
		for(Character c : mT.keySet()) {
			if(mT.get(c) > curMap.get(c)) {
				return false;
			}
		}
		return true;
 	}


	private static int minimumWindowSubstring(String pattern, String str) {
		int minLength = Integer.MAX_VALUE;
		int left = 0, right = 0;
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		Set<Character> set = new HashSet<Character>();
		for (int i = 0; i < pattern.length(); i++)
			set.add(pattern.charAt(i));

		while (left < str.length() && right < str.length()) {
			char ch = str.charAt(right);
			if (set.contains(ch)) {
				int count = map.containsKey(ch) ? map.get(ch) + 1 : 1;
				map.put(ch, count);

				if (map.size() == pattern.length()) {
					minLength = Math.min(minLength, right - left + 1);
					while (left < right) {
						ch = str.charAt(left);
						Integer remaining = map.get(ch);
						if (remaining != null && remaining == 1) {
							map.remove(ch);
							minLength = Math.min(minLength, right - left + 1);
							left++;
							break;
						} else if (remaining != null)
							map.put(ch, remaining - 1);
						left++;
					}
				}
			}
			right++;
		}
		return minLength == Integer.MAX_VALUE ? Integer.MAX_VALUE : Math.min(minLength, right - left + 1);
	}

	private static void test() {
		assert (minimumWindowSubstring("ab", "acvbsshsaf") == 4);
		assert (minimumWindowSubstring("ab", "ab") == 2);
		assert (minimumWindowSubstring("a", "a") == 1);
		assert (minimumWindowSubstring("aprlfhlafh", "hla") == Integer.MAX_VALUE);
		assert (minimumWindowSubstring("hla", "aprlfhlafh") == 3);
		System.out.println("PASSED!");
	}

	public static void main(String[] args) {

		System.out.println(new MinimumWindowSubstring().minWindow("ADOBECODEBANC", "ABC"));
		System.out.println(new MinimumWindowSubstring().minWindow("ADOBECODEBANC", "AB"));
		System.out.println(new MinimumWindowSubstring().minWindow("ADOBECODEBANC", "ADZ"));
		System.out.println(new MinimumWindowSubstring().minWindow("ADOBECODEBANC", "A"));
		test();

//		System.out.println(minWindow("acvbsshsaf", "ab"));
//		System.out.println(minWindow("ab", "ab"));
//		System.out.println(minWindow("a", "a"));
//		System.out.println(minWindow("aprlfhlafh", "hla"));
//		System.out.println(minWindow("hla", "aprlfhlafh"));
//		System.out.println(minWindow("ADOBECODEBANC", "ABC"));

	}
}