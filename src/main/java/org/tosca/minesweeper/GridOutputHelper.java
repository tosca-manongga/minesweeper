package org.tosca.minesweeper;

public class GridOutputHelper {
    public static void printVisibleCells(boolean[][] visibleCells, int size) {
        System.out.println("Visible Cells");
        System.out.println();

        System.out.print("  ");

        for (int i = 0; i < size; i++) {
            System.out.print(" " + i);
        }

        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print(i + " " + "[");

            for (int j = 0; j < size; j++) {
                System.out.print(visibleCells[i][j] ? 1 : 0);

                if (j != size - 1) {
                    System.out.print(" ");
                }
            }

            System.out.println("]");
        }
    }

    public static void printHiddenCells(int[][] hiddenCells, int size) {
        System.out.println("Hidden Cells");
        System.out.println();

        System.out.print("  ");

        for (int i = 0; i < size; i++) {
            System.out.print(" " + i);
        }

        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print(i + " " + "[");

            for (int j = 0; j < size; j++) {
                System.out.print(hiddenCells[i][j]);

                if (j != size - 1) {
                    System.out.print(" ");
                }
            }

            System.out.println("]");
        }
    }

    public static void printGameBoard(boolean[][] visibleCells, int[][] hiddenCells, int size) {
        System.out.println("Game Board");
        System.out.println();

        System.out.print("  ");

        for (int i = 0; i < size; i++) {
            System.out.print(" " + i);
        }

        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print(i + " " + "[");

            for (int j = 0; j < size; j++) {
                if (visibleCells[i][j]) {
                    String s;

                    if (hiddenCells[i][j] == Minesweeper.EMPTY) {
                        s = " ";
                    } else if (hiddenCells[i][j] == Minesweeper.MINE) {
                        s = "M";
                    } else {
                        s = Integer.toString(hiddenCells[i][j]);
                    }

                    System.out.print(s);
                } else {
                    System.out.print("_");
                }

                if (j != size - 1) {
                    System.out.print(" ");
                }
            }

            System.out.println("]");
        }
    }
}
