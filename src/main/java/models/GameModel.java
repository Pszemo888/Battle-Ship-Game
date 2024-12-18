package models;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private static GameModel instance;

    private List<Player> players;
    private Player currentPlayer;
    private List<Board> boards;
    private GameState gameState;

    // Prywatny konstruktor
    private GameModel() {
        initializeGame();
    }

    // Metoda dostępu do Singletona
    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }
        return instance;
    }

    // Inicjalizacja gry
    private void initializeGame() {
        players = new ArrayList<>();
        boards = new ArrayList<>();
        gameState = GameState.IN_PROGRESS;

        // Tworzenie dwóch graczy i przypisanie im plansz
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        players.add(player1);
        players.add(player2);

        boards.add(new Board());
        boards.add(new Board());

        currentPlayer = player1;
    }

    // Metoda do zmiany aktualnego gracza
    public void switchPlayer() {
        currentPlayer = (currentPlayer == players.get(0)) ? players.get(1) : players.get(0);
    }

    // Metoda zwracająca indeks aktywnego gracza (0 lub 1)
    public int getActivePlayerIndex() {
        return (currentPlayer == players.get(0)) ? 0 : 1;
    }

    // Gettery
    public List<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public GameState getGameState() {
        return gameState;
    }

    // Resetowanie gry
    public void resetGame() {
        initializeGame();
    }
}
