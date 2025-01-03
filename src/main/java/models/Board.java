package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int SIZE = 10;
    private final Cell[][] grid = new Cell[SIZE][SIZE];
    private final List<Ship> ships = new ArrayList<>();
    private final List<Position> shipPositions = new ArrayList<>(); // Lista zajętych pozycji

    public Board() {
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public boolean placeShip(Position position, int size, boolean horizontal) {
        if (canPlaceShip(position, size, horizontal)) {
            Ship ship = new Ship(size);
            ships.add(ship); // Dodanie statku do listy
            System.out.println("Dodano statek o rozmiarze: " + size + " na pozycję: ("
                    + position.getRow() + ", " + position.getCol() + "), orientacja: " + (horizontal ? "pozioma" : "pionowa"));

            for (int i = 0; i < size; i++) {
                int row = horizontal ? position.getRow() : position.getRow() + i;
                int col = horizontal ? position.getCol() + i : position.getCol();

                grid[row][col].placeShip(ship); // Przypisanie statku do pola
                ship.addPosition(new Position(row, col)); // Dodanie pozycji do statku
                System.out.println("Pole zajęte przez statek: (" + row + ", " + col + ")");
            }

            return true;
        }
        System.out.println("Nie można umieścić statku na pozycji: ("
                + position.getRow() + ", " + position.getCol() + ")");
        return false;
    }


    private boolean canPlaceShip(Position position, int size, boolean horizontal) {
        for (int i = 0; i < size; i++) {
            int row = horizontal ? position.getRow() : position.getRow() + i;
            int col = horizontal ? position.getCol() + i : position.getCol();
            if (row >= SIZE || col >= SIZE || !grid[row][col].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void logShips() {
        System.out.println("Lista statków na planszy:");
        for (Ship ship : ships) {
            System.out.println("Statek o rozmiarze: " + ship.getSize());
            for (Position position : ship.getPositions()) {
                System.out.println(" - Pozycja: (" + position.getRow() + ", " + position.getCol() + ")");
            }
        }
    }

    // Zmiana metody shoot na użycie Position
    public String shoot(Position position) {
        int row = position.getRow();
        int col = position.getCol();

        if (grid[row][col].isShot()) {
            return "Already shot here!";
        }

        grid[row][col].shoot();

        if (grid[row][col].hasShip()) {
            grid[row][col].getShip().hit();
            if (grid[row][col].getShip().isSunk()) {
                return "Hit and sunk!";
            }
            return "Hit!";
        }
        return "Miss!";
    }
    public List<Position> getShipPositions() {
        return new ArrayList<>(shipPositions);
    }

    public boolean allShipsSunk() {
        if (ships.isEmpty()) {
            System.out.println("Lista statków jest pusta. Wszystkie statki zatopione: false");
            return false;
        }

        for (Ship ship : ships) {
            System.out.println("Statek o rozmiarze: " + ship.getSize() + ", Trafienia: " + ship.getHits());
        }

        boolean result = ships.stream().allMatch(Ship::isSunk);
        System.out.println("Czy wszystkie statki zatopione? " + result);
        return result;
    }

}
