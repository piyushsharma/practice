package com.game.connect4;

public class AIPlayer extends Player {
    public AIPlayer(String name, char symbol) {
        super(name, symbol);
    }

    public int getNextMove(GameBoard board) {
        // Simple AI: Choose the first available column
        for (int i = 0; i < board.getColumns(); i++) {
            if (!board.isColumnFull(i)) {
                return i;
            }
        }
        return -1; // No valid moves
    }

    // This method can be overridden to implement different AI strategies
    protected int evaluateMove(GameBoard board, int column) {
        // Default implementation: random evaluation
        return (int) (Math.random() * 100);
    }
} 