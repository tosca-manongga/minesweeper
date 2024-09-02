package org.tosca.minesweeper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

class MinesweeperTest {
    @Test
    void testSize() {
        // Expected value is 5 since the specified size does not exceed maximum allowable size of 10
        Minesweeper minesweeper = new Minesweeper(5, 10);
        Assertions.assertEquals(5, minesweeper.getSize());
        Assertions.assertEquals(5, minesweeper.getVisibleCells()[0].length);
        Assertions.assertEquals(5, minesweeper.getHiddenCells()[0].length);

        // Expected value is 10 since the specified size does not exceed maximum allowable size of 10
        minesweeper = new Minesweeper(Minesweeper.MAXIMUM_SIZE, 10);
        Assertions.assertEquals(Minesweeper.MAXIMUM_SIZE, minesweeper.getSize());
        Assertions.assertEquals(Minesweeper.MAXIMUM_SIZE, minesweeper.getVisibleCells()[0].length);
        Assertions.assertEquals(Minesweeper.MAXIMUM_SIZE, minesweeper.getHiddenCells()[0].length);

        // Expected value is 10 since the specified size exceeds maximum allowable size of 10
        minesweeper = new Minesweeper(15, 10);
        Assertions.assertEquals(Minesweeper.MAXIMUM_SIZE, minesweeper.getSize());
        Assertions.assertEquals(Minesweeper.MAXIMUM_SIZE, minesweeper.getVisibleCells()[0].length);
        Assertions.assertEquals(Minesweeper.MAXIMUM_SIZE, minesweeper.getHiddenCells()[0].length);
    }

    @Test
    void testMines() {
        // Expected value is 5 since the specified number of mines is less than 35% of total number of cells (25) in the grid
        Minesweeper minesweeper = new Minesweeper(5, 5);
        Assertions.assertEquals(5, minesweeper.getNumberOfMines());
        Assertions.assertEquals(5, Arrays.stream(minesweeper.getHiddenCells())
                .flatMapToInt(Arrays::stream)
                .filter(i -> i == Minesweeper.MINE)
                .count());

        // Expected value is 8 since the specified number of mines exceeds 35% of total number of cells (25) in the grid
        minesweeper = new Minesweeper(5, 10);
        Assertions.assertEquals(8, minesweeper.getNumberOfMines());
        Assertions.assertEquals(8, Arrays.stream(minesweeper.getHiddenCells())
                .flatMapToInt(Arrays::stream)
                .filter(i -> i == Minesweeper.MINE)
                .count());

        // Expected value is 10 since the specified number of mines is less than 35% of total number of cells (100) in the grid
        minesweeper = new Minesweeper(Minesweeper.MAXIMUM_SIZE, 10);
        Assertions.assertEquals(10, minesweeper.getNumberOfMines());
        Assertions.assertEquals(10, Arrays.stream(minesweeper.getHiddenCells())
                .flatMapToInt(Arrays::stream)
                .filter(i -> i == Minesweeper.MINE)
                .count());

        // Expected value is 35 since the specified number of mines exceeds 35% of total number of cells (100) in the grid
        minesweeper = new Minesweeper(Minesweeper.MAXIMUM_SIZE, 50);
        Assertions.assertEquals(35, minesweeper.getNumberOfMines());
        Assertions.assertEquals(35, Arrays.stream(minesweeper.getHiddenCells())
                .flatMapToInt(Arrays::stream)
                .filter(i -> i == Minesweeper.MINE)
                .count());
    }

    @Test
    void testSelectingMineCell() {
        Minesweeper minesweeper = new Minesweeper(10, 10);

        int numberOfNonVisibleCellsBefore = minesweeper.getNumberOfNonVisibleCells();

        // Search for a cell containing a mine and select it
        boolean foundCell = false;

        for (int i = 0; i < minesweeper.getSize(); i++) {
            for (int j = 0; j < minesweeper.getSize(); j++) {
                if (minesweeper.getHiddenCells()[i][j] == Minesweeper.MINE) {
                    minesweeper.selectCell(i, j);
                    foundCell = true;
                    break;
                }
            }

            if (foundCell) {
                break;
            }
        }

        Assertions.assertEquals(numberOfNonVisibleCellsBefore - 1, minesweeper.getNumberOfNonVisibleCells());
        Assertions.assertTrue(minesweeper.isGameLost());
        Assertions.assertFalse(minesweeper.isGameWon());
    }

    @Test
    void testSelectingCellWithAdjacentMines() {
        Minesweeper minesweeper = new Minesweeper(10, 10);

        int numberOfNonVisibleCellsBefore = minesweeper.getNumberOfNonVisibleCells();

        // Search for a cell with adjacent mine and select it
        boolean foundCell = false;

        for (int i = 0; i < minesweeper.getSize(); i++) {
            for (int j = 0; j < minesweeper.getSize(); j++) {
                if (minesweeper.getHiddenCells()[i][j] != Minesweeper.EMPTY && minesweeper.getHiddenCells()[i][j] != Minesweeper.MINE) {
                    minesweeper.selectCell(i, j);
                    foundCell = true;
                    break;
                }
            }

            if (foundCell) {
                break;
            }
        }

        Assertions.assertEquals(numberOfNonVisibleCellsBefore - 1, minesweeper.getNumberOfNonVisibleCells());
        Assertions.assertFalse(minesweeper.isGameLost());
        Assertions.assertFalse(minesweeper.isGameWon());
    }

    @Test
    void testSelectingCellWithNoAdjacentMines() {
        // Test case where mines are present
        // Game will not be over after selecting the cell
        Minesweeper minesweeper = new Minesweeper(10, 10);

        int numberOfNonVisibleCellsBefore = minesweeper.getNumberOfNonVisibleCells();

        // Search for a cell with no adjacent mines and select it
        boolean foundCell = false;

        for (int i = 0; i < minesweeper.getSize(); i++) {
            for (int j = 0; j < minesweeper.getSize(); j++) {
                if (minesweeper.getHiddenCells()[i][j] == Minesweeper.EMPTY) {
                    minesweeper.selectCell(i, j);
                    foundCell = true;
                    break;
                }
            }

            if (foundCell) {
                break;
            }
        }

        Assertions.assertTrue(minesweeper.getNumberOfNonVisibleCells() < numberOfNonVisibleCellsBefore);
        Assertions.assertFalse(minesweeper.isGameLost());
        Assertions.assertFalse(minesweeper.isGameWon());

        // Test case where no mines are present
        // Game will be over once selecting the cell
        minesweeper = new Minesweeper(10, 0);

        numberOfNonVisibleCellsBefore = minesweeper.getNumberOfNonVisibleCells();

        // Search for a cell with no adjacent mines and select it
        foundCell = false;

        for (int i = 0; i < minesweeper.getSize(); i++) {
            for (int j = 0; j < minesweeper.getSize(); j++) {
                if (minesweeper.getHiddenCells()[i][j] == Minesweeper.EMPTY) {
                    minesweeper.selectCell(i, j);
                    foundCell = true;
                    break;
                }
            }

            if (foundCell) {
                break;
            }
        }

        Assertions.assertTrue(minesweeper.getNumberOfNonVisibleCells() < numberOfNonVisibleCellsBefore);
        Assertions.assertFalse(minesweeper.isGameLost());
        Assertions.assertTrue(minesweeper.isGameWon());
    }
}
