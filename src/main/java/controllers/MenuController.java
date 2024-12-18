package controllers;

import javafx.stage.Stage;
import views.MenuView;

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
    }

    private void startNewGame() {
        // Implementacja rozpoczęcia nowej gry
        System.out.println("Rozpoczynanie nowej gry...");
    }

    private void displaySettingsMenu() {
        // Implementacja przejścia do ustawień
        System.out.println("Wyświetlanie menu ustawień...");
    }

    private void displayStatistics() {
        // Implementacja wyświetlania statystyk
        System.out.println("Wyświetlanie statystyk...");
    }

    private void displayAchievements() {
        // Implementacja wyświetlania osiągnięć
        System.out.println("Wyświetlanie osiągnięć...");
    }

    private void displayRules() {
        // Implementacja wyświetlania zasad
        System.out.println("Wyświetlanie zasad...");
    }
}
