package minesweeper;

import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {
    private final Scanner scanner;

    public ConsoleInputHandler() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public GameInput getGameInput() {
        System.out.println();
        System.out.print("Enter the size of the grid (e.g. 4 for a 4x4 grid): ");

        int size = scanner.nextInt();

        System.out.println();
        System.out.print("Enter the number of mines to place on the grid (maximum is 35% of the total squares): ");

        int mines = scanner.nextInt();

        return new GameInput(size, mines);
    }

    @Override
    public Move getMove() {
        System.out.println();
        System.out.print("Select a square to reveal (e.g. '0 3'): ");

        int row = scanner.nextInt();
        int col = scanner.nextInt();

        return new Move(row, col);
    }
}
