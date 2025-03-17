package other;

import java.util.*;

public class Board {
    private final int width, height; // Board dimensions
    private final int mineCount;     // Number of mines
    private final List<List<Cell>> cells; // 2D list of cells
    private final Set<Cell> minedCells;   // Set of cells with mines
    private boolean firstMove;  // Flag for the first move (to avoid hitting a mine)

    // Constructor
    public Board(int width, int height, int mineCount) {
        this.width = width;
        this.height = height;
        this.mineCount = mineCount;
        this.cells = new ArrayList<>();
        this.minedCells = new HashSet<>();
        this.firstMove = true; // First move of the player

        initializeBoard(); // Initialize the board
    }

    // Create the game board (cells without mines)
    private void initializeBoard() {
        for (int y = 0; y < height; y++) {
            List<Cell> row = new ArrayList<>();
            for (int x = 0; x < width; x++) {
                row.add(new Cell(x, y));
            }
            cells.add(row);
        }
    }

    // Place mines randomly (excluding the first clicked cell)
    public void placeMines(int startX, int startY) {
        Random random = new Random();
        int placedMines = 0;

        while (placedMines < mineCount) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Cell cell = cells.get(y).get(x);

            // Ensure the mine is not placed on the start and is not duplicated
            if (!cell.hasMine() && (x != startX || y != startY)) {
                cell.placeMine();
                minedCells.add(cell);
                placedMines++;
            }
        }

        // Update the number of mines around each cell
        calculateAdjacentMines();
    }

    // Count mines around each cell
    private void calculateAdjacentMines() {
        for (List<Cell> row : cells) {
            for (Cell cell : row) {
                if (!cell.hasMine()) {
                    int count = countAdjacentMines(cell.getX(), cell.getY());
                    cell.setAdjacentMines(count);
                }
            }
        }
    }

    // Count mines around a specific cell
    private int countAdjacentMines(int x, int y) {
        int count = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int nx = x + dx, ny = y + dy;
                if (isValid(nx, ny) && cells.get(ny).get(nx).hasMine()) {
                    count++;
                }
            }
        }
        return count;
    }

    // Open a cell
    public void openCell(int x, int y) {
        Cell cell = cells.get(y).get(x);

        // If this is the first move, place mines after the click (to avoid hitting a mine)
        if (firstMove) {
            placeMines(x, y);
            firstMove = false;
        }

        // If the cell is already opened or flagged, do nothing
        if (cell.isOpened() || cell.isFlagged()) {
            return;
        }

        cell.open();

        // If clicked on a mine, the game is over
        if (cell.hasMine()) {
            System.out.println("Boom! Game over.");
            return;
        }

        // If there are no mines around, open adjacent cells (recursively)
        if (cell.getAdjacentMines() == 0) {
            openAdjacentCells(x, y);
        }
    }

    // Recursively open empty adjacent cells
    private void openAdjacentCells(int x, int y) {
        Queue<Cell> queue = new LinkedList<>();
        queue.add(cells.get(y).get(x));

        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            int cx = current.getX(), cy = current.getY();

            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    int nx = cx + dx, ny = cy + dy;

                    if (isValid(nx, ny)) {
                        Cell neighbor = cells.get(ny).get(nx);
                        if (!neighbor.isOpened() && !neighbor.hasMine()) {
                            neighbor.open();
                            if (neighbor.getAdjacentMines() == 0) {
                                queue.add(neighbor);
                            }
                        }
                    }
                }
            }
        }
    }

    // Toggle flag
    public void toggleFlag(int x, int y) {
        Cell cell = cells.get(y).get(x);
        cell.toggleFlag();
    }

    // Check for win (all non-mine cells are opened)
    public boolean checkWin() {
        for (List<Cell> row : cells) {
            for (Cell cell : row) {
                if (!cell.hasMine() && !cell.isOpened()) {
                    return false;
                }
            }
        }
        return true;
    }

    // Check if coordinates are within bounds
    private boolean isValid(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    // Print the game board to the console (for debugging)
    public void printBoard() {
        for (List<Cell> row : cells) {
            for (Cell cell : row) {
                if (cell.isFlagged()) {
                    System.out.print("F ");
                } else {
                    System.out.print(cell + " ");
                }
            }
            System.out.println();
        }
    }

    // Getter for the cells field
    public List<List<Cell>> getCells() {
        return cells;
    }

}