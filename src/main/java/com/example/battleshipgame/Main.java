package com.example.battleshipgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Board;
import controllers.GameController;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        GameController gameController = new GameController();
        gameController.initStage(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}