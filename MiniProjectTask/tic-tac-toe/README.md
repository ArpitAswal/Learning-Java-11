# Tic Tac Toe Game

## Overview
Tic Tac Toe is a classic game that can be played by two players. This project allows players to customize the board size, making it possible to play on an n x n grid.

## Link: https://claude.ai/public/artifacts/bb3541d4-f262-4f22-a256-8b3c0a0b20bc

## Features
- Customizable board size (n x n)
- Player turns alternating between X and O
- Win conditions for rows, columns, and diagonals
- Draw condition if the board is full without a winner

## How to Run the Game
- Ensure you have Java installed on your machine.
- Clone the repository to your local machine.
- Navigate to the project directory.
- Compile the Java files using the command:
   ```
   mvn compile
   ```
- Run the game using the command:
   ```
   mvn exec:java -Dexec.mainClass="TicTacToe"
   ```

## Game Rules
- Players take turns to place their marks (X or O) in the grid.
- The first player to align their marks in a row, column, or diagonal wins the game.
- If the grid is filled without a winner, the game ends in a draw.

## License
This project is licensed under the MIT License.

## Program Flow
main() → startGameLoop() → Repeat until player quits

**startGameLoop() does:**
```
1. Shows welcome message
2. Loops while `playAgain = true`
3. Gets player names
4. Gets grid size
5. Initializes game
6. Plays the game
7. Asks if they want to play again
8. Shows final statistics and exits

```
---

#### **Setup Phase:**

#### A. getPlayerNames()
- Asks for Player 1 name (X)
- Asks for Player 2 name (O)
- If empty input, uses default names "Player 1" and "Player 2"

#### B. getGridSize()
- Asks user to enter grid size
- **Validates:**
  - Must be a number
  - Must be ≥ 3
  - Must be odd (3, 5, 7, 9, 11...)
- Keeps asking until valid input is received

#### C. initializeGame()**
- Creates empty board (all cells filled with spaces ' ')
- Sets `currentPlayer = 'X'` (Player 1 starts)
- Sets `gameActive = true`, `gamePaused = false`, `gameWon = false`
- Records `startTime` (current time in milliseconds)
- Resets pause counts to 0
- Resets `totalPausedDuration = 0`

---

#### **Game Play Loop (playGame method)**

#### The main loop runs while `gameActive = true`:**

***Step 1: Check Time***
- Calls `isTimeExpired()`
- If time is up → Declares draw, updates statistics, ends game

***Step 2: Display Game State***
- Calls `displayBoard()` - Shows the grid with row/column numbers
- Calls `displayTimer()` - Shows remaining time (MM:SS format)

***Step 3: Show Current Turn***
- Displays which player's turn it is
- Shows available commands (move, STOP, FINISH)

***Step 4: Get User Input***
- Reads user's command
- Converts to uppercase for easy comparison

***Step 5: Process Command***
- **If "STOP"** → Calls `handleStop()`
- **If "FINISH"** → Calls `handleFinish()`
- **Otherwise** → Calls `processMove()` to handle the move

***Step 6: Save Game***
- After each valid move, calls `saveGameState()` to save to file

---

#### **Display Methods:**

#### A. displayBoard()
```
1. Prints column numbers (0, 1, 2, ...)
2. Prints top border
3. For each row:
   - Prints row number
   - Prints each cell content (X, O, or space)
4. Prints bottom border
```

#### B. displayTimer()

- Calls `getElapsedTime()` to calculate time passed
- Calculates remaining time: `GAME_DURATION - elapsed`
- Converts milliseconds to minutes and seconds
- Displays in format: `⏱️ Time Remaining: 01:45`
- Shows warning if less than 30 seconds left

#### C. getElapsedTime()
- **If game is paused:** Returns `pausedTime - startTime - totalPausedDuration`
- **If game is active:** Returns `currentTime - startTime - totalPausedDuration`
- This ensures paused time doesn't count toward the 2-minute limit

---

#### **Move Processing (processMove method):**

***Step 1: Parse Input***
- Splits input by comma: `"1,2"` → `["1", "2"]`
- Converts strings to integers: `row = 1`, `col = 2`

***Step 2: Validate Input***
- **Format check:** Must be "row,col" format
- **Range check:** Row and column must be between 0 and (gridSize-1)
- **Occupied check:** Cell must be empty (not already X or O)
- Shows error message if any validation fails

***Step 3: Place Move***
- Sets `board[row][col] = currentPlayer`

***Step 4: Check Win***
- Calls `checkWin(row, col)` to see if this move won the game
- **If won:**
  - Displays board
  - Shows congratulations message
  - Updates win statistics
  - Sets `gameWon = true`
  - Waits for FINISH command

***Step 5: Check Draw***
- Calls `isBoardFull()` to check if board is completely filled
- **If full and no winner:**
```
  * Displays board
  * Declares draw
  * Updates statistics
  * Ends game
```

**Switch Player:**
- Changes `currentPlayer` from X to O (or O to X)

---

#### **Win Check Logic (checkWin method)**

**After a move at position (row, col), checks 4 possible win conditions:**

**A. Check Row**
- Calls `checkLine(row, 0, 0, 1)`
- Checks entire row from left to right
- `(0, 1)` means: don't move row, move column by 1 each time

**B. Check Column**
- Calls `checkLine(0, col, 1, 0)`
- Checks entire column from top to bottom
- `(1, 0)` means: move row by 1, don't move column

**C. Check Main Diagonal** (only if row == col)
- Calls `checkLine(0, 0, 1, 1)`
- Checks top-left to bottom-right diagonal
- `(1, 1)` means: move both row and column by 1

**D. Check Anti-Diagonal** (only if row + col == gridSize - 1)
- Calls `checkLine(0, gridSize-1, 1, -1)`
- Checks top-right to bottom-left diagonal
- `(1, -1)` means: move row by 1, move column by -1

#### **checkLine() Helper Method**
```
1. Gets the first symbol in the line
2. If it's empty space, returns false
3. Loops through all positions in the line
4. If any position has a different symbol, returns false
5. If all positions match, returns true (WIN!)
```
---

#### **Pause/Resume System**

**A. handleStop()**

***Step 1: Check if already paused***
- If `gamePaused = true`, shows error message

***Step 2: Check pause limit***
- Gets current player's pause count
- If they already paused once, shows error message

***Step 3: Pause the game***
- Sets `gamePaused = true`
- Records `pausedTime = current time`
- Increments player's pause count
- Shows "GAME PAUSED" message
- Displays current board state

***Step 4: Wait for resume***
- Calls `waitForResume()`

**B. waitForResume()**

**Loops while `gamePaused = true`:**

- Asks for command
- If user types **"RESUME"**:
   - Calculates how long game was paused: `pauseDuration = currentTime - pausedTime`
   - Adds to `totalPausedDuration` (so it doesn't count toward game time)
   - Sets `gamePaused = false`
   - Shows "Game Resumed" message
   - Exits loop
- If user types anything else, shows error

---

#### **Finish System**

#### handleFinish()
- Checks if `gameWon = true`
- **If game not won:** Shows error (can only finish after win)
- **If game won:** Sets `gameActive = false` to end game

#### waitForFinish()
- After someone wins, this loops until user types "FINISH"
- When "FINISH" is entered:
  - Sets `gameActive = false`
  - Shows "Game Finished" message
  - Returns to main loop to start new game

---

#### **Statistics System**

#### displayStatistics()
- Shows statistics box with:
  - Total games played
  - Each player's win count
  - Total draws

#### displayFinalStatistics()
- Called when player quits the game
- Shows same info as `displayStatistics()` plus:
  - Win rate percentages for each player
  - Calculated as: `(wins / totalGames) × 100`

---

#### **Save Game Feature**

#### saveGameState(): **Saves to file "tictactoe_save.txt":**

```
1. Game Configuration:
   - Grid size
   - Player names
   - Current player

2. Board State:
   - Writes "BOARD_START"
   - Writes each row of the board
   - Writes "BOARD_END"

3. Game Status:
   - Is game active?
   - Has someone won?

4. Timer Info:
   - Elapsed time

```
**Note:** This is called after every move, so file always has latest state

---

#### **Play Again System**

#### askPlayAgain():
- Asks user: "Do you want to play again? (yes/no)"
- Returns `true` if user types "yes" or "y"
- Returns `false` otherwise
- Controls the main game loop

---

#### 📝 **How the saveGameState Works :**

#### **Scenario 1: Fresh Start (No Save File)**
```
Program starts → No save file found → Ask player names → Play game → Save after each move
```

#### **Scenario 2: Resume Interrupted Game**
```
Program starts → Save file found → Game was active → Ask "Resume?" 
→ YES → Load board + timer + names → Continue playing from exact position
```

#### **Scenario 3: Load Statistics from Finished Game**
```
Program starts → Save file found → Game was finished 
→ Load statistics → Show stats → Start new game → Add to existing statistics
```

#### **Scenario 4: Multiple Sessions**
```
Session 1: Player1: 2 wins, Player2: 1 win → Exit (saved)
Session 2: Load stats (2-1) → Play 3 more games → Player1: +1, Player2: +2
Final Stats: Player1: 3 wins, Player2: 3 wins ✅
```

## **🔄 Complete Flow Summary:**
```
START
  ↓
Display Welcome
  ↓
Get Player Names
  ↓
Get Grid Size (validate: odd, ≥3)
  ↓
Initialize Game (create board, start timer, reset variables)
  ↓
GAME LOOP:
  ├─ Check if time expired → Draw
  ├─ Display board and timer
  ├─ Get player input
  ├─ Process command:
  │   ├─ STOP → Pause game → Wait for RESUME
  │   ├─ FINISH → End game (only after win)
  │   └─ Move → Validate → Place → Check win/draw
  ├─ Save game state
  └─ Repeat until game ends
  ↓
Show Statistics
  ↓
Ask "Play Again?"
  ├─ YES → Go back to "Get Player Names"
  └─ NO → Show Final Statistics → EXIT