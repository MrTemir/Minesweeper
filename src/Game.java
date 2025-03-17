import java.util.Random;
import java.util.Scanner;

import other.Board;
import other.Cell;
import java.util.ArrayList;

public class Game {
    private Board board;

    public Game(int width, int height, int mines) {
        board = new Board(width, height, mines);
    }

    public Board getBoard() {
        return board;
    }

    public void openCell(int x, int y) {
        board.openCell(x, y);
    }

    public void toggleFlag(int x, int y) {
        board.toggleFlag(x, y);
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // Create the game board
        System.out.println("Welcome to the Minesweeper game!\nChoose a difficulty level: 1 - easy, 2 - medium, 3 - hard");
        int width = 0, height = 0, mines = 0;
        int difficulty = scanner.nextInt();
        if (difficulty == 1) {
            width = 8;
            height = 8;
            mines = 10;
        } else if (difficulty == 2) {
            width = 12;
            height = 12;
            mines = 20;
        } else if (difficulty == 3) {
            width = 14;
            height = 14;
            mines = 40;
        } else {
            System.out.println("Invalid input. Please enter a number between 1 and 3.");
        }

        Game game = new Game(width, height, mines);

        game.getBoard().printBoard(); // Print the board (initially hidden)

        boolean gameOver = false;

        while (!gameOver) {
            System.out.println("\nEnter command:");
            System.out.println("Format: o x y (open) | f x y (flag)");
            String command = scanner.next();

            int x = scanner.nextInt();
            int y = scanner.nextInt();
            x = x - 1;
            y = y - 1;

            if (command.equals("o")) {
                game.openCell(x, y);
                if (game.getBoard().getCells().get(y).get(x).hasMine()) {
                    System.out.println("BOOM! You lost.");
                    gameOver = true;
                }
            } else if (command.equals("f")) {
                game.toggleFlag(x, y);
            } else {
                System.out.println("Unknown command. Use 'o x y' or 'f x y'.");
            }

            game.getBoard().printBoard(); // Update the board

            if (game.getBoard().checkWin()) {
                System.out.println("Congratulations! You found all the mines and won!");
                gameOver = true;
            }
        }

        System.out.println("Game over!");
        scanner.close();
    }
}