package minesweeper;

public class GameInput {
    private final int size;
    private final int mines;

    public GameInput(int size, int mines) {
        this.size = size;
        this.mines = mines;
    }

    public int getSize() {
        return size;
    }

    public int getMines() {
        return mines;
    }
}
