package org.tosca;

import org.tosca.minesweeper.GridOutputHelper;
import org.tosca.minesweeper.Minesweeper;

import java.util.Scanner;

public class MinesweeperApp {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Welcome to Minesweeper!");
        System.out.println();

        Minesweeper minesweeper;

        do {
            System.out.print("Enter the size of the grid (maximum is 10 - 10x10): ");
            int size = s.nextInt();
            System.out.print("Enter the number of mines to place on the grid (maximum is 35% of the total squares): ");
            int numberOfMines = s.nextInt();
            System.out.println();

            minesweeper = new Minesweeper(size, numberOfMines);

            do {
                // Uncomment below for debugging purposes
//                GridOutputHelper.printVisibleCells(minesweeper.getVisibleCells(), minesweeper.getSize());
//                System.out.println();
//                GridOutputHelper.printHiddenCells(minesweeper.getHiddenCells(), minesweeper.getSize());
//                System.out.println();
//                GridOutputHelper.printGameBoard(minesweeper.getVisibleCells(), minesweeper.getHiddenCells(), minesweeper.getSize());
//                System.out.println();

                System.out.println("Select cell to reveal.");
                System.out.print("Row: ");
                int row = s.nextInt();
                System.out.print("Column: ");
                int column = s.nextInt();
                System.out.println();

                boolean result = minesweeper.selectCell(row, column);

                if (minesweeper.isGameWon() | minesweeper.isGameLost()) {
                    GridOutputHelper.printGameBoard(minesweeper.getVisibleCells(), minesweeper.getHiddenCells(), minesweeper.getSize());
                    System.out.println();

                    if (minesweeper.isGameLost()) {
                        System.out.println("You have hit a mine! You have lost the game!");
                    } else if (minesweeper.isGameWon()) {
                        System.out.println("Congratulations! You have won the game!");
                    }
                }
            } while (!minesweeper.isGameWon() && !minesweeper.isGameLost());

            System.out.print("Press any key to continue.");
            System.console().readPassword();
            System.out.println();
        } while (true);
    }
}
