package com.dsandalgos.string;

public class ValidWordAbbr {


    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0;
        int j = 0;
        int n = word.length();
        int m = abbr.length();

        while(i < n && j < m) {

            if(word.charAt(i) == abbr.charAt(j)) {
                ++i;
                ++j;
            } else {

                if(abbr.charAt(j) == '0' || !Character.isDigit(abbr.charAt(j))) {
                    return false;
                }

                int num = 0;
                while(j < m && Character.isDigit(abbr.charAt(j))) {
                    num = num * 10 + ((abbr.charAt(j)) - '0');
                    ++j;
                }

                i += num;
            }
        }

        return i == n && m == j;

    }
}
