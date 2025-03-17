import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

public class GameUI extends Application {
    private static final int SIZE = 8; // Размер поля
    private static final int MINES = 10; // Количество мин
    private Game game; // Логика игры
    private Button[][] buttons; // Кнопки для клеток

    @Override
    public void start(Stage primaryStage) {
        game = new Game(SIZE, SIZE, MINES);
        buttons = new Button[SIZE][SIZE];
        GridPane grid = new GridPane();

        // Создаем кнопки для каждой клетки
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                Button btn = new Button();
                btn.setMinSize(40, 40); // Размер кнопки (ячейки)
                int finalX = x;
                int finalY = y;

                // Обработка нажатия на клетку (левая кнопка мыши для открытия)
                btn.setOnMouseClicked(e -> {
                    if (e.getButton() == MouseButton.PRIMARY) {
                        handleClick(finalX, finalY); // Левый клик - открытие клетки
                    } else if (e.getButton() == MouseButton.SECONDARY) {
                        handleFlag(finalX, finalY); // Правый клик - установка/снятие флага
                    }
                });

                buttons[y][x] = btn;
                grid.add(btn, x, y);
            }
        }

        // Настроим сцену
        Scene scene = new Scene(grid, SIZE * 40, SIZE * 40);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Minesweeper");
        primaryStage.show();
    }

    // Обработка левого клика мышью (открытие клетки)
    private void handleClick(int x, int y) {
        game.openCell(x, y);
        updateUI();

        // Если клетка с миной, выводим сообщение о проигрыше
        if (game.getBoard().getCell(x, y).hasMine()) {
            showAlert("Game Over", "BOOM! You lost.");
        }

        // Проверка на победу
        if (game.getBoard().checkWin()) {
            showAlert("You Win!", "Congratulations! You found all the mines and won!");
        }
    }

    // Обработка правого клика мышью (ставим/снимаем флаг)
    private void handleFlag(int x, int y) {
        game.toggleFlag(x, y);
        updateUI();
    }

    // Обновление графического интерфейса
    private void updateUI() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                Cell cell = game.getBoard().getCell(x, y);
                if (cell.isOpened()) {
                    // Если клетка открыта, показываем количество мин вокруг или мину
                    if (cell.hasMine()) {
                        buttons[y][x].setText("💣");
                    } else {
                        buttons[y][x].setText(cell.getAdjacentMines() == 0 ? "" : String.valueOf(cell.getAdjacentMines()));
                    }
                    buttons[y][x].setDisable(true); // Отключаем кнопку после открытия
                } else if (cell.isFlagged()) {
                    buttons[y][x].setText("🚩"); // Если клетка флажирована, показываем флаг
                } else {
                    buttons[y][x].setText(""); // Иначе кнопка пуста
                }
            }
        }
    }

    // Показываем всплывающее сообщение
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
