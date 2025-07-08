package com.dsandalgos.array;

import java.util.*;

public class FindLREArray {


    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        int p1 = 0, p2=0;
        List<List<Integer>> res = new ArrayList<>();

        while(p1 < encoded1.length) {
            int len = Math.min(encoded1[p1][1], encoded2[p2][1]);
            int mult = encoded1[p1][0] * encoded2[p2][0];

            //to handle cases like [[1,3],[2,3]] * [[6,3],[3,3]] --> [[6,6]]
            if(res.size() > 0 && res.get(res.size()-1).get(0) == mult) {
                //update previous mult in res instead of adding a new one
                List<Integer> last = res.get(res.size() - 1);
                last.set(1, last.get(1) + len);
            } else {
                res.add(Arrays.asList(mult, len));
            }

            encoded1[p1][1] -= len;
            encoded2[p2][1] -= len;
            if(encoded1[p1][1] == 0) p1++;
            if(encoded2[p2][1] == 0) p2++;
        }
        return res;
    }

    public List<List<Integer>> findRLEArrayV2(int[][] encoded1, int[][] encoded2) {
        List<Integer> one = expand(encoded1);
        List<Integer> two = expand(encoded2);

        List<Integer> three = new ArrayList<>();
        for(int i = 0; i < one.size(); ++i) {
            three.add(one.get(i) * two.get(i));
        }

        // Collections.sort(three);
        List<List<Integer>> result = new ArrayList<>();
        int i = 0;
        while(i < three.size()) {

            int cur = three.get(i);
            int count = 1;
            while(i + 1 < three.size() && three.get(i + 1) == cur) {
                ++count;
                ++i;
            }

            List<Integer> toadd = new ArrayList<>();
            toadd.add(cur);
            toadd.add(count);
            result.add(toadd);

            ++i;
        }

        return result;

    }

    List<Integer> expand(int[][] encoded) {
        List<Integer> one = new ArrayList<>();
        for(int[] t : encoded) {
            int times = t[1];
            while(times > 0) {
                one.add(t[0]);
                --times;
            }
        }
        return one;
    }
}
