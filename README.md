This Minesweeper application was developed on MacOS Sonoma 14.6.1 (x86) using IntelliJ IDEA CE using the below software:
JDK 22
Maven
JUnit 5

Assumptions:
- Maximum size of the grid is set as 10 to simplify output formatting of the grid.
- Maximum number of mines is set to at most 35% of the number of cells in the grid.
- Cell selection input is achieved through two separated inputs, row and column, where the numbering starts from 0.
  e.g. 
  Row: 0
  Column: 0

Classes:
- MinesweeperApp.java is the driver class containing the main method.
- Minesweeper.java is the class containing the internal workings of the Minesweeper application.
- GridOutputHelper.java is a helper method for outputting the grid to the terminal.

To run the application:
Run MinesweeperApp.java
