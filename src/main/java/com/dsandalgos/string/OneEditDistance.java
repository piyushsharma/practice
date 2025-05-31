package com.dsandalgos.string;

/**
 * Created by Piyush Sharma
 */

/*
Given two strings S and T, determine if they are both one edit distance apart.
 */


public class OneEditDistance {

    /*
    1) If difference between m an n is more than 1,  return false.
    2) Initialize count of edits as 0.
    3) Start traversing both strings from first character.
        a) If current characters don't match, then
            (i)   Increment count of edits
            (ii)  If count becomes more than 1, return false
            (iii) If length of one string is more, then only possible edit is to remove a character.
                  Therefore, move ahead in larger string.
            (iv)  If length is same, then only possible edit is to  change a character.
                  Therefore, move ahead in both strings.
        b) Else, move ahead in both strings.
     */

    public boolean isOneEditDistance(String s, String t) {
        int m = s.length();
        int n = t.length();

        if(n > m) return isOneEditDistance(t, s);

        if (Math.abs(m - n) > 1) return false;
        int count = 0; // count of edits
        int i = 0; int j = 0;

        while(i < m) {

            char charOfSmallerString = j < n ? t.charAt(j) : ' ';
            if (s.charAt(i) != charOfSmallerString) {
                ++count;

                if (count > 1) {
                    return false;
                } else if (m > n) {
                    ++i;
                } else if (n > m) {
                    ++j;
                } else {
                    ++i;
                    ++j;
                }
            } else {
                ++i;
                ++j;
            }
        }
        return count == 1;
    }


    /*
    For the case where m is equal to n, it becomes finding if there is exactly one modified character.
    Assume X represents the one-edit character.
    Now let’s assume m ≤ n. (If m > n we could just swap them).
    There are three one-edit distance operations that could be applied to S.
        i. Modify operation – Modify a character to X in S.
        ii. Insert operation – X was inserted before a character in S.
        iii. Append operation – X was appended at the end of S.
    */

    public boolean isOneEditDistanceV2(String s, String t) {
        int m = s.length();
        int n = t.length();

        if (m > n) return isOneEditDistance(t, s);

        if(n - m > 1) return false;

        int i = 0;
        int diff = n - m;  // will be 1 or 0
        while(i < m && s.charAt(i) == t.charAt(i)) ++i;

        // at this point we are at the character that is different or i == m

        // i.e. all m characters are same, so at most there will be another character in t which is not there in s
        if(i == m) return true;

        // i != m, so we are at the point where character in s and t differ
        // we ignore this character in both s and t, so all the remaining should match!
        if(diff == 0) ++i;

        // if diff is 1; we skip the character in t, all remaining characters should match the ones in s
        while(i < m && s.charAt(i) == t.charAt(i + diff)) ++i;

        return i == m;
    }


    public static void main(String[] args) {
        System.out.println(3 != 3);
    }

}
