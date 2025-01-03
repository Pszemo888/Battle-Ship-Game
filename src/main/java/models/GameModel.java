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
        gameState = GameState.PLACEMENT_PHASE;  // Ustawienie początkowego stanu gry

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

    // Metoda do sprawdzania aktualnego stanu gry
    public boolean isPlacementPhase() {
        return gameState == GameState.PLACEMENT_PHASE;
    }

    public boolean isGameInProgress() {
        return gameState == GameState.IN_PROGRESS;
    }

    public void setGameState(GameState newState) {
        gameState = newState;
    }

    public int getActivePlayerIndex() {
        return (currentPlayer == players.get(0)) ? 0 : 1;
    }
    public void setActivePlayerIndex(int index) {
        currentPlayer = players.get(index);
    }

    public void setBoards(Board board1, Board board2) {
        boards.clear();
        boards.add(board1);
        boards.add(board2);
        System.out.println("Plansze graczy zapisane w GameModel.");
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
