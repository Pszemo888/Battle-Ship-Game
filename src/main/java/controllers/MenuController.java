package controllers;

import javafx.stage.Stage;
import views.MenuView;
import java.io.IOException;

public class MenuController {
    private final Stage stage;

    public MenuController(Stage stage) {
        this.stage = stage;
    }

    public void displayMainMenu() {
        MenuView menuView = new MenuView(stage);

        // Podłączanie akcji do przycisków
        menuView.getStartGameButton().setOnAction(e -> startNewGame());
        menuView.getSettingsButton().setOnAction(e -> displaySettingsMenu());
        menuView.getStatisticsButton().setOnAction(e -> displayStatistics());
        menuView.getAchievementsButton().setOnAction(e -> displayAchievements());
        menuView.getRulesButton().setOnAction(e -> displayRules());
        menuView.getExitButton().setOnAction(e -> stage.close());

        stage.setScene(menuView.createScene());
        stage.setTitle("Main Menu");
        stage.show();
    }

    private void startNewGame() {
        try {
            GameController gameController = new GameController(stage);
            gameController.startNewGame();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nie udało się rozpocząć nowej gry.");
        }
    }

    private void displaySettingsMenu() {
        System.out.println("Wyświetlanie menu ustawień...");
    }

    private void displayStatistics() {
        System.out.println("Wyświetlanie statystyk...");
    }

    private void displayAchievements() {
        System.out.println("Wyświetlanie osiągnięć...");
    }

    private void displayRules() {
        System.out.println("Wyświetlanie zasad...");
    }
}
