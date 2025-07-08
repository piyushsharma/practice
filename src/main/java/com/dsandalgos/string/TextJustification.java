package com.dsandalgos.string;


import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of strings words and a width maxWidth, format the text such that each line
 * has exactly maxWidth characters and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
 * Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 *
 * Extra spaces between words should be distributed as evenly as possible.
 * If the number of spaces on a line does not divide evenly between words,
 * the empty slots on the left will be assigned more spaces than the slots on the right.
 *
 * For the last line of text, it should be left-justified, and no extra space is inserted between words.
 *
 * Note:
 *
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 *
 *
 * Example 1:
 *
 * Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
 * Output:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * Example 2:
 *
 * Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
 * Output:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall     be", because the
 * last line must be left-justified instead of fully-justified.
 * Note that the second line is also left-justified because it contains only one word.
 * Example 3:
 *
 * Input: words = ["Science","is","what","we","understand","well","enough","to",
 * "explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
 * Output:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 */
public class TextJustification {

    public List<String> fullJustify(String[] words, int maxWidth) {

        List<String> answer = new ArrayList<>();

        int i = 0;
        while(i < words.length){
            List<String> line = getWords(i, words, maxWidth);
            i += line.size();

            String justified = justifyLine(line, maxWidth, i == words.length);
            answer.add(justified);
        }
        return answer;
    }

    public List<String> getWords(int index, String[] words, int maxWidth) {
        List<String> line = new ArrayList<>();
        int curLen = 0;
        while(index < words.length && curLen + words[index].length() <= maxWidth) {
            line.add(words[index]);
            curLen += words[index].length() + 1;
            ++index;
        }
        return line;
    }

    public String justifyLine(List<String> line, int maxWidth, boolean lastLine) {

        int size = line.size();
        int len = 0;
        for (String s : line) {
            len += s.length();
        }

        if(lastLine || size == 1) {
            String base = String.join(" ", line);
            int extraLeft = maxWidth - len - (size - 1);
            return base + " ".repeat(extraLeft);
        }
        int spacesBetweenWords = (maxWidth - len)/(size - 1);
        int spacesLeft = (maxWidth - len) % (size - 1);

        StringBuilder justified = new StringBuilder();
        justified.append(line.get(0));
        for(int i = 1; i < line.size(); ++i) {
            int cur = 0;
            while(cur < spacesBetweenWords) {
                justified.append(" ");
                ++cur;
            }
            if(spacesLeft > 0) {
                justified.append(" ");
                --spacesLeft;
            }
            justified.append(line.get(i));
        }

        while(spacesLeft > 0) {
            justified.append(" ");
            --spacesLeft;
        }

        return justified.toString();
    }



}