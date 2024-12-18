package com.example.battleshipgame;

import controllers.MenuController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        MenuController menuController = new MenuController(stage);
        menuController.displayMainMenu();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
