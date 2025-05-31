package com.dsandalgos.dp;

/**
 * Created by Piyush Sharma
 */

/*

Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "a a b c c",
s2 = "d b b c a",

When s3 = "a a d b b c b c a c", return true.
When s3 = "a a d b b b a c c c", return false.

*/

public class InterleavingString {

    // DP
    public boolean isInterleave(String s1, String s2, String s3) {

        int[][] cache = new int[s1.length() + 1][s2.length() + 1];
        return isInterleaveDP(cache, s1, s2, s3, 0, 0, 0);
    }


    public boolean isInterleaveDP(int[][] cache, String s1, String s2, String s3, int iOne, int iTwo, int iThree) {
        if(s1.length() + s2.length() != s3.length()) return false;

        if(iThree >= s3.length()) return true;
        if(iOne >= s1.length() && iTwo >= s2.length()) return false;

        if(iOne < s1.length() && s1.charAt(iOne) != s3.charAt(iThree) && iTwo < s2.length() && s2.charAt(iTwo) !=
                s3.charAt(iThree))  return false;
        
        boolean one = false;
        if(iOne < s1.length() && s1.charAt(iOne) == s3.charAt(iThree)) {
            // check if we have future strings stored, then we can assume that this will work
            if(cache[iOne + 1][iTwo] == 1) {
                one = true;
            } else if (cache[iOne][iTwo] == 0) {
                one = isInterleaveDP(cache, s1, s2, s3, iOne + 1, iTwo, iThree + 1);
                cache[iOne + 1][iTwo] = one ? 1 : -1;
            }
        }

        boolean two = false;
        if(iTwo < s2.length() && s2.charAt(iTwo) == s3.charAt(iThree)) {
            // is this a valid solution
            if(cache[iOne][iTwo + 1] == 1) {
                two = true;
            } else if (cache[iOne][iTwo] == 0){
                two = isInterleaveDP(cache, s1, s2, s3, iOne, iTwo + 1, iThree + 1);
                cache[iOne][iTwo + 1] = two ? 1 : -1;
            }
        }

        return one || two;
    }


    public boolean isInterleaveBackTracking(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()) return false;
        return isInterleave(s1.toCharArray(), s2.toCharArray(), s3.toCharArray(), 0, 0, 0);
    }


    public boolean isInterleave(char[] s1, char[] s2, char[] s3, int iOne, int iTwo, int iThree) {

        if(iThree >= s3.length) return true;

        if(iOne >= s1.length && iTwo >= s2.length) return false;

        if(iOne < s1.length && s1[iOne] != s3[iThree] && iTwo < s2.length && s2[iTwo] != s3[iThree]) return false;

        boolean one = false;
        if(iOne < s1.length && s1[iOne] == s3[iThree]) {
            // is this a valid solution
            one = isInterleave(s1, s2, s3, iOne+1, iTwo, iThree+1);
        }

        boolean two = false;
        if(iTwo < s2.length && s2[iTwo] == s3[iThree]) {
            // is this a valid solution
            two = isInterleave(s1, s2, s3, iOne, iTwo+1, iThree+1);
        }

        return one || two;
    }


    public boolean isInterleaveDPNew(String s1, String s2, String s3) {

        boolean[][] memo = new boolean[s1.length()][s2.length()];

        return isInterleaveDPNew(s1, 0, s2, 0, s3, 0, memo);

    }


    public boolean isInterleaveDPNew(String s1, int s1Index, String s2, int s2Index,
                                     String s3, int s3Index, boolean[][] memo) {

        if(s1Index >= s1.length()) {
            return s2.substring(s2Index).equals(s3.substring(s3Index));
        }

        if(s2Index >= s2.length()) {
            return s1.substring(s1Index).equals(s3.substring(s3Index));
        }

        if(memo[s1Index][s2Index]) {
            return true;
        }

        boolean matchWithFirst = s1.charAt(s1Index) == s3.charAt(s3Index) && isInterleaveDPNew(
                s1, s1Index + 1, s2, s2Index, s3, s3Index + 1, memo);

        boolean matchWithSecond = s2.charAt(s2Index) == s3.charAt(s3Index) && isInterleaveDPNew(
                s1, s1Index, s2, s2Index + 1, s3, s3Index + 1, memo);

        memo[s1Index][s2Index] = matchWithFirst || matchWithSecond;
        return matchWithFirst || matchWithSecond;
    }



    public boolean isInterleaveRecursive(String s1, String s2, String s3) {

        return isInterleaveR("", s1, 0, s2, 0, s3);

    }


    public boolean isInterleaveR(String curStr, String s1, int s1Index,
                                 String s2, int s2Index, String s3) {

        if(curStr.equals(s3) && s1Index == s1.length() && s2Index == s2.length()) {
            return true;
        }

        if(s1Index >= s1.length()) {
            return (curStr + s2.substring(s2Index)).equals(s3);
        }

        if(s2Index >= s2.length()) {
            return (curStr + s1.substring(s1Index)).equals(s3);
        }

        boolean matchWithFirst = isInterleaveR(curStr + s1.charAt(s1Index),
                s1, s1Index + 1, s2, s2Index, s3);

        boolean matchWithSecond = isInterleaveR(curStr + s2.charAt(s2Index),
                s1, s1Index, s2, s2Index + 1, s3);

        return matchWithFirst || matchWithSecond;
    }





    public static void main(String[] args) {
        InterleavingString i = new InterleavingString();
        System.out.println(i.isInterleave("aabcc", "dbbca", "aadbbcbcac"));

        System.out.println(i.isInterleave("aabcc", "dbbca", "aadbbbaccc"));
    }







}