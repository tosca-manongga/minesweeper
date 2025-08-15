package minesweeper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinesweeperBoardTest {
    private MinesweeperBoard board;

    @BeforeEach
    void setUp() {
        board = new MinesweeperBoard();
    }

    @Test
    void testInitializeBoard() {
        board.initialize(5, 5, 5);

        int mineCount = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board.getCell(i, j).isMine()) {
                    mineCount++;
                }
            }
        }

        assertEquals(5, mineCount, "Should place exact number of mines");
    }

    @Test
    void testRevealSafeCell() {
        board.initialize(3, 3, 1);

        // Find a safe cell
        int safeRow = 0, safeCol = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!board.getCell(i, j).isMine()) {
                    safeRow = i;
                    safeCol = j;
                    break;
                }
            }
        }

        assertTrue(board.revealCell(safeRow, safeCol), "Revealing safe cell should return true");
        assertTrue(board.getCell(safeRow, safeCol).isRevealed(), "Cell should be revealed");
    }

    @Test
    void testRevealMine() {
        board.initialize(3, 3, 1);

        // Find a mine
        int mineRow = 0, mineCol = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getCell(i, j).isMine()) {
                    mineRow = i;
                    mineCol = j;
                    break;
                }
            }
        }

        assertFalse(board.revealCell(mineRow, mineCol), "Revealing mine should return false");
        assertTrue(board.isGameOver(), "Game should be over after hitting a mine");
    }

    @Test
    void testWinCondition() {
        board.initialize(2, 2, 1);

        // Reveal all safe cells
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (!board.getCell(i, j).isMine()) {
                    board.revealCell(i, j);
                }
            }
        }

        assertTrue(board.isGameWon(), "Game should be won when all safe cells are revealed");
    }

    @Test
    void testInvalidCell() {
        board.initialize(3, 3, 1);

        assertFalse(board.revealCell(-1, 0), "Should not reveal invalid cell");
        assertFalse(board.revealCell(3, 0), "Should not reveal out-of-bounds cell");
    }
}
