package com.dsandalgos.string;

import java.util.ArrayList;
import java.util.List;

public class SmallestStringWithSwaps {

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
//        "dcab"
//         [[0,3],[1,2]]



        char[] str = s.toCharArray();

        boolean swapped = false;
        List<String> others = new ArrayList<>();
        for(List<Integer> pair : pairs) {

            int start = pair.get(0);
            int end = pair.get(1);

            if(str[start] > str[end]) {
                char temp = str[start];
                str[start] = str[end];
                str[end] = temp;

                others.add(new String(str));
                swapped = true;
            }

            for(String o : others) {
                smallestStringWithSwaps(o, pairs);
            }
        }

        return new String(str);
    }
}
