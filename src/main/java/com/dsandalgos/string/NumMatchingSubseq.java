package com.dsandalgos.string;

import java.util.HashMap;
import java.util.Map;

public class NumMatchingSubseq {
    public int numMatchingSubseq(String s, String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for(String w : words) {
            map.put(w, map.getOrDefault(w,0) + 1);
        }

        int ans = 0;
        char ch[] = s.toCharArray();

        for(String w : map.keySet()) {

            char temp[] = w.toCharArray();
            int i = 0;
            int j = 0;
            while(i < ch.length && j < temp.length) {
                if(ch[i] == temp[j]) {
                    i++;
                    j++;
                } else{
                    i++;
                }
            }

            if(j == temp.length){
                ans += map.get(w);
            }
        }

        return ans;
    }
}
