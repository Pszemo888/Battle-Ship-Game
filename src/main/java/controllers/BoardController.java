package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import models.Board;

public class BoardController {

    private static final int BOARD_SIZE = 10;

    @FXML
    private GridPane player1Grid;
    private GameController gameController;
    @FXML
    private GridPane player2Grid;

    private Board player1Board;
    private Board player2Board;

    public void initialize(GameController gameController) {
        this.gameController = gameController;
        player1Board = new Board();
        player2Board = new Board();

        createBoard(player1Grid);
        createBoard(player2Grid);
    }

    private void createBoard(GridPane grid) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Button cellButton = new Button();
                cellButton.setPrefSize(40, 40);
                cellButton.setStyle("-fx-background-color: lightblue;");

                int finalRow = row;
                int finalCol = col;

                cellButton.setOnAction(event -> handleCellClick(finalRow, finalCol, cellButton));
               grid.add(cellButton, col, row);
            }
        }
    }

    private void handleCellClick(int row, int col, Button cellButton) {
        cellButton.setStyle("-fx-background-color: gray;");
        cellButton.setText("X");
        System.out.println("Clicked on cell: (" + row + ", " + col + ")");

        if (gameController != null) {
            gameController.handleTurn(row, col, cellButton);
        }
    }
    public void setBoardEnabled(GridPane grid, boolean isEnabled) {
        grid.getChildren().forEach(node -> {
            if (node instanceof Button) {
                node.setDisable(!isEnabled);
            }
        });
    }

    public GridPane getPlayer1Grid() {
        return player1Grid;
    }

    public GridPane getPlayer2Grid() {
        return player2Grid;
    }
}
