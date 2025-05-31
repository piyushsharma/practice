package com.dsandalgos.misc;

/**
 * I'm making a search engine called MillionGazillion™.
 *
 * I wrote a crawler that visits web pages, stores a few keywords in a database,
 * and follows links to other web pages.
 * I noticed that my crawler was wasting a lot of time visiting the same pages over and over,
 * so I made a hash set, visited, where I'm storing URLs I've already visited.
 * Now the crawler only visits a URL if it hasn't already been visited.
 *
 * Thing is, the crawler is running on my old desktop computer in my parents' basement
 * (where I totally don't live anymore), and it keeps running out of memory because visited is getting so huge.
 *
 * How can I trim down the amount of space taken up by visited?
 *
 */

public class MillionGazillion {

	private int CHARACTER_SET = 256;
	private TrieNode root;
	private long totalUrls;

	public MillionGazillion() {
		root = new TrieNode(0);
		totalUrls = 0;
	}

	class TrieNode {
		int charValue;
		boolean isEnd;
		TrieNode[] children;

		public TrieNode(int charValue) {
			this.charValue = charValue;
			this.children = new TrieNode[CHARACTER_SET];
			for(int i = 0; i < CHARACTER_SET; ++i) {
				children[i] = null;
			}
			this.isEnd = false;
		}
	}


	/**
	 * Analysis
	 *
	 * Say we store each url separately.
	 * All possible 5 letter urls (with just a-z) = 26 * 26 * 26 * 26 * 26 = 26 ^ 5
	 * To store each url = 5 characters, so total = 5 * 26^5
	 *
	 * Similarly for 4 letter urls => 4 * 26 ^ 4
	 *
	 * If we add all => O(n * 26 ^ n) space
	 *
	 * With tries =>
	 * To store all 5 letter urls => first layer 26 (for each starting letter)
	 * second layer => 26 ^ 2 nodes as each of the first layer has 26 child nodes, ...
	 * ... 5th layer 26 ^ 5 nodes
	 *
	 * So the total becomes 26 ^ 1 + 26 ^ 2 + ... + 26 ^ 5 = O (26 ^ n) space
	 *
	 * we save a factor of "n" by using tries
	 */

	private void addUrl(String url) {

		TrieNode cur = root;
		for(int i = 0; i < url.length(); ++i) {

			int c = url.charAt(i);

			if(cur.children[c] == null) {
				cur.children[c] = new TrieNode(c);
			}
			cur = cur.children[c];
		}

		cur.isEnd = true;
		++totalUrls;
	}


	private boolean search(String url) {

		TrieNode cur = root;
		for(int i = 0; i < url.length(); ++i) {

			int c = url.charAt(i);

			if(cur.children[c] == null) {
				return false;
			}
			cur = cur.children[c];
		}

		return cur != null && cur.isEnd == true;
	}


	public static void main(String[] args) {
		MillionGazillion m = new MillionGazillion();

		m.addUrl("www.google.com");
		m.addUrl("www.google.com/maps");
		m.addUrl("www.google.com/about");

		System.out.println(m.search("www.google.com"));
		System.out.println(m.search("www.google.com/maps"));
		System.out.println(m.search("www.google.com/about"));

	}

}
