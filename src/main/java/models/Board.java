package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int SIZE = 10;
    private final Cell[][] grid = new Cell[SIZE][SIZE];
    private final List<Ship> ships = new ArrayList<>();

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

    // Zmiana metody placeShip na użycie Position
    public boolean placeShip(Position position, int size, boolean horizontal) {
        if (canPlaceShip(position, size, horizontal)) {
            Ship ship = new Ship(size);
            ships.add(ship);

            for (int i = 0; i < size; i++) {
                int row = horizontal ? position.getRow() : position.getRow() + i;
                int col = horizontal ? position.getCol() + i : position.getCol();
                grid[row][col].placeShip(ship);
            }
            return true;
        }
        return false;
    }

    // Zmiana metody canPlaceShip na użycie Position
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

    public boolean allShipsSunk() {
        return ships.stream().allMatch(Ship::isSunk);
    }
}
