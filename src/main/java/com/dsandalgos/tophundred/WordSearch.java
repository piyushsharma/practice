package com.dsandalgos.tophundred;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 *
 * Example:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 */

public class WordSearch {


    public boolean exist(char[][] board, String word) {
        if(word == null || word.length() == 0) {
            return true;
        }

        for(int i = 0; i < board.length; ++i) {
            for(int j = 0; j < board[0].length; ++j) {

                if(word.charAt(0) == board[i][j]) {
                    boolean[][] visited = new boolean[board.length][board[0].length];
                    visited[i][j] = true;
                    if(dfs(board, i, j, word, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, int i, int j, String word, int wordIndex) {

        if (wordIndex == word.length()) return true;

        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) return false;

        if (board[i][j] != word.charAt(wordIndex)) return false;

        char temp = board[i][j];
        board[i][j] = ' ';

        boolean match = dfs(board, i - 1, j, word, wordIndex + 1)
                || dfs(board, i + 1, j, word, wordIndex + 1)
                || dfs(board, i, j - 1, word, wordIndex + 1)
                || dfs(board, i, j + 1, word, wordIndex + 1);

        board[i][j] = temp;
        return match;
    }


    public List<String> findWords(char[][] board, String[] words) {

        if(words == null || words.length == 0) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        for(String w : words) {
            if(dfs(w, board)) {
                result.add(w);
            }
        }
        return result;
    }


    private boolean dfs(String word, char[][] board) {

        int m = board.length;
        int n = board[0].length;
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {

                if(word.charAt(0) == board[i][j]) {

                    if(dfs(i, j, board, word, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, char[][] board, String word, int wordIndex) {

        if (wordIndex == word.length()) return true;

        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) return false;

        if (board[i][j] != word.charAt(wordIndex)) return false;

        char temp = board[i][j];
        board[i][j] = ' ';

        boolean match = dfs(i - 1, j, board, word, wordIndex + 1)
                || dfs(i + 1, j, board, word, wordIndex + 1)
                || dfs(i, j - 1, board, word, wordIndex + 1)
                || dfs(i, j + 1, board, word, wordIndex + 1);

        board[i][j] = temp;
        return match;
    }

    public static void main(String[] args) {
//        char[][] input = new char[][] {
//                {'A','B','C','E'},
//                {'S','F','C','S'},
//                {'A','D','E','E'}
//        };
//
//        System.out.println(new WordSearch().exist(input, "ABCCED"));


        char[][] input = new char[][] {
                {'a', 'b'},
                {'a', 'a'}
        };

        List<String> result = new WordSearch().findWords(input, new String[]{"aaba"});
        for(String s : result) {
            System.out.println(s);
        }

    }

}
