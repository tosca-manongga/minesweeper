package minesweeper;

public class MinesweeperGame {
    private final GameBoard board;
    private final InputHandler inputHandler;

    public MinesweeperGame(GameBoard board, InputHandler inputHandler) {
        this.board = board;
        this.inputHandler = inputHandler;
    }

    public static void main(String[] args) {
        do {
            System.out.println("Welcome to Minesweeper!");

            GameBoard board = new MinesweeperBoard();
            InputHandler inputHandler = new ConsoleInputHandler();

            MinesweeperGame game = new MinesweeperGame(board, inputHandler);
            game.play();

            System.out.println();
            System.out.print("Press any key to continue.");
            System.console().readPassword();
        } while (true);
    }

    public void play() {
        GameInput gameInput = inputHandler.getGameInput();

        board.initialize(gameInput.getSize(), gameInput.getSize(), gameInput.getMines());

        while (!board.isGameOver() && !board.isGameWon()) {
            board.display();

            Move move = inputHandler.getMove();

            boolean validMove = board.revealCell(move.getRow(), move.getCol());

            if (validMove) {
                System.out.println("This square contains " + board.getCell(move.getRow(), move.getCol()).getAdjacentMines() + " adjacent mine(s).");
            } else {
                if (board.isGameOver()) {
                    System.out.println("Oh no, you detonated a mine! Game over.");

                    board.display();

                    return;
                } else {
                    System.out.println("Invalid move.");
                }
            }
        }

        if (board.isGameWon()) {
            System.out.println("Congratulations, you have won the game!");

            board.display();
        }
    }
}
