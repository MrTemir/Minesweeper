package other;


public class Cell {
    private boolean hasMine;      // Есть ли мина в ячейке
    private boolean isOpened;     // Открыта ли ячейка
    private boolean isFlagged;    // Помечена ли ячейка флагом
    private int adjacentMines;    // Количество мин в соседних ячейках
    private final int x, y;       // Координаты ячейки

    // Конструктор
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.hasMine = false;
        this.isOpened = false;
        this.isFlagged = false;
        this.adjacentMines = 0;
    }

    // Установить мину
    public void placeMine() {
        this.hasMine = true;
    }

    // Проверить, есть ли мина
    public boolean hasMine() {
        return hasMine;
    }

    // Открыть ячейку
    public void open() {
        if (!isFlagged) {
            this.isOpened = true;
        }
    }

    // Проверить, открыта ли ячейка
    public boolean isOpened() {
        return isOpened;
    }

    // Поставить или убрать флаг
    public void toggleFlag() {
        if (!isOpened) {
            isFlagged = !isFlagged;
        }
    }

    // Проверить, стоит ли флаг
    public boolean isFlagged() {
        return isFlagged;
    }

    // Установить количество мин вокруг
    public void setAdjacentMines(int count) {
        this.adjacentMines = count;
    }

    // Получить количество мин вокруг
    public int getAdjacentMines() {
        return adjacentMines;
    }

    // Получить координаты ячейки
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Вывод информации о ячейке (для отладки)
    @Override
    public String toString() {
        if (isOpened) {
            return hasMine ? "*" : String.valueOf(adjacentMines);
        } else if (isFlagged) {
            return "F";
        } else {
            return ".";
        }
    }
}
