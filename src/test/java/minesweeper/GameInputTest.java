package minesweeper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameInputTest {
    @Test
    void setUp() {
        GameInput gameInput = new GameInput(10, 5);

        assertEquals(10, gameInput.getSize(), "GameInput should return exact size");
        assertEquals(5, gameInput.getMines(), "GameInput should return exact mines");
    }
}
