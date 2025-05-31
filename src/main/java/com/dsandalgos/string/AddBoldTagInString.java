package com.dsandalgos.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the
 * substrings in s that exist in dict.
 *
 * If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag.
 * Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
 *
 * Example 1:
 * Input:
 * s = "abcxyz123"
 * dict = ["abc","123"]
 * Output:
 * "<b>abc</b>xyz<b>123</b>"
 *
 * Example 2:
 * Input:
 * s = "aaabbcc"
 * dict = ["aaa","aab","bc"]
 * Output:
 * "<b>aaabbc</b>c"
 *
 * Note:
 * The given dict won't contain duplicates, and its length won't exceed 100.
 * All the strings in input have length in range [1, 1000].
 */


public class AddBoldTagInString {

    public String addBoldTag(String s, String[] dict) {
        List<Interval> substrings = new ArrayList();

        for(String w : dict) {
            int substringIndex = s.indexOf(w);
            // have to loop to find all occurrences of w in string s
            while(substringIndex != -1) {
                substrings.add(new Interval(substringIndex, substringIndex + w.length()));
                substringIndex += 1;
                substringIndex = s.indexOf(w, substringIndex);
            }
        }

        List<Interval> result = merge(substrings);
        StringBuilder sb = new StringBuilder();
        int prev = 0;
        for(Interval in : result) {
            sb.append(s, prev, in.start);
            sb.append("<b>");
            sb.append(s, in.start, in.end);
            sb.append("</b>");
            prev = in.end;
        }
        if(prev < s.length()) {
            sb.append(s.substring(prev));
        }
        return sb.toString();
    }

    private List<Interval> merge(List<Interval> substrings) {
        if(substrings.isEmpty()) {
            return substrings;
        }

        substrings.sort((a1, a2) -> {
            if(a1.start == a2.start) {
                return a1.end - a2.end;
            }
            return a1.start - a2.start;
        });

        List<Interval> result = new ArrayList<>();

        Interval prev = substrings.get(0);
        for(int i = 1; i < substrings.size(); ++i) {

            Interval cur = substrings.get(i);

            if(prev.end >= cur.start) {
                prev.end = Math.max(prev.end, cur.end);
            } else {
                result.add(prev);
                prev = cur;
            }
        }
        result.add(prev);

        return result;
    }

    class Interval {
        int start;
        int end;

        public Interval(int i, int j) {
            start = i;
            end = j;
        }
    }

    public static void main(String[] args) {
//        String y = new AddBoldTagInString().addBoldTag("abcxyz123", new String[]{"abc","123"});
//        String y = new AddBoldTagInString().addBoldTag("aaabbcc", new String[]{"aaa","aab","bc"});
        String y = new AddBoldTagInString().addBoldTag("aaabbcc", new String[]{"aaa","aab","bc","aaabbcc"});

        System.out.println(y);
    }
}
