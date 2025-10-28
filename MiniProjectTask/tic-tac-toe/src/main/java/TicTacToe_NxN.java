package main.java;

import java.util.*;
import java.io.*;

/**
 * NxN TicTacToe Game - A flexible TicTacToe game that supports odd-sized grids
 * (3x3, 5x5, 7x7, etc.)
 * Features: Player names, Timer (2 minutes), Pause/Resume, Statistics,
 * Save/Load game state
 */
public class TicTacToe_NxN {

    // Game board and configuration
    private String[][] board;
    private int gridSize;
    private String player1Name;
    private String player2Name;
    private String currentPlayer; // 'X' or 'O'

    // Game state management
    private boolean gameActive;
    private boolean gamePaused;
    private boolean gameWon;

    // Timer related variables
    private long startTime;
    private long pausedTime;
    private long totalPausedDuration;
    private static final long GAME_DURATION = 120000; // 2 minutes in milliseconds

    // Pause limit - each player can pause once per game
    private int player1PauseCount;
    private int player2PauseCount;
    private static final int MAX_PAUSE_PER_PLAYER = 1;

    // Statistics tracking
    private static int player1Wins = 0;
    private static int player2Wins = 0;
    private static int draws = 0;
    private static int totalGames = 0;

    // Scanner for input
    private Scanner scanner;

    /**
     * Constructor - Initializes the game
     */
    public TicTacToe_NxN() {
        scanner = new Scanner(System.in);
    }

    /**
     * Main method - Entry point of the program
     */
    public static void main(String[] args) {
        TicTacToe_NxN game = new TicTacToe_NxN();
        System.out.println();
        game.startGameLoop();
    }

    /**
     * Main game loop - Handles multiple game sessions
     */
    public void startGameLoop() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║     WELCOME TO NxN TICTACTOE GAME     ║");
        System.out.println("╔════════════════════════════════════════╗\n");

        boolean playAgain = true;

        while (playAgain) {
            // Get player names
            getPlayerNames();

            // Get grid size
            gridSize = getGridSize();

            // Initialize the game
            initializeGame();

            // Play the game
            playGame();

            // Ask if players want to play again
            playAgain = askPlayAgain();
        }

        // Show final statistics
        displayFinalStatistics();
        System.out.println("\nThank you for playing! Goodbye! 👋");
        scanner.close();
    }

    /**
     * Get player names from input
     */
    private void getPlayerNames() {
        System.out.println("\n═══ Player Setup ═══");
        System.out.print("Enter Player 1 name (X): ");
        player1Name = scanner.nextLine().trim();
        if (player1Name.isEmpty())
            player1Name = "Player 1";

        System.out.print("Enter Player 2 name (O): ");
        player2Name = scanner.nextLine().trim();
        if (player2Name.isEmpty())
            player2Name = "Player 2";

        System.out.println("\n" + player1Name + " (X) vs " + player2Name + " (O)");
    }

    /**
     * Get valid grid size from user (must be odd and >= 3)
     */
    private int getGridSize() {
        int size = 0;
        boolean validInput = false;

        System.out.println("\n═══ Grid Size Selection ═══");
        System.out.println("Enter an odd number (3, 5, 7, 9, 11, ...)");

        while (!validInput) {
            System.out.print("Enter grid size (n): ");
            try {
                size = Integer.parseInt(scanner.nextLine().trim());

                // Validate: must be odd and >= 3
                if (size < 3) {
                    System.out.println("❌ Grid size must be at least 3!");
                } else if (size % 2 == 0) {
                    System.out.println("❌ Grid size must be odd (3, 5, 7, 9, ...)!");
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Please enter a number.");
            }
        }

        return size;
    }

    /**
     * Initialize game board and variables
     */
    private void initializeGame() {
        // Create empty board
        board = new String[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                board[i][j] = i + "," + j;
            }
        }

        // Reset game state
        currentPlayer = "X"; // Player 1 starts
        gameActive = true;
        gamePaused = false;
        gameWon = false;

        // Reset timer
        startTime = System.currentTimeMillis();
        totalPausedDuration = 0;

        // Reset pause counts
        player1PauseCount = 0;
        player2PauseCount = 0;

        System.out.println("\n🎮 Game Started! You have 2 minutes to complete the game.");
        System.out.println("═══════════════════════════════════════════════════════\n");
    }

    /**
     * Main game play loop
     */
    private void playGame() {
        while (gameActive) {
            // Check if time has expired
            if (isTimeExpired()) {
                System.out.println("\n⏰ TIME'S UP! The game has ended in a DRAW!");
                draws++;
                totalGames++;
                displayStatistics();
                saveGameState(); // Save final state
                return;
            }

            // Display board and timer
            displayBoard();
            displayTimer();

            // Get current player name
            String currentPlayerName = (currentPlayer == "X") ? player1Name : player2Name;

            // Show current turn
            System.out.println("\n" + currentPlayerName + "'s turn (" + currentPlayer + ")");
            System.out.println("Commands: 'row,col' to play | 'STOP' to pause | 'FINISH' to end after win");
            System.out.println();
            System.out.print("Enter your move: ");

            String input = scanner.nextLine().trim().toUpperCase();

            // Handle commands
            if (input.equals("STOP")) {
                handleStop();
            } else if (input.equals("FINISH")) {
                handleFinish();
            } else {
                // Process move
                processMove(input);
            }

            // Save game state after each move
            if (gameActive && !gamePaused) {
                saveGameState();
            }
        }
    }

    /**
     * Display the current game board
     */
    private void displayBoard() {

        for (int i = 0; i <= gridSize; i++) {
            if (i == gridSize) {
                System.out.print("|");
            } else {
                System.out.print("| ─── ");
            }
        }
        System.out.println();

        for (int i = 0; i < gridSize; i++) {

            for (int j = 0; j < gridSize; j++) {
                if (board[i][j].equals("X") || board[i][j].equals("O")) {
                    System.out.print("|  " + board[i][j] + "  ");
                } else {
                    System.out.print("| " + board[i][j] + " ");
                }
            }
            System.out.println("|");
            for (int k = 0; k <= gridSize; k++) {
                if (k == gridSize) {
                    System.out.print("|");
                } else {
                    System.out.print("| ─── ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Display remaining time
     */
    private void displayTimer() {
        long elapsed = getElapsedTime();
        long remaining = GAME_DURATION - elapsed;

        if (remaining < 0)
            remaining = 0;

        long minutes = remaining / 60000;
        long seconds = (remaining % 60000) / 1000;

        System.out.printf("\n⏱️  Time Remaining: %02d:%02d", minutes, seconds);

        // Warning if less than 30 seconds
        if (remaining < 30000 && remaining > 0) {
            System.out.print(" ⚠️  HURRY UP!");
        }
        System.out.println();
    }

    /**
     * Get elapsed game time (excluding paused duration)
     */
    private long getElapsedTime() {
        if (gamePaused) {
            return pausedTime - startTime - totalPausedDuration;
        }
        return System.currentTimeMillis() - startTime - totalPausedDuration;
    }

    /**
     * Check if time has expired
     */
    private boolean isTimeExpired() {
        return getElapsedTime() >= GAME_DURATION;
    }

    /**
     * Process player's move
     */
    private void processMove(String input) {
        try {
            // Parse input (format: "row,col")
            String[] parts = input.split(",");
            if (parts.length != 2) {
                System.out.println("❌ Invalid format! Use: row,col (e.g., 0,0)\n");
                return;
            }

            int row = Integer.parseInt(parts[0].trim());
            int col = Integer.parseInt(parts[1].trim());

            // Validate coordinates
            if (row < 0 || row >= gridSize || col < 0 || col >= gridSize) {
                System.out.println(
                        "❌ Invalid coordinates! Row and column must be between 0 and " + (gridSize - 1) + "\n");
                return;
            }

            // Check if cell is already occupied
            if (!board[row][col].equals("" + (parts[0].trim()) + "," + (parts[1].trim()))) {
                System.out.println("❌ Cell already occupied! Choose another position.\n");
                return;
            }

            // Place the move
            board[row][col] = currentPlayer;
            System.out.println();
            // Check for win
            if (checkWin(row, col)) {
                displayBoard();
                String winnerName = (currentPlayer == "X") ? player1Name : player2Name;
                System.out.println("\n🎉 CONGRATULATIONS! " + winnerName + " (" + currentPlayer + ") WINS! 🎉");

                // Update statistics
                if (currentPlayer == "X") {
                    player1Wins++;
                } else {
                    player2Wins++;
                }
                totalGames++;
                gameWon = true;
                displayStatistics();

                // Game won, waiting for FINISH command
                System.out.println("\nType 'FINISH' to end the game and start a new one.");
                waitForFinish();
                return;
            }

            // Check for draw (board full)
            if (isBoardFull()) {
                displayBoard();
                System.out.println("\n🤝 GAME DRAW! The board is full with no winner!");
                draws++;
                totalGames++;
                displayStatistics();
                gameActive = false;
                return;
            }

            // Switch player
            currentPlayer = (currentPlayer == "X") ? "O" : "X";

        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input! Please enter numbers only (e.g., 0,0)");
        }
    }

    /**
     * Handle STOP command - Pause the game
     */
    private void handleStop() {
        if (gamePaused) {
            System.out.println("⚠️  Game is already paused! Type 'RESUME' to continue.");
            return;
        }

        // Check if current player can pause
        int currentPauseCount = (currentPlayer == "X") ? player1PauseCount : player2PauseCount;
        String currentPlayerName = (currentPlayer == "X") ? player1Name : player2Name;

        if (currentPauseCount >= MAX_PAUSE_PER_PLAYER) {
            System.out.println("❌ " + currentPlayerName + " has already used their pause! You cannot pause again.");
            return;
        }

        // Pause the game
        gamePaused = true;
        pausedTime = System.currentTimeMillis();

        // Increment pause count
        if (currentPlayer == "X") {
            player1PauseCount++;
        } else {
            player2PauseCount++;
        }

        System.out.println("\n⏸️  GAME PAUSED by " + currentPlayerName);
        System.out.println("═══════════════════════════════════════════");
        displayBoard();
        System.out.println("\nType 'RESUME' to continue the game.");

        // Wait for RESUME command
        waitForResume();
    }

    /**
     * Wait for RESUME command
     */
    private void waitForResume() {
        while (gamePaused) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("RESUME")) {
                // Calculate paused duration and add to total
                long pauseDuration = System.currentTimeMillis() - pausedTime;
                totalPausedDuration += pauseDuration;

                gamePaused = false;
                System.out.println("\n▶️  Game Resumed!");
                System.out.println("═══════════════════════════════════════════\n");
            } else {
                System.out.println("⚠️  Invalid command! Type 'RESUME' to continue.");
            }
        }
    }

    /**
     * Handle FINISH command - End game after win
     */
    private void handleFinish() {
        if (!gameWon) {
            System.out.println("⚠️  You can only use FINISH after someone wins the game!");
            return;
        }

        gameActive = false;
    }

    /**
     * Wait for FINISH command after win
     */
    private void waitForFinish() {
        while (gameActive) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("FINISH")) {
                gameActive = false;
                System.out.println("\n✅ Game Finished! Starting new game...\n");
            } else {
                System.out.println("⚠️  Invalid command! Type 'FINISH' to end this game.");
            }
        }
    }

    /**
     * Check if the current player has won after placing at (row, col)
     */
    private boolean checkWin(int row, int col) {
        // Check row
        if (checkLine(row, 0, 0, 1))
            return true;

        // Check column
        if (checkLine(0, col, 1, 0))
            return true;

        // Check main diagonal (top-left to bottom-right)
        if (row == col) {
            if (checkLine(0, 0, 1, 1))
                return true;
        }

        // Check anti-diagonal (top-right to bottom-left)
        if (row + col == gridSize - 1) {
            if (checkLine(0, gridSize - 1, 1, -1))
                return true;
        }

        return false;
    }

    /**
     * Check if a line (row, column, or diagonal) has all same symbols
     * startRow, startCol: starting position
     * rowInc, colInc: increment for row and column
     */
    private boolean checkLine(int startRow, int startCol, int rowInc, int colInc) {
        String first = board[startRow][startCol];
        if (first != "X" && first != "O")
            return false;

        for (int i = 1; i < gridSize; i++) {
            int r = startRow + i * rowInc;
            int c = startCol + i * colInc;
            if (board[r][c] != first) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the board is completely filled
     */
    private boolean isBoardFull() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (board[i][j] != "X" && board[i][j] != "O") {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Display current game statistics
     */
    private void displayStatistics() {
        System.out.println("\n╔════════════════ STATISTICS ════════════════╗");
        System.out.println("  Total Games Played: " + totalGames);
        System.out.println("  " + player1Name + " (X) Wins: " + player1Wins);
        System.out.println("  " + player2Name + " (O) Wins: " + player2Wins);
        System.out.println("  Draws: " + draws);
        System.out.println("╚════════════════════════════════════════════╝");
    }

    /**
     * Display final statistics before exiting
     */
    private void displayFinalStatistics() {
        System.out.println("\n╔══════════════ FINAL STATISTICS ═══════════════╗");
        System.out.println("  Total Games Played: " + totalGames);
        System.out.println("  " + player1Name + " (X) Wins: " + player1Wins);
        System.out.println("  " + player2Name + " (O) Wins: " + player2Wins);
        System.out.println("  Draws: " + draws);

        // Calculate win percentages
        if (totalGames > 0) {
            double p1WinRate = (player1Wins * 100.0) / totalGames;
            double p2WinRate = (player2Wins * 100.0) / totalGames;
            System.out.printf("  %s Win Rate: %.1f%%\n", player1Name, p1WinRate);
            System.out.printf("  %s Win Rate: %.1f%%\n", player2Name, p2WinRate);
        }
        System.out.println("╚═══════════════════════════════════════════════╝");
    }

    /**
     * Save current game state to a file
     * This allows the game to be reviewed while window is open
     */
    private void saveGameState() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("tictactoe_save.txt"));

            // Save game configuration
            writer.println("GRID_SIZE:" + gridSize);
            writer.println("PLAYER1:" + player1Name);
            writer.println("PLAYER2:" + player2Name);
            writer.println("CURRENT_PLAYER:" + currentPlayer);

            // Save board state
            writer.println("BOARD_START");
            for (int i = 0; i < gridSize; i++) {
                for (int j = 0; j < gridSize; j++) {
                    writer.print(" " + board[i][j]);
                }
                writer.println();
            }
            writer.println("BOARD_END");

            // Save game state
            writer.println("GAME_ACTIVE:" + gameActive);
            writer.println("GAME_WON:" + gameWon);

            // Save timer state
            writer.println("ELAPSED_TIME:" + getElapsedTime());

            writer.close();
        } catch (IOException e) {
            // Silently fail - save is optional feature
        }
    }

    /**
     * Ask if players want to play again
     */
    private boolean askPlayAgain() {
        System.out.println("\n═══════════════════════════════════════════");
        System.out.print("Do you want to play again? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }
}