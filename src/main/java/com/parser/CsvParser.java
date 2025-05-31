package com.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */
public class CsvParser {

    private static List<List<String>> parse(String s) {

        char[] input = s.toCharArray();
        int len = input.length;

        List<List<String>> result = new ArrayList<List<String>>();
        int i = 0;
        List<String> line = new ArrayList<String>();
        String word = "";

        while(i < len) {
            if(input[i] == '"') {
                word = "";
                i = getNextWordStartingUnderQuotes(input, len, i, word);

                word += input[i];
                if(i < len) ++i;
                while(input[i] != '\"') {
                    word += input[i];
                    if(i < len) ++i;
                    else break;
                }
                word += input[i];
                line.add(word);
                word = "";
                if(i < len) ++i;
                continue;
            }
            if(input[i] == ',') {
                line.add(word);
                word = "";
                ++i;
                continue;
            }
            if(input[i] == '\n') {
               if(word.length() > 0)
                    line.add(word);

               word = "";
               result.add(new ArrayList<String>(line));
               line = new ArrayList<String>();
               ++i;
               continue;
            }

            word += input[i];
            ++i;

        }
        return result;
    }

    private static int getNextWordStartingUnderQuotes(char[] input, int length, int i, String word) {
        word += input[i];
        ++i;
        while (i < length) {
            if(input[i] == '"' && (input[i+1] == ',' || input[i+1] == '\n')) {
                word += input[i];
                break;
            }
            word += input[i];
            ++i;
        }
        return i;
    }


    public static void main(String[] args) {
        String s1 = "hello,\"world,this\\\"testing\\\"\"\n";
//
//
//        char[] input = s1.toCharArray();
//        int i = 0;
//        int n = input.length;
//        while (i < n) {
//            System.out.println(input[i]);
//            ++i;
//        }

        List<List<String>> result = parse(s1);
        for(int i = 0; i < result.size(); ++i) {
            List<String> line = result.get(i);

            for(String s : line) {
                System.out.printf("%s ", s);
            }
            System.out.printf("\n +++++");

        }
    }
}
