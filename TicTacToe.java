import java.util.Scanner;

/**
 * The classical Tic Tac Toe game.
 * It is console-based and allows two players.
 */
public class TicTacToe {
    public static final int X = 1;  // Player X
    public static final int O = -1; // Player O
    public static final int EMPTY = 0; // Empty cell

    private final int[][] grid = new int[3][3]; // 3x3 Tic Tac Toe grid
    private int currentPlayer; // Current player (X or O)
    private String player_X;
    private String player_O;

    /**
     * Constructor to initialize the Tic Tac Toe grid.
     */
    public TicTacToe(String player_X, String player_O) {
        this.player_X = player_X;
        this.player_O = player_O;
        clearGrid();
    }

    /**
     * Clears the Tic Tac Toe grid and sets the starting player as player-X.
     */
    public void clearGrid() {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                grid[i][j] = EMPTY;
            }
        }
        currentPlayer = X;
    }

    /**
     * Puts a mark (X or O) at the specified cell/position on the grid.
     *
     * @param row the row index
     * @param col the column index
     * @throws IllegalArgumentException if the cell/position is invalid or already occupied
     */
    public void putMark(int row, int col) throws IllegalArgumentException {
        if((row!=0 && row!=1 && row!=2) || (col!=0 && col!=1 && col!=2)) {
            throw new IllegalArgumentException("Invalid cell position!");
        }
        if(grid[row][col] != EMPTY) {
            throw new IllegalArgumentException("Oops! Cell already marked.");
        }
        grid[row][col] = currentPlayer;
        currentPlayer = -currentPlayer; // to switch the player
    }

    /**
     * Checks if the specified player has won the game.
     *
     * @param mark the player to check (X or O)
     * @return true if the player has won, otherwise false
     */
    public boolean isWin(int mark) {
        return ((grid[0][0] + grid[0][1] + grid[0][2] == mark * 3) || // row 0
                (grid[1][0] + grid[1][1] + grid[1][2] == mark * 3) || // row 1
                (grid[2][0] + grid[2][1] + grid[2][2] == mark * 3) || // row 2
                (grid[0][0] + grid[1][0] + grid[2][0] == mark * 3) || // col 0
                (grid[0][1] + grid[1][1] + grid[2][1] == mark * 3) || // col 1
                (grid[0][2] + grid[1][2] + grid[2][2] == mark * 3) || // col 2
                (grid[0][0] + grid[1][1] + grid[2][2] == mark * 3) || // backward diagonal 
                (grid[2][0] + grid[1][1] + grid[0][2] == mark * 3));  // forward diagonal
    }

    /**
     * Determines the winner of the game.
     *
     * @return X if player X wins, O if player O wins, or 0 if there is no winner yet
     */
    public int winner() {
        if(isWin(X)) {
            return X;
        }
        else if(isWin(O)) {
            return O;
        } 
        else {
            return 0;
        }
    }

    /**
     * Checks if the game has ended in a tie.
     *
     * @return true if the game is a tie, otherwise false 
     */
    public boolean isDraw() {
        for(int[] row : grid) {
            for(int cell : row) {
                if(cell == EMPTY) {
                    return false;   // Game is still ongoing
                }
            }
        }
        return true;    // All cells are filled, no winner
    }

    /**
     * Returns a string representation of the current state of the grid.
     *
     * @return the current grid state as a string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                switch(grid[i][j]) {
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
                if(j<2) {
                    sb.append("|");
                }
            }
            sb.append("\n");
            if(i<2) {
                sb.append("---+---+---\n");
            }
        }

        String gameOverMessage = "\n\nGame Over\n";
        int result = winner();
        if(result == X) {
            sb.append(gameOverMessage).append(player_X.toUpperCase()).append(" WINS!\n");

        } 
        else if(result == O) {
            sb.append(gameOverMessage).append(player_O.toUpperCase()).append(" WINS!\n");
        } 
        else if(isDraw()) {
            sb.append(gameOverMessage).append("\nIt's a tie!\n");
        }
        return sb.toString();
    }

    /**
     * Prints the grid indices for the players to understand the grid layout.
     */
    public static void printGridIndices() {
        System.out.println("___________________________________________________________________________________________________");
        System.out.println("\nBasic Instructions: " +
                        "\n> The game is played on a 3x3 grid. One player uses 'X' and the other uses 'O' to mark empty cells." +
                        "\n> The winner is the player who first marks three cells in a single row, column, or diagonal." +
                        "\n> The game ends in a tie when all nine cells have been marked and nobody has won.");
        
        System.out.println("\nIndex Representation of the grid: \n");
        System.out.println(" 0 0  |  0 1  |  0 2 ");
        System.out.println("------+-------+------");
        System.out.println(" 1 0  |  1 1  |  1 2 ");
        System.out.println("------+-------+------");
        System.out.println(" 2 0  |  2 1  |  2 2 ");

        System.out.println("\n> You have to enter the row and column index (each ranging 0-2) of the cell you wanna mark" +
                            "\n  For example: Enter 1<space>2 if you want to mark the cell with row 1 and column 2 in the grid");
        System.out.println("___________________________________________________________________________________________________\n");
    }

    /**
     * Main method to start the Tic Tac Toe game.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        System.out.println("\nTIC TAC TOE ----------------------------|\n\nThis game will require two players.");

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the name of Player X: ");
        String player_X = sc.nextLine();
        System.out.print("Enter the name of Player O: ");
        String player_O = sc.nextLine();

        TicTacToe game = new TicTacToe(player_X, player_O);
        printGridIndices();

        System.out.println("Game On: "+player_X+" vs "+player_O+"\n");

        while(true) {
            System.out.println(game);
            String player = (game.currentPlayer == X ? player_X : player_O) + "'s turn";
            System.out.print(player+"\nEnter the row and column index: ");
            int row = sc.nextInt();
            int col = sc.nextInt();
            System.out.println("___________________________________\n");

            try {
                game.putMark(row, col);
            } 
            catch(IllegalArgumentException e) {
                System.out.println("Invalid move... Try again.");
                System.out.println("___________________________________\n");
                continue;
            }

            if(game.winner()!= 0 || game.isDraw()) {
                System.out.println(game);
                break;
            }
        }
        sc.close();
    }
}
