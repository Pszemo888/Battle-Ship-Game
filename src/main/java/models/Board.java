package models;

import javafx.scene.layout.GridPane;

public class Board extends GridPane {
    private final int boardSize;
    private Cell[][] cells;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        initBoard();
    }

    private void initBoard() {
        cells = new Cell[boardSize][boardSize];
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                Cell cell = new Cell(new Position(row, col));
                cells[row][col] = cell;
                this.add(cell, col, row);
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }
}
