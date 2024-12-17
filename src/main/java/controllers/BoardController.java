package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BoardController {

    private static final int BOARD_SIZE = 10;

    @FXML
    private GridPane boardGrid;

    @FXML
    public void initialize() {
        createBoard();
    }

    private void createBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Button cellButton = new Button();
                cellButton.setPrefSize(40, 40);
                cellButton.setStyle("-fx-background-color: lightblue;");

                // Obsługa kliknięcia na komórkę
                int finalRow = row;
                int finalCol = col;
                cellButton.setOnAction(e -> handleCellClick(finalRow, finalCol, cellButton));

                boardGrid.add(cellButton, col, row);
            }
        }
    }

    private void handleCellClick(int row, int col, Button cellButton) {
        cellButton.setStyle("-fx-background-color: gray;");
        cellButton.setText("X");
        System.out.println("Clicked on cell: (" + row + ", " + col + ")");
    }
}
