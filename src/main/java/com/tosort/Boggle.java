package com.tosort;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */
public class Boggle {

    public static Set<String> dictionary = new HashSet<String>();
//    public static String str = "";

    private static void findWords(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        String[] str = {""};
        for(int i = 0; i < grid.length; ++i) {
            for(int j = 0; j < grid[0].length; ++j) {
                findWordsUtil(grid, i, j, visited, str);
            }
        }
    }

    private static void findWordsUtil(char[][] grid, int i, int j, boolean[][] visited, String[] s) {

        visited[i][j] = true;
        s[0] = s[0] + grid[i][j];

        if(dictionary.contains(s[0])) {
            System.out.println(s[0]);
        }

        for(int row = i - 1; row <= i + 1 && row < grid.length; ++row) {
            for(int col = j - 1; col <= j + 1 && col < grid[0].length; ++col) {

                if(row >= 0 && col >= 0 && !visited[row][col])
                    findWordsUtil(grid, row, col, visited, s);
            }
        }

        s[0] = s[0].substring(0, s[0].length()-1);
        visited[i][j] = false;
    }

    public static void main(String[] args) {
        char[][] grid = {   {'c', 'f', 's', 't'},
                            {'g', 'h', 's', 'o'},
                            {'a', 'p', 'y', 'e'},
                            {'h', 'i', 'w', 'b'}
                        };
        dictionary.add("cgap");
        dictionary.add("fhpi");
        dictionary.add("toeb");
        dictionary.add("ystf");
        System.out.println("Following words of dictionary are present");
        findWords(grid);
    }
}
