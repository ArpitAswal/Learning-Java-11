/*
 * Game: Tic Tac Toe
 * Description: Tic-Tac-Toe is a classic game that two people can enjoy together. It is played on a 3x3 grid where players take turns placing their marks, X or O, in empty spots. The main goal is to get three of the same marks in a row-horizontally, vertically, or diagonally.
 * 
 * How to Play the Game
 * The steps to play the game are listed below:
 * . Both players choose either X or O to mark their cells.
 * . There will be a 3x3 grid with numbers assigned to each of the 9 cells.
 * . The player who chooses X begins to play first.
 * . He enters the cell number where he wishes to place X.
 * . Now, both O and X play alternatively until one of the two wins.
 * 
 * Winning criteria: 
 * . Whenever any of the two players has fully filled one row/ column/ diagonal with their symbol (X/ O), they win and the game ends.
 * . If neither of the two players wins, the game is said to have ended in a draw.
 */

import java.util.Scanner;

class TicTacToe {

    public static void main(String[] args) {
        Game game = new Game();
        System.out.println();
        System.out.println("X Player will play first.");
        game.start();
    }
}

class Game {

    static String[] board;
    static String currentPlayer;
    Scanner sc;

    public Game() {
        sc = new Scanner(System.in);
        board = new String[9];
        for (int i = 0; i < 9; i++) {
            board[i] = String.valueOf(i + 1);
        }
        currentPlayer = "X";
        System.out.println("Welcome to 3x3 Tic Tac Toe.");
        makeBoard();
    }

    void makeBoard() {
        System.out.println("\n");
        for (int i = 0; i < 9; i++) {
            System.out.print(" " + board[i] + " ");
            if ((i + 1) % 3 == 0) {
                System.out.println("\n");
            } else {
                System.out.print("|");
            }
        }
    }

    void start() {
        System.out.print("Enter the slot number, player " + currentPlayer + " : ");
        // System.out.println();
        int slot = sc.nextInt();
        if (slot < 1 || slot > 9) {
            System.out.println("Invalid slot number, try again.");
            start();
            return;
        }
        if (board[slot - 1].equals(String.valueOf(slot))) {
            board[slot - 1] = currentPlayer;
            makeBoard();
            switchPlayer();
            checkWinner();
        } else {
            System.out.println("Slot already taken, try again.");
            start();
            return;
        }
    }

    void switchPlayer() {
        if (currentPlayer.equals("X")) {
            currentPlayer = "O";
        } else {
            currentPlayer = "X";
        }
    }

    void checkWinner() {
        String winner = null;
        // Check rows, columns and diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i * 3].equals(board[i * 3 + 1]) && board[i * 3].equals(board[i * 3 + 2])) {
                winner = board[i * 3];
            }
            if (board[i].equals(board[i + 3]) && board[i].equals(board[i + 6])) {
                winner = board[i];
            }
        }
        if (board[0].equals(board[4]) && board[0].equals(board[8])) {
            winner = board[0];
        }
        if (board[2].equals(board[4]) && board[2].equals(board[6])) {
            winner = board[2];
        }

        if (winner != null) {
            System.out.println("Player " + winner + " wins!");
        } else {
            boolean draw = true;
            for (String cell : board) {
                if (cell.matches("\\d")) {
                    draw = false;
                    break;
                }
            }
            if (draw) {
                System.out.println("The game is a draw!");
            } else {
                start();
            }
        }
    }

}
