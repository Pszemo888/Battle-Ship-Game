package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Board;
import models.GameModel;
import models.Player;
import java.io.IOException;
import javafx.scene.control.Button;

public class GameController {
    private final Stage stage;
    private final GameModel gameModel;
    private BoardController boardController;

    public GameController(Stage stage) {
        this.stage = stage;
        this.gameModel = GameModel.getInstance();
    }

    public void startNewGame() throws IOException {
        showPlaceShipsScreen(() -> {
            try {
                startMainGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void showPlaceShipsScreen(Runnable onFinishCallback) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/PlaceShipsView.fxml"));
        Scene scene = new Scene(loader.load(), 1000, 600);

        PlaceShipsController controller = loader.getController();
        controller.initialize(stage, () -> {
            Board player1Board = controller.getPlayer1Board();
            Board player2Board = controller.getPlayer2Board();
            onFinishCallback.run();
        });

        stage.setScene(scene);
        stage.setTitle("Rozmieszczanie statków");
        stage.show();
    }

    private void startMainGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/Board.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);

        boardController = fxmlLoader.getController();
        boardController.initialize(this);

        stage.setTitle("BattleShip Game");
        stage.setScene(scene);
        stage.show();
    }

    public void handleTurn(int row, int col, Button cellButton) {
        Player currentPlayer = gameModel.getCurrentPlayer();

        cellButton.setText("X");
        cellButton.setDisable(true);

        System.out.println(currentPlayer.getName() + " made a move at (" + row + ", " + col + ")");

        gameModel.switchPlayer();
        updateBoardAccess();
    }

    private void updateBoardAccess() {
        int activePlayerIndex = gameModel.getActivePlayerIndex();

        boardController.setBoardEnabled(boardController.getPlayer1Grid(), activePlayerIndex == 0);
        boardController.setBoardEnabled(boardController.getPlayer2Grid(), activePlayerIndex == 1);

        System.out.println("Now it's " + gameModel.getCurrentPlayer().getName() + "'s turn.");
    }
}
