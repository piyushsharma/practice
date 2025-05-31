package com.dsandalgos.string;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 */

public class ZigzagConversion {

    // For details of the algorithm, just in case:
    // http://www.geeksforgeeks.org/print-concatenation-of-zig-zag-string-form-in-n-rows/
    public String convert(String s, int numRows) {
        int len = s.length();
        if(numRows <= 1 || len <= numRows) {
            return s;
        }

        StringBuilder[] outputArr = new StringBuilder[numRows];
        for (int i = 0; i < outputArr.length; ++i) {
            outputArr[i] = new StringBuilder();
        }

        boolean directionDown = true;  // lets say true for down and false when moving up
        int rowIndex = 0;

        for(int i = 0; i < len; ++i) {

            outputArr[rowIndex].append(s.charAt(i));
            if(rowIndex == 0) {
                directionDown = true;
            }
            if (rowIndex == numRows - 1) {
                directionDown = false;
            }

            if(directionDown) {
                ++rowIndex;
            } else {
                --rowIndex;
            }
        }

        StringBuilder output = new StringBuilder();
        for (StringBuilder sb : outputArr) {
            output.append(sb);
        }

        return output.toString();
    }


    public String convertA(String s, int numRows) {

        int length = s.length();
        if(numRows <= 1 || numRows >= length) {
            return s;
        }

        // subtract first and last rows
        int numMiddleRows = numRows - 2;

        StringBuilder sb = new StringBuilder();
        // si = start index for each row
        for(int si = 0; si < numRows; ++si) {
            // first and last rows
            if (si == 0 || si == numRows - 1) {
                // first and last rows will not have any middle characters
                int j = si;
                while (j < length) {
                    sb.append(s.charAt(j));
                    j += numRows + numMiddleRows;
                }
            } else {

                int j = si;
                // this is to calculate the next characters to be added to the result
                // first time add firstPass, second time add secondPass as the difference
                int firstPass = numRows + numMiddleRows - 2 * si;
                int secondPass = 2 * si;

                boolean flag = true;
                while (j < length) {
                    sb.append(s.charAt(j));
                    if (flag) {
                        j += firstPass;
                    } else {
                        j += secondPass;
                    }
                    flag = !flag;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new ZigzagConversion().convert("PAYPALISHIRING", 3));
        System.out.println(new ZigzagConversion().convert("PAYPALISHIRING", 4));
        System.out.println(new ZigzagConversion().convert("PAYPALISHIRING", 5));
    }


}
