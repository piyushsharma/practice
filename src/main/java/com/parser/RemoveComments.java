package com.parser;

/**
 * Created by Piyush Sharma
 */

/*
Remove comments from C code
 */

public class RemoveComments {

    // works only for valid code (as in comment block started must end, etc)
    public String removeComments(String in) {

        StringBuilder sb = new StringBuilder();
        int i = 0;

        while(i < in.length()) {

            // from the index where we see the start of a double quote,
            // append everything until we encounter the ending quote character
            if(in.charAt(i) == '\"') {
                sb.append(in.charAt(i));
                i = appendToOutput(sb, in, i + 1, '\"');
            }

            // if we encounter the start of a comment,
            // skip characters until the end of the comment
            if(i + 1 < in.length()) {
                if(in.charAt(i) == '/' && in.charAt(i+1) == '*') {
                    i = skipUntilEndOfComment(in, i, "*/");

                } else if(in.charAt(i) == '/' && in.charAt(i+1) == '/') {
                    i = skipUntilEndOfComment(in, i, "\n");
                }
            }

            // append code to the output string
            if(i < in.length()) {
                sb.append(in.charAt(i));
            }
            ++i;
        }
        return sb.toString();
    }

    // append all characters to sb until endChar is seen (including endChar)
    // return the index after endChar
    public int appendToOutput(StringBuilder sb, String in, int i, char endChar) {
        while(i < in.length()) {
            sb.append(in.charAt(i));
            if(in.charAt(i) == endChar) {
                break;
            }
            ++i;
        }
        return i+1;
    }

    // increment index until end of comment string is found
    // return the index after the end of the comment string
    public int skipUntilEndOfComment(String in, int i, String end) {
        int j = 0;

        while(i < in.length()) {

            boolean foundEnd = false;
            if(in.charAt(i) == end.charAt(j)) {
                while(j < end.length() && in.charAt(i) == end.charAt(j)) {
                    ++i;
                    ++j;
                }
                if(j == end.length()) {
                    foundEnd = true;
                }
            }

            if(foundEnd) {
                break;
            }
            ++i;
        }

        return i;
    }


    public static void main(String[] args) {
        RemoveComments r = new RemoveComments();

        System.out.println(r.removeComments("/* test */ \n" +
                                            "// only comments \n"));
        System.out.println("====================================");

        System.out.println(r.removeComments(
                "/* \n" +
                "some multi line comments \n" +
                "*/ \n" +
                "some code \n" +
                "// more comments /* nested comments */ \n" +
                "more code1 \n" +
                "more code2 \n"));
        System.out.println("====================================");

        System.out.println(r.removeComments(
                "\"/* \n" +
                "comments inside quotes should not be removed \n" +
                "*/ \n" +
                "// more comments inside quotes \"\n"));
        System.out.println("====================================");

        System.out.println(r.removeComments(
                "/* \n" +
                "\" quotes inside comments should be removed \" \n" +
                "*/ \n" +
                "// \" \" quotes inside comments should be removed \"\n" +
                "some code \n"));
        System.out.println("====================================");
    }
}
