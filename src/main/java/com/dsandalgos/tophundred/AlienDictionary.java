package com.dsandalgos.tophundred;

import java.util.*;

/**
 *
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
 * You receive a list of non-empty words from the dictionary, where words are sorted lexicographically
 * by the rules of this new language. Derive the order of letters in this language.
 *
 * Example 1:
 *
 * Input:
 * [
 *   "wrt",
 *   "wrf",
 *   "er",
 *   "ett",
 *   "rftt"
 * ]
 *
 * Output: "wertf"
 * Example 2:
 *
 * Input:
 * [
 *   "z",
 *   "x"
 * ]
 *
 * Output: "zx"
 * Example 3:
 *
 * Input:
 * [
 *   "z",
 *   "x",
 *   "z"
 * ]
 *
 * Output: ""
 *
 * Explanation: The order is invalid, so return "".
 * Note:
 *
 * You may assume all letters are in lowercase.
 * You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
 * If the order is invalid, return an empty string.
 * There may be multiple valid order of letters, return any one of them is fine.
 *
 */

public class AlienDictionary {


	public String alienOrder(String[] words) {
		if(words == null || words.length == 0) {
			return "";
		}

		Map<Character, Integer> degree = new HashMap<>();
		Map<Character, Set<Character>> charPrecedenceGraph = new HashMap<>();

		buildGraph(words, charPrecedenceGraph, degree);

		return topologicalSort(charPrecedenceGraph, degree);


	}

	private String topologicalSort(Map<Character, Set<Character>> charPrecedenceGraph, Map<Character, Integer> degree) {

		Queue<Character> queue = new LinkedList<>();
		for(Character c : degree.keySet()) {
			if(degree.get(c) == 0) {
				queue.add(c);
			}
		}

		StringBuilder result = new StringBuilder();
		while(!queue.isEmpty()) {

			char c = queue.remove();
			result.append(c);

			Set<Character> successors = charPrecedenceGraph.get(c);
			for(Character pc : successors) {
				degree.put(pc, degree.get(pc) - 1);
				if(degree.get(pc) == 0) {
					queue.add(pc);
				}
			}
		}

		// could not find a way to order all characters in the dictionary
		if(result.toString().length() != degree.size()) {
			return "";
		}
		return result.toString();
	}

	private void buildGraph(String[] words, Map<Character, Set<Character>> charPrecedenceGraph,
							Map<Character, Integer> degree) {

		for(String w : words) {
			for(char c : w.toCharArray()) {
				degree.put(c, 0);
				charPrecedenceGraph.putIfAbsent(c, new HashSet<>());
			}
		}

		// Build graph: find char diff between curr row and next row => build graph edge and increase inDegree relationship
		// always compare same index on: words[i] -> words[i+1]
		// if c1 != c2, build graph and inDegree map and break: later index does not have any more relationship.
		for(int i = 0; i < words.length - 1; ++i) {
			// compare current and next
			String cur = words[i];
			String next = words[i + 1];

			int compareUntil = Math.min(cur.length(), next.length());
			for(int j = 0; j < compareUntil; ++j) {
				char curChar = cur.charAt(j);
				char nextChar = next.charAt(j);
				if(curChar != nextChar) {
					if(!charPrecedenceGraph.get(curChar).contains(nextChar)) {

						charPrecedenceGraph.get(curChar).add(nextChar);
						degree.put(nextChar, degree.get(nextChar) + 1);

					}
					// note we always break as soon as we find characters don't match
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		String[] test = {"za","zb","ca","cb"};

		String result = new AlienDictionary().alienOrder(test);
		System.out.println(result); // abzc

		String[] test1 = {"wrt","wrf","er","ett","rftt","te"};
		String result1 = new AlienDictionary().alienOrder(test1);
		System.out.println(result1); // wertf

	}

}
