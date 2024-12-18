package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Player;

import java.io.IOException;

public class GameController {
    private final Stage stage;
    private final Player player1;
    private final Player player2;
    private boolean player1Turn;

    public GameController(Stage stage) {
        this.stage = stage;
        this.player1 = new Player("Player 1");
        this.player2 = new Player("Player 2");
        this.player1Turn = true;
    }


    public void startNewGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/Board.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("BattleShip Game");
        stage.setScene(scene);
        stage.show();
    }
    public void nextTurn() {
        player1Turn = !player1Turn;
        System.out.println("It's " + (player1Turn ? player1.getName() : player2.getName()) + "'s turn.");
    }
}
