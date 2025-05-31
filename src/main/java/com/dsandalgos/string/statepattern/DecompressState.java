package com.dsandalgos.string.statepattern;

/**
 * Created by Piyush Sharma
 */

public class DecompressState implements State {


    @Override
    public int decompress(Context context) {

        int index = context.getBegin();
        String s = context.getStr();
        StringBuilder sb = context.getSb();

        int repeatTimes = 0;
        while(index < s.length() && Character.isDigit(s.charAt(index))) {
            repeatTimes = repeatTimes * 10 + (s.charAt(index) - '0');
            ++index;
        }

        ++index; // skip '['
        StringBuilder repeatedString = new StringBuilder();
        while(index < s.length() && s.charAt(index) != ']') {
            repeatedString.append(s.charAt(index));
            ++index;
        }

        while(repeatTimes > 0) {
            sb.append(repeatedString.toString());
            --repeatTimes;
        }

        return index;
    }
}
