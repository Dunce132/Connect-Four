import java.util.Scanner;

public class ConnectFour
{
    
    static String[][] board = new String[6][7];
    static boolean p1Move = true;
    static boolean won = false;
    static String winner = " ";
    
    public ConnectFour()
    {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                this.board[i][j] = " ";
            }
        }
    }

    public static boolean insertAtCol(int col) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == " ") {
                if (p1Move) {
                    board[i][col] = "R";
                    return true;
                }
                else {
                    board[i][col] = "Y";
                    return true;
                }
            }
        }
        return false;
    }
    
    public static void drawBoard() 
    {
        String divider = "  +---+---+---+---+---+---+---+";
        System.out.println("    1   2   3   4   5   6   7");
        System.out.println(divider);
        for (int i = board.length - 1; i > -1; i--) {
            String temp = "  | ";
            for (int j = 0; j < board[0].length; j++) {
                temp += board[i][j] + " | ";
            }
            System.out.println(temp);
            System.out.println(divider);
        }
    }
    
    //3 goals
    //checks for valid column
    //checks if there is space
    //if there is space, insert correct letter into space
    public static boolean validInput(String move) {
        int one = Integer.parseInt(move);
        System.out.println(one);
        one--;
        if (one < 0 || one > 7) {
            return false;
        }
        return insertAtCol(one);
    }
    
    public static void hasWon() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != " ") {
                    checkVertical(i, j);
                    checkHorizontal(i, j);
                    checkDiagonal(i, j);
                }
            }
        }
    }
    
    public static void checkVertical(int row, int col) {
        String mark = board[row][col];
        int win = 0;
        if (row - 4 >= 0) {
            for (int i = 1; i < 4; i++) {
                if (mark.equals(board[row - i][col])) {
                    win++;
                }
            }
            if (win == 3) {
                won = true;
                if (mark == "R") {
                    winner = "Player 1";
                }
                else {
                    winner = "Player 2";
                }
            }
        }
        if (row + 4 <= 6) {
            win = 0;
            for (int i = 1; i < 4; i++) {
                if (mark.equals(board[row + i][col])) {
                    win++;
                }
            }
        }
        if (win == 3) {
                won = true;
                if (mark == "R") {
                    winner = "Player 1";
                }
                else {
                    winner = "Player 2";
                }
            }
    }
    
    public static void checkHorizontal(int row, int col) {
        String mark = board[row][col];
        int win = 0;
        if (col - 4 >= 0) {
            for (int i = 1; i < 4; i++) {
                if (mark.equals(board[row][col - i])) {
                    win++;
                }
            }
            if (win == 3) {
                won = true;
                if (mark == "R") {
                    winner = "Player 1";
                }
                else {
                    winner = "Player 2";
                }
            }
        }
        if (col + 4 <= 6) {
            win = 0;
            for (int i = 1; i < 4; i++) {
                if (mark.equals(board[row][col + i])) {
                    win++;
                }
            }
            if (win == 3) {
                won = true;
                if (mark == "R") {
                    winner = "Player 1";
                }
                else {
                    winner = "Player 2";
                }
            }
        }
        
    }
    
    public static void checkDiagonal(int row, int col) {
        String mark = board[row][col];
        int win = 0;
        if (col - 4 >= 0 && row - 4 >= 0) {
            for (int i = 1; i < 4; i++) {
                if (mark.equals(board[row - i][col - i])) {
                    win++;
                }
            }
            if (win == 3) {
                won = true;
                if (mark == "R") {
                    winner = "Player 1";
                }
                else {
                    winner = "Player 2";
                }
            }
        }
        if (col - 4 >= 0 && row + 4 <= 5) {
            win = 0;
            for (int i = 1; i < 4; i++) {
                if (mark.equals(board[row + i][col - i])) {
                    win++;
                }
            }
            if (win == 3) {
                won = true;
                if (mark == "R") {
                    winner = "Player 1";
                }
                else {
                    winner = "Player 2";
                }
            }
        }
        if (col + 4 >= 6 && row - 4 >= 0) {
            win = 0;
            for (int i = 1; i < 4; i++) {
                if (mark.equals(board[row - i][col + i])) {
                    win++;
                }
            }
            if (win == 3) {
                won = true;
                if (mark == "R") {
                    winner = "Player 1";
                }
                else {
                    winner = "Player 2";
                }
            }
        }
        if (col + 4 <= 6 && row + 4 <= 5) {
            for (int i = 1; i < 4; i++) {
                if (mark.equals(board[row + i][col + i])) {
                    win++;
                }
            }
            if (win == 3) {
                won = true;
                if (mark == "R") {
                    winner = "Player 1";
                }
                else {
                    winner = "Player 2";
                }
            }
        }
    }
    
    public static void main(String[] args) {
        //initializing variables
        ConnectFour game = new ConnectFour();
        
        String move = " ";
        boolean valid = false;
        boolean again = true;
        
        //initializing 2D array to be " " to make printing easier
        for (int i = 0; i < game.board.length; i++) {
            for (int j = 0; j < game.board[0].length; j++) {
                game.board[i][j] = " ";
            }
        }
        //begin the game
        System.out.println("Welcome to Connect4!");
        Scanner reader = new Scanner(System.in);
        while (again) {
            while (!won) {
                drawBoard();                
                if (p1Move) {
                    System.out.println("Player 1, enter your move! (enter move as column number)");
                    move = reader.nextLine();
                } else {
                    System.out.println("Player 2, enter your move! (enter move as column number)");
                    move = reader.nextLine();
                }
                System.out.println("You entered col " + move);
                valid = validInput(move);
                if (!valid) {
                    System.out.println("You did not enter a valid move!");
                    }
                else {
                    p1Move = !p1Move;
                    }
                hasWon();
            }
            drawBoard();
            System.out.println(winner + " has won the game!");
            System.out.println("Do you want to play again? (Y/N)");
            String next = reader.nextLine();
            while (!next.equals("Y") && !next.equals("N")) {
                System.out.println("Invalid input, please enter again.");
                next = reader.nextLine();
            }
            if (next.equals("N")) {
                again = false;
                System.out.println("Goodbye!");
            }
            else {
                won = false;
                for (int i = 0; i < game.board.length; i++) {
                    for (int j = 0; j < game.board[0].length; j++) {
                        game.board[i][j] = " ";
                    }
                }
                p1Move = true;
            }
        }
        
        
    }
    
    
}
