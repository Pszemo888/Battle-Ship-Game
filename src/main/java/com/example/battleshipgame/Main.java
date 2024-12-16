package com.example.battleshipgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Board;
public class Main extends Application {
    private static final int BOARD_SIZE = 10;

    @Override
    public void start(Stage primaryStage) {
        Board playerBoard = new Board(BOARD_SIZE);
        Board opponentBoard = new Board(BOARD_SIZE);

        HBox root = new HBox(20, playerBoard, opponentBoard);
        root.setStyle("-fx-padding: 20; -fx-background-color: beige;");

        Scene scene = new Scene(root, 900, 450);

        primaryStage.setTitle("Battleship Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
