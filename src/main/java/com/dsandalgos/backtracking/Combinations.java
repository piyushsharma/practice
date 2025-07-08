package com.dsandalgos.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();

        backtrack(new ArrayList<>(), 1, n, k, result);
        return result;
    }

    private void backtrack(List<Integer> cur, int num, int n, int k, List<List<Integer>> result) {
        if(cur.size() == k) {
            result.add(new ArrayList<>(cur));
            return;
        }

        for(int i = num; i <= n; ++i) {
            cur.add(i);
            backtrack(cur, i + 1, n, k, result);
            cur.remove(cur.size() - 1);
        }
    }
}
