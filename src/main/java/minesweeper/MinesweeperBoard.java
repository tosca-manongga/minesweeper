package minesweeper;

import java.util.Random;

public class MinesweeperBoard implements GameBoard {
    private Cell[][] board;
    private int rows;
    private int cols;
    private int mines;
    private int unrevealedSafeCells;
    private boolean gameOver;

    @Override
    public void initialize(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
        this.unrevealedSafeCells = rows * cols - mines;
        this.gameOver = false;
        this.board = new Cell[rows][cols];

        // Initialize cells
        initializeCells(rows, cols);

        // Place mines
        placeMines(rows, cols, mines);

        // Calculate adjacent mines
        calculateAdjacentMines(rows, cols);
    }

    private void initializeCells(int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    private void placeMines(int rows, int cols, int mines) {
        Random random = new Random();

        int minesPlaced = 0;

        while (minesPlaced < mines) {
            int row = random.nextInt(rows);
            int col = random.nextInt(cols);

            if (!board[row][col].isMine()) {
                board[row][col].setMine(true);
                minesPlaced++;
            }
        }
    }

    private void calculateAdjacentMines(int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!board[i][j].isMine()) {
                    board[i][j].setAdjacentMines(countAdjacentMines(i, j));
                }
            }
        }
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;

                if (isValidCell(newRow, newCol) && board[newRow][newCol].isMine()) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    @Override
    public boolean revealCell(int row, int col) {
        if (!isValidCell(row, col) || board[row][col].isRevealed() || gameOver) {
            return false;
        }

        board[row][col].reveal();

        if (board[row][col].isMine()) {
            gameOver = true;
            return false;
        }

        unrevealedSafeCells--;

        if (board[row][col].getAdjacentMines() == 0) {
            // Recursively reveal adjacent cells
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    revealCell(row + i, col + j);
                }
            }
        }

        return true;
    }

    @Override
    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    @Override
    public boolean isGameWon() {
        return unrevealedSafeCells == 0 && !gameOver;
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public void display() {
        System.out.println();
        System.out.println("Here is your minefield:");
        System.out.println();
        System.out.print("  ");

        for (int j = 0; j < cols; j++) {
            System.out.print(j + " ");
        }

        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print(i + " ");

            for (int j = 0; j < cols; j++) {
                if (board[i][j].isRevealed()) {
                    if (board[i][j].isMine()) {
                        System.out.print("M ");
                    } else {
                        System.out.print(board[i][j].getAdjacentMines() + " ");
                    }
                } else {
                    System.out.print("_ ");
                }
            }

            System.out.println();
        }
    }
}
