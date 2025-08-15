package minesweeper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CellTest {
    @Test
    void testCell() {
        Cell cell = new Cell();
        cell.reveal();
        cell.setMine(false);
        cell.setAdjacentMines(0);

        assertTrue(cell.isRevealed(), "Cell should be revealed");
        assertFalse(cell.isMine(), "Cell should be a mine");
        assertEquals(0, cell.getAdjacentMines(), "Cell should return exact number");
    }
}
