This Minesweeper application was developed on MacOS Sequoai 15.6 (x86) using the below software and should run on any
machine with JRE.

- IntelliJ IDEA CE
- JDK 24
- Maven
- JUnit 5

Assumptions:

- Input sanitization/validation is not covered in this implementation.
- Maximum size of the grid should be at most 10 to ensure proper output formatting of the game board.

Design and Classes/Interfaces:

- MinesweeperGame.java is the driver class for the Minesweeper application.

- InputHandler is the interface for handling input from user.
- GameBoard.java is the interface for representing the game board.

- ConsoleInputHandler.java is the class for handling console input from user.
- MinesweeperBoard.java is the class containing the game logic.

- Cell.java is the POJO class representing a cell in the board.
- GameInput.java is the POJO class for storing the game parameters.
- Move.java is the POJO class for storing move made by the user.

- CellTest.java is the class containing unit testing of Cell.java.
- GameInputTest.java is the class containing unit testing of GameInput.java.
- MoveTest.java is the class containing unit testing of Move.java.
- MinesweeperBoardTest.java is the class containing unit testing of MinesweeperBoard.java.

To run the application:
Run MinesweeperGame.java
