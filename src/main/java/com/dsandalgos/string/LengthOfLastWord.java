package com.dsandalgos.string;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */
public class LengthOfLastWord {

    public int lengthOfLastWord(String s) {
        if(s == null)
            return 0;

        String[] sp = s.split(" ");
        if(sp.length == 0) return 0;

        return sp[sp.length - 1].length();
    }
}
