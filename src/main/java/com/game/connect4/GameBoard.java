package com.game.connect4;

import java.util.Arrays;

public class GameBoard {
    private final int rows;
    private final int columns;
    private final char[][] board;
    private final int[] columnHeights; // Track the height of each column
    private static final int WIN_LENGTH = 4;

    public GameBoard(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.board = new char[rows][columns];
        this.columnHeights = new int[columns];
        // Initialize board with empty spaces
        for (char[] row : board) {
            Arrays.fill(row, ' ');
        }
    }

    public boolean dropDisc(int column, char symbol) {
        if (column < 0 || column >= columns || columnHeights[column] >= rows) {
            return false; // Invalid move
        }

        int row = rows - 1 - columnHeights[column];
        board[row][column] = symbol;
        columnHeights[column]++;
        return true;
    }

    public boolean isWin(int lastColumn) {
        if (lastColumn < 0 || lastColumn >= columns) {
            return false;
        }

        int lastRow = rows - columnHeights[lastColumn];
        char symbol = board[lastRow][lastColumn];

        // Check horizontal
        if (checkHorizontal(lastRow, lastColumn, symbol)) return true;

        // Check vertical
        if (checkVertical(lastRow, lastColumn, symbol)) return true;

        // Check diagonal (both directions)
        if (checkDiagonal(lastRow, lastColumn, symbol)) return true;

        return false;
    }

    private boolean checkHorizontal(int row, int col, char symbol) {
        int count = 1;
        // Check left
        for (int i = col - 1; i >= 0 && board[row][i] == symbol; i--) {
            count++;
        }
        // Check right
        for (int i = col + 1; i < columns && board[row][i] == symbol; i++) {
            count++;
        }
        return count >= WIN_LENGTH;
    }

    private boolean checkVertical(int row, int col, char symbol) {
        int count = 1;
        // Check up
        for (int i = row - 1; i >= 0 && board[i][col] == symbol; i--) {
            count++;
        }
        // Check down
        for (int i = row + 1; i < rows && board[i][col] == symbol; i++) {
            count++;
        }
        return count >= WIN_LENGTH;
    }

    private boolean checkDiagonal(int row, int col, char symbol) {
        // Check diagonal (top-left to bottom-right)
        int count = 1;
        for (int i = 1; row - i >= 0 && col - i >= 0 && board[row - i][col - i] == symbol; i++) {
            count++;
        }
        for (int i = 1; row + i < rows && col + i < columns && board[row + i][col + i] == symbol; i++) {
            count++;
        }
        if (count >= WIN_LENGTH) return true;

        // Check diagonal (top-right to bottom-left)
        count = 1;
        for (int i = 1; row - i >= 0 && col + i < columns && board[row - i][col + i] == symbol; i++) {
            count++;
        }
        for (int i = 1; row + i < rows && col - i >= 0 && board[row + i][col - i] == symbol; i++) {
            count++;
        }
        return count >= WIN_LENGTH;
    }

    public boolean isFull() {
        for (int height : columnHeights) {
            if (height < rows) {
                return false;
            }
        }
        return true;
    }

    public boolean isColumnFull(int column) {
        return columnHeights[column] >= rows;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public char[][] getBoard() {
        return board;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Add column numbers
        sb.append("  ");
        for (int i = 0; i < columns; i++) {
            sb.append(i).append(" ");
        }
        sb.append("\n");

        // Add board
        for (int i = 0; i < rows; i++) {
            sb.append("| ");
            for (int j = 0; j < columns; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("|\n");
        }
        // Add bottom border
        sb.append("+");
        for (int i = 0; i < columns * 2 + 1; i++) {
            sb.append("-");
        }
        sb.append("+");
        return sb.toString();
    }
} 