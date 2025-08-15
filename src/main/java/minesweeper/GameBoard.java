package minesweeper;

public interface GameBoard {
    void initialize(int rows, int cols, int mines);

    boolean revealCell(int row, int col);

    Cell getCell(int row, int col);

    boolean isGameWon();

    boolean isGameOver();

    void display();
}
