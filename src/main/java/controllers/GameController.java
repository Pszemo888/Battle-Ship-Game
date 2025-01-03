package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Board;
import models.GameState;
import models.GameModel;
import models.Player;
import models.Position;
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
        gameModel.setGameState(GameState.PLACEMENT_PHASE); // Ustawienie początkowego stanu gry
        showPlaceShipsScreen(() -> {
            try {
                gameModel.setGameState(GameState.IN_PROGRESS); // Przejście do rozgrywki
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

        // Pobranie plansz z GameModel
        Board player1Board = gameModel.getBoards().get(0);
        Board player2Board = gameModel.getBoards().get(1);

        // Przekazanie plansz do kontrolera widoku
        boardController.setBoards(player1Board, player2Board);
        // Logowanie gracza zaczynającego grę
        gameModel.setActivePlayerIndex(0);
        System.out.println("Gra rozpoczęta. Pierwszy ruch wykonuje: " + gameModel.getCurrentPlayer().getName());
        boardController.setBoardEnabled(boardController.getPlayer1Grid(), false); // Plansza Gracza 1 zablokowana
        boardController.setBoardEnabled(boardController.getPlayer2Grid(), true);  // Plansza Gracza 2 odblokowana

        stage.setTitle("BattleShip Game");
        stage.setScene(scene);
        stage.show();
    }

    public void handleTurn(int row, int col, Button cellButton) {
        if (gameModel.getGameState() != GameState.IN_PROGRESS) {
            System.out.println("Gra nie jest w fazie rozgrywki!");
            return;
        }

        int activePlayerIndex = gameModel.getActivePlayerIndex();
        Player currentPlayer = gameModel.getCurrentPlayer();
        Player opponentPlayer = gameModel.getPlayers().get(1 - activePlayerIndex);
        Board opponentBoard = gameModel.getBoards().get(1 - activePlayerIndex); // Plansza przeciwnika

        // Logowanie strzału
        System.out.println(currentPlayer.getName() + " strzela na planszę gracza " + opponentPlayer.getName() + " na pozycję: (" + row + ", " + col + ")");

        // Obsługa strzału
        String result = opponentBoard.shoot(new Position(row, col));

        // Aktualizacja widoku w zależności od wyniku
        if (result.equals("Hit!") || result.equals("Hit and sunk!")) {
            cellButton.setStyle("-fx-background-color: red;");
            cellButton.setText("H");
        } else if (result.equals("Miss!")) {
            cellButton.setStyle("-fx-background-color: gray;");
            cellButton.setText("M");
        }

        System.out.println(currentPlayer.getName() + " made a move at (" + row + ", " + col + "): " + result);

        // Sprawdzenie końca gry
        if (opponentBoard.allShipsSunk()) {
            gameModel.setGameState(GameState.FINISHED);
            System.out.println("Gracz " + currentPlayer.getName() + " wygrał!");
            return;
        }

        // Przełączanie gracza
        gameModel.switchPlayer();
        updateBoardAccess();
    }




    private void updateBoardAccess() {
        int activePlayerIndex = gameModel.getActivePlayerIndex();

        boardController.setBoardEnabled(boardController.getPlayer1Grid(), activePlayerIndex == 1);
        boardController.setBoardEnabled(boardController.getPlayer2Grid(), activePlayerIndex == 0);

        System.out.println("Teraz ruch gracza: " + gameModel.getCurrentPlayer().getName());
    }

}
