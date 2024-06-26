import java.util.*;

public class TicTacToe {
    public static final int X = 1;
    public static final int O = -1;
    public static final int EMPTY = 0;
    private int[][] board = new int[3][3];
    private int player;

    public TicTacToe() {
        clearBoard();
    }

    public void clearBoard() {
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                board[i][j] = EMPTY;
        player = X;
    }

    public void putMark(int row, int col) throws IllegalArgumentException {
        if(row < 0 || row >= 3 || col < 0 || col >= 3)
            throw new IllegalArgumentException("Invalid board position");
        if(board[row][col] != EMPTY)
            throw new IllegalArgumentException("Board position already occupied");
        board[row][col] = player;
        player = -player;
    }

    public boolean isWin(int mark) {
        return ((board[0][0] + board[0][1] + board[0][2] == mark * 3) // row0
                || (board[1][0] + board[1][1] + board[1][2] == mark * 3) // row1
                || (board[2][0] + board[2][1] + board[2][2] == mark * 3) // row2
                || (board[0][0] + board[1][0] + board[2][0] == mark * 3) // column0
                || (board[0][1] + board[1][1] + board[2][1] == mark * 3) // column1
                || (board[0][2] + board[1][2] + board[2][2] == mark * 3) // column2
                || (board[0][0] + board[1][1] + board[2][2] == mark * 3) // diagonal
                || (board[2][0] + board[1][1] + board[0][2] == mark * 3)); // revdiag
    }

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

    public boolean isDraw() {
        for(int[] row : board) {
            for(int cell : row) {
                if(cell == EMPTY) {
                    return false; // Game still ongoing
                }
            }
        }
        return true; // All cells filled, no winner
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        // sb.append("\nTic Tac Toe\n");
        // sb.append("-----------\n");
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                switch(board[i][j]) {
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
                if(j < 2) {
                    sb.append("|");
                }
            }
            sb.append("\n");
            if(i < 2) {
                sb.append("---+---+---\n");
            }
        }

        String s = "\nGame Over\n";
        int result = winner();
        if(result == X) {
            sb.append(s+"\nPlayer X wins!\n");
        } 
        else if(result == O) {
            sb.append(s+"\nPlayer O wins!\n");
        } 
        else if(isDraw()) {
            sb.append(s+"\nIt's a draw!\n");
        }

        return sb.toString();
    }

    public static void printGridIndices() {
        System.out.println("\nTIC TAC TOE welcomes you!");
        System.out.println("\nHave a look at the Index Representation of the grid before you start:\n");
        System.out.println(" 0 0  |  0 1  |  0 2 ");
        System.out.println("------+-------+------");
        System.out.println(" 1 0  |  1 1  |  1 2 ");
        System.out.println("------+-------+------");
        System.out.println(" 2 0  |  2 1  |  2 2 ");
        System.out.println("\nYou have to enter the row and the column index (each ranging 0-2) to mark your move."+
                            "\nFor example: Enter (1 2) if you wanna mark the box with row-1 and column-2 in the grid.");
        System.out.println("_______________________________________________________________________________________\n\n");
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Scanner sc = new Scanner(System.in);
        printGridIndices();
        while(true) {
            System.out.println(game);
            System.out.print("Enter the row and column index for your move: ");
            int row = sc.nextInt();
            int col = sc.nextInt();
            System.out.println("_________________________________________________\n");

            try {
                game.putMark(row, col);
            } 
            catch(IllegalArgumentException e) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            if(game.winner() != 0 || game.isDraw()) {
                System.out.println(game);
                break;
            }
        }
        sc.close();
    }

}
