package com.game.connect4;

public class GameService {
    private final GameBoard board;
    private final Player[] players;
    private int currentPlayerIndex;
    private boolean gameOver;
    private Player winner;

    public GameService(int rows, int columns, Player player1, Player player2) {
        this.board = new GameBoard(rows, columns);
        this.players = new Player[]{player1, player2};
        this.currentPlayerIndex = 0;
        this.gameOver = false;
        this.winner = null;
    }

    public boolean makeMove(int column) {
        if (gameOver || column < 0 || column >= board.getColumns() || board.isColumnFull(column)) {
            return false;
        }

        Player currentPlayer = players[currentPlayerIndex];
        if (board.dropDisc(column, currentPlayer.getSymbol())) {
            if (board.isWin(column)) {
                gameOver = true;
                winner = currentPlayer;
            } else if (board.isFull()) {
                gameOver = true;
            } else {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
            }
            return true;
        }
        return false;
    }

    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Player getWinner() {
        return winner;
    }

    public GameBoard getBoard() {
        return board;
    }

    public String getGameStatus() {
        if (!gameOver) {
            return "Game in progress. Current player: " + getCurrentPlayer();
        }
        if (winner != null) {
            return "Game over. Winner: " + winner;
        }
        return "Game over. It's a draw!";
    }
} 