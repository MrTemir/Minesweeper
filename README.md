# Documentation for the "Minesweeper" Game in Java

## Introduction
The "Minesweeper" game is a classic logic game where the objective is to uncover all the cells on the board without triggering a mine. The player can flag cells to mark potential mines and open cells to discover how many mines are adjacent to them.

## Game Description
In this implementation, the player can select a difficulty level and start playing on a grid where mines are randomly placed. The player opens cells, tries to avoid explosions, and can set flags on suspicious cells.

## Starting the Game
Upon starting the game, the user will be prompted to choose a difficulty level:

1. **Easy level**: 8x8 grid with 10 mines.
2. **Medium level**: 12x12 grid with 20 mines.
3. **Hard level**: 14x14 grid with 40 mines.

### Command Input
During the game, the player inputs commands to either open cells or place flags:

- Command format to open a cell: `o x y` (where `x` is the row number and `y` is the column number).
- Command format to place a flag: `f x y` (where `x` is the row number and `y` is the column number).

The commands are entered via the keyboard, and the game updates the board state after each action.

---

## Main Classes and Methods

### `Game` Class
The `Game` class is the central component that handles game logic. It initializes the game board, handles user inputs, and manages the flow of the game. Key methods in this class include:

- **`Game(int width, int height, int mines)`**: Constructor to initialize the game with the given board dimensions and number of mines.
- **`getBoard()`**: Returns the current `Board` object representing the game state.
- **`openCell(int x, int y)`**: Opens a specific cell on the board. If the cell contains a mine, the game ends.
- **`toggleFlag(int x, int y)`**: Toggles the flag status of a specific cell.

### `Board` Class
The `Board` class is responsible for managing the grid of cells and the game mechanics related to the board. It stores the state of each cell (whether it has a mine, how many mines are adjacent to it, etc.).

### `Cell` Class
Each `Cell` represents a single square on the game board. It contains the following attributes:

- Whether the cell contains a mine.
- The number of adjacent mines.
- Whether the cell is revealed or flagged.

### Game Flow

1. **Game Initialization**: The game starts with the player choosing a difficulty level.
2. **Displaying the Board**: Initially, the board is displayed with all cells hidden. The board is updated after each move.
   
   ![Initial Board](image_placeholder.png) <!-- Insert a screenshot of the initial game board -->
   
3. **Entering Commands**: The player enters commands to either open a cell or flag it. For example:
   
   ```
   o 3 4  // Open cell at row 3, column 4
   f 5 6  // Flag cell at row 5, column 6
   ```
   
   ![Command Input](image_placeholder.png) <!-- Insert a screenshot of the command input -->

4. **Handling Cell Opening**: When a player opens a cell, if it contains a mine, the game ends with a message saying "BOOM! You lost."

   ![Mine Hit](image_placeholder.png) <!-- Insert a screenshot of a game over state -->

5. **Winning the Game**: The game checks for a win condition after each move. The win condition is met when all non-mined cells are opened. If the player finds all mines and opens all safe cells, the game congratulates the player.

   ![Win State](image_placeholder.png) <!-- Insert a screenshot of the win state -->

---

## Example Gameplay Flow

1. **Starting the Game**:
   ```
   Welcome to Minesweeper!
   Choose a difficulty level: 1 - easy, 2 - medium, 3 - hard
   ```

2. **Choosing Difficulty**: 
   ```
   Enter 1 for easy, 2 for medium, or 3 for hard: 2
   ```

3. **Opening Cells**:
   ```
   Enter command: o 3 4
   ```

   If the cell contains no mine, the number of adjacent mines is displayed.

4. **Placing Flags**:
   ```
   Enter command: f 5 6
   ```

5. **Game Over** (if a mine is hit):
   ```
   BOOM! You lost.
   Game Over!
   ```

   ![Game Over](image_placeholder.png) <!-- Insert a screenshot of game over state -->

6. **Winning**:
   ```
   Congratulations! You found all the mines and won!
   ```

   ![Victory](image_placeholder.png) <!-- Insert a screenshot of the victory state -->

---

## Conclusion
This Minesweeper game provides a fun and interactive experience for players to test their logic and memory skills. The game ends when the player either hits a mine or successfully flags all mines and opens all safe cells.

### Notes
- This game requires a terminal or command-line interface for input and output.
- Make sure to use valid commands (`o x y` or `f x y`) to ensure proper functionality.

--- 

You can insert screenshots of the game at each stage (initial board, command input, game over, and victory) in place of the `image_placeholder.png` text to provide visual clarity.
