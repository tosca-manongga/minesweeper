package minesweeper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveTest {
    @Test
    void testMove() {
        Move move = new Move(10, 5);

        assertEquals(10, move.getRow(), "Move should return exact row");
        assertEquals(5, move.getCol(), "Move should return exact column");
    }
}
