import java.util.Scanner;

/**
 * A simple Tic Tac Toe game.
 * Allows two players to play Tic Tac Toe and determines the winner or if the game ends in a draw.
 */
public class TicTacToe {
    public static final int X = 1;  // Player X
    public static final int O = -1; // Player O
    public static final int EMPTY = 0; // Empty cell

    private final int[][] board = new int[3][3]; // 3x3 Tic Tac Toe board
    private int currentPlayer; // Current player (X or O)

    /**
     * Constructor to initialize the Tic Tac Toe board.
     */
    public TicTacToe() {
        clearBoard();
    }

    /**
     * Clears the Tic Tac Toe board and sets the starting player to X.
     */
    public void clearBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
        currentPlayer = X;
    }

    /**
     * Puts a mark (X or O) at the specified position on the board.
     *
     * @param row the row index
     * @param col the column index
     * @throws IllegalArgumentException if the position is invalid or already occupied
     */
    public void putMark(int row, int col) throws IllegalArgumentException {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            throw new IllegalArgumentException("Invalid board position");
        }
        if (board[row][col] != EMPTY) {
            throw new IllegalArgumentException("Board position already occupied");
        }
        board[row][col] = currentPlayer;
        currentPlayer = -currentPlayer; // Switch player
    }

    /**
     * Checks if the specified player has won the game.
     *
     * @param mark the player to check (X or O)
     * @return true if the player has won, false otherwise
     */
    public boolean isWin(int mark) {
        return ((board[0][0] + board[0][1] + board[0][2] == mark * 3) || // row 0
                (board[1][0] + board[1][1] + board[1][2] == mark * 3) || // row 1
                (board[2][0] + board[2][1] + board[2][2] == mark * 3) || // row 2
                (board[0][0] + board[1][0] + board[2][0] == mark * 3) || // column 0
                (board[0][1] + board[1][1] + board[2][1] == mark * 3) || // column 1
                (board[0][2] + board[1][2] + board[2][2] == mark * 3) || // column 2
                (board[0][0] + board[1][1] + board[2][2] == mark * 3) || // diagonal
                (board[2][0] + board[1][1] + board[0][2] == mark * 3)); // reverse diagonal
    }

    /**
     * Determines the winner of the game.
     *
     * @return X if player X wins, O if player O wins, or 0 if there is no winner yet
     */
    public int winner() {
        if (isWin(X)) {
            return X;
        } else if (isWin(O)) {
            return O;
        } else {
            return 0;
        }
    }

    /**
     * Checks if the game has ended in a draw.
     *
     * @return true if the game is a draw, false otherwise
     */
    public boolean isDraw() {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == EMPTY) {
                    return false; // Game is still ongoing
                }
            }
        }
        return true; // All cells are filled, no winner
    }

    /**
     * Returns a string representation of the current state of the board.
     *
     * @return the current board state as a string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (board[i][j]) {
                    case X:
                        sb.append(" X ");
                        break;
                    case O:
                        sb.append(" O ");
                        break;
                    case EMPTY:
                        sb.append("   ");
                        break;
                }
                if (j < 2) {
                    sb.append("|");
                }
            }
            sb.append("\n");
            if (i < 2) {
                sb.append("---+---+---\n");
            }
        }

        String gameOverMessage = "\nGame Over\n";
        int result = winner();
        if (result == X) {
            sb.append(gameOverMessage).append("\nPlayer X wins!\n");
        } else if (result == O) {
            sb.append(gameOverMessage).append("\nPlayer O wins!\n");
        } else if (isDraw()) {
            sb.append(gameOverMessage).append("\nIt's a draw!\n");
        }

        return sb.toString();
    }

    /**
     * Prints the grid indices for the players to understand the board layout.
     */
    public static void printGridIndices() {
        System.out.println("\nTIC TAC TOE welcomes you!");
        System.out.println("\nHave a look at the Index Representation of the grid before you start:\n");
        System.out.println(" 0 0  |  0 1  |  0 2 ");
        System.out.println("------+-------+------");
        System.out.println(" 1 0  |  1 1  |  1 2 ");
        System.out.println("------+-------+------");
        System.out.println(" 2 0  |  2 1  |  2 2 ");
        System.out.println("\nYou have to enter the row and the column index (each ranging 0-2) to mark your move." +
                "\nFor example: Enter (1 2) if you want to mark the box with row 1 and column 2 in the grid.");
        System.out.println("_______________________________________________________________________________________\n\n");
    }

    /**
     * Main method to start the Tic Tac Toe game.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Scanner sc = new Scanner(System.in);
        printGridIndices();
        while (true) {
            System.out.println(game);
            System.out.print("Enter the row and column index for your move: ");
            int row = sc.nextInt();
            int col = sc.nextInt();
            System.out.println("_________________________________________________\n");

            try {
                game.putMark(row, col);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            if (game.winner() != 0 || game.isDraw()) {
                System.out.println(game);
                break;
            }
        }
        sc.close();
    }
}
