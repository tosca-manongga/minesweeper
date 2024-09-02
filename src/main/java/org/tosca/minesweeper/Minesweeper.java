package org.tosca.minesweeper;

import java.util.Arrays;
import java.util.Random;

public class Minesweeper {
    public static final int MAXIMUM_SIZE = 10;
    public static final int MAXIMUM_PERCENTAGE_OF_MINES = 35;
    public static final int EMPTY = 0;
    public static final int MINE = 9;

    private final int size;
    private final int numberOfMines;

    private final boolean[][] visibleCells;
    private final int[][] hiddenCells;

    private boolean isGameLost = false;
    private boolean isGameWon = false;

    private int numberOfNonMineCells;
    private int numberOfNonVisibleCells;

    public Minesweeper(int size, int numberOfMines) {
        size = Math.min(size, Minesweeper.MAXIMUM_SIZE);

        this.size = size;

        int maximumNumberOfAllowableMines = size * size * Minesweeper.MAXIMUM_PERCENTAGE_OF_MINES / 100;

        numberOfMines = Math.min(numberOfMines, maximumNumberOfAllowableMines);

        this.numberOfMines = numberOfMines;

        this.numberOfNonMineCells = size * size - numberOfMines;
        this.numberOfNonVisibleCells = size * size;

        visibleCells = new boolean[size][size];
        hiddenCells = new int[size][size];

        placeMines(numberOfMines);
        computeAdjacentMines();
    }

    public int getSize() {
        return this.size;
    }

    public int getNumberOfMines() {
        return this.numberOfMines;
    }

    public int getNumberOfNonVisibleCells() {
        return this.numberOfNonVisibleCells;
    }

    public boolean[][] getVisibleCells() {
        boolean[][] visibleCellsClone = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            visibleCellsClone[i] = Arrays.copyOf(visibleCells[i], size);
        }

        return visibleCellsClone;
    }

    public int[][] getHiddenCells() {
        int[][] hiddenCellsClone = new int[size][size];

        for (int i = 0; i < size; i++) {
            hiddenCellsClone[i] = Arrays.copyOf(hiddenCells[i], size);
        }

        return hiddenCellsClone;
    }

    private void placeMines(int numberOfMines) {
        Random r = new Random();

        while (numberOfMines > 0) {
            int row = r.nextInt(size);
            int column = r.nextInt(size);

            if (hiddenCells[row][column] == Minesweeper.EMPTY) {
                hiddenCells[row][column] = Minesweeper.MINE;
                numberOfMines--;
            }
        }
    }

    private void computeAdjacentMines() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int numberOfAdjacentMines = 0;

                if (!isMineCell(i, j)) {
                    // Check north
                    if (isValidCell(i - 1, j) && isMineCell(i - 1, j)) {
                        numberOfAdjacentMines++;
                    }
                    // Check northeast
                    if (isValidCell(i - 1, j + 1) && isMineCell(i - 1, j + 1)) {
                        numberOfAdjacentMines++;
                    }
                    // Check east
                    if (isValidCell(i, j + 1) && isMineCell(i, j + 1)) {
                        numberOfAdjacentMines++;
                    }
                    // Check southeast
                    if (isValidCell(i + 1, j + 1) && isMineCell(i + 1, j + 1)) {
                        numberOfAdjacentMines++;
                    }
                    // Check south
                    if (isValidCell(i + 1, j) && isMineCell(i + 1, j)) {
                        numberOfAdjacentMines++;
                    }
                    // Check southwest
                    if (isValidCell(i + 1, j - 1) && isMineCell(i + 1, j - 1)) {
                        numberOfAdjacentMines++;
                    }
                    // Check west
                    if (isValidCell(i, j - 1) && isMineCell(i, j - 1)) {
                        numberOfAdjacentMines++;
                    }
                    // Check northwest
                    if (isValidCell(i - 1, j - 1) && isMineCell(i - 1, j - 1)) {
                        numberOfAdjacentMines++;
                    }

                    hiddenCells[i][j] = numberOfAdjacentMines;
                }
            }
        }
    }

    private boolean isValidCell(int row, int column) {
        return 0 <= row && row < size && 0 <= column && column < size;
    }

    private boolean isMineCell(int row, int column) {
        return hiddenCells[row][column] == Minesweeper.MINE;
    }

    public boolean selectCell(int row, int column) {
        if (isValidCell(row, column)) {
            if (visibleCells[row][column]) {
                return false;
            } else {
                if (hiddenCells[row][column] == Minesweeper.MINE) {
                    visibleCells[row][column] = true;
                    numberOfNonVisibleCells--;
                    isGameLost = true;
                    return false;
                } else {
                    revealCell(row, column);
                }

                return true;
            }
        } else {
            return false;
        }
    }

    private void revealCell(int row, int column) {
        if (isValidCell(row, column) && !visibleCells[row][column]) {
            visibleCells[row][column] = true;
            numberOfNonVisibleCells--;
            numberOfNonMineCells--;

            if (numberOfNonMineCells == 0) {
                isGameWon = true;
                return;
            }

            if (hiddenCells[row][column] == Minesweeper.EMPTY) {
                // Check north
                revealCell(row - 1, column);
                // Check northeast
                revealCell(row - 1, column + 1);
                // Check east
                revealCell(row, column + 1);
                // Check southeast
                revealCell(row + 1, column + 1);
                // Check south
                revealCell(row + 1, column);
                // Check southwest
                revealCell(row + 1, column - 1);
                // Check west
                revealCell(row, column - 1);
                // Check northwest
                revealCell(row - 1, column - 1);
            }
        }
    }

    public boolean isGameLost() {
        return isGameLost;
    }

    public boolean isGameWon() {
        return isGameWon;
    }
}
