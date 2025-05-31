package com.dsandalgos.string.statepattern;

/**
 * Created by Piyush Sharma
 */

/*
There is compressed string like this: 3[abc]4[ab]c
and we want to decompress it into form like this: abcabcabcababababc

*/

public class DecompressString {

    private String decompress(String s) {
        StringBuilder sb = new StringBuilder();

        int i = 0;
        int len = s.length();

        DecompressState d = new DecompressState();
        while(i < len) {

            if(Character.isDigit(s.charAt(i))) {
                Context context = new Context(s, i, sb);
                i = d.decompress(context);

            } else {

                sb.append(s.charAt(i));
            }
            ++i;
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        DecompressString d = new DecompressString();

        System.out.println(d.decompress("30[a]4[ab]c"));
    }


}
