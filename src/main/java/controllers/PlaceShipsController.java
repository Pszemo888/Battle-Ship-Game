package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import models.Board;
import models.Position;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import models.GameModel;

public class PlaceShipsController {

    @FXML
    private GridPane grid;

    @FXML
    private Text playerNameText;



    private Board player1Board = new Board();
    private Board player2Board = new Board();
    private int currentPlayer = 1;
    private Stage stage;
    private Runnable onFinishCallback;

    public void initialize(Stage stage, Runnable onFinishCallback) {
        this.stage = stage;
        this.onFinishCallback = onFinishCallback;
        playerNameText.setText("Gracz 1 - Rozmieszczanie statków");
        createBoard();
    }

    private void createBoard() {
        grid.getChildren().clear();
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Button cellButton = new Button();
                cellButton.setPrefSize(40, 40);
                cellButton.setStyle("-fx-background-color: lightblue;");

                Position position = new Position(row, col);  // Tworzenie obiektu Position

                cellButton.setOnAction(event -> handleCellClick(position, cellButton));
                grid.add(cellButton, col, row);
            }
        }
    }

    private void handleCellClick(Position position, Button cellButton) {
        Board board = currentPlayer == 1 ? player1Board : player2Board;

        if (board.placeShip(position, 1, true)) {
            cellButton.setStyle("-fx-background-color: navy;");
            cellButton.setText("S");

            // Logowanie informacji o umieszczeniu statku
            System.out.println("Gracz " + currentPlayer + " umieścił statek na pozycji: "
                    + position.getRow() + ", " + position.getCol());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Nie można umieścić statku na tym polu.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleFinishPlacement() {
        if (currentPlayer == 1) {
            System.out.println("Statki gracza 1 po rozmieszczeniu:");
            player1Board.logShips(); // Logowanie statków gracza 1
            currentPlayer = 2;
            playerNameText.setText("Gracz 2 - Rozmieszczanie statków");
            createBoard(); // Reset planszy dla gracza 2
        } else {
            System.out.println("Statki gracza 2 po rozmieszczeniu:");
            player2Board.logShips(); // Logowanie statków gracza 2

            if (onFinishCallback != null) {
                GameModel.getInstance().setBoards(player1Board, player2Board);
                onFinishCallback.run();
            }
        }
    }


    public Board getPlayer1Board() {
        return player1Board;
    }

    public Board getPlayer2Board() {
        return player2Board;
    }
}
