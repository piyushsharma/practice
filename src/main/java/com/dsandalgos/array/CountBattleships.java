package com.dsandalgos.array;

public class CountBattleships {

    public int countBattleships(char[][] board) {
        if (board == null) {
            throw new IllegalArgumentException("Input is null");
        }
        if (board.length == 0 || board[0].length == 0) {
            return 0;
        }

        int m = board.length;
        int n = board[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X'
                        && (i == m - 1 || board[i + 1][j] == '.')
                        && (j == n - 1 || board[i][j + 1] == '.')) {
                    count++;
                }
            }
        }

        return count;
    }


    public int countBattleshipsV2(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        int battleShips = 0;
        boolean[][] visited = new boolean[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(board[i][j] == 'X' && (!visited[i][j])){  //here count only new ships
                    dfs(board, i, j, visited);
                    battleShips += 1;   //count the ship
                }
            }
        }
        return battleShips;
    }
    private void dfs(char[][] board, int i, int j, boolean[][] visited){
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != 'X' || visited[i][j]){
            return;
        }

        visited[i][j] = true;   // Mark as visited

        dfs(board, i + 1, j, visited);  //Up
        dfs(board, i - 1, j, visited);  //Down
        dfs(board, i, j + 1, visited);  //Right
        dfs(board, i, j - 1, visited);  //Left

    }
}
