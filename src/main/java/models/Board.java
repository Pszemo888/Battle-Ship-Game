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

    public boolean placeShip(int row, int col, int size, boolean horizontal) {
        if (canPlaceShip(row, col, size, horizontal)) {
            Ship ship = new Ship(size);
            ships.add(ship);

            for (int i = 0; i < size; i++) {
                if (horizontal) {
                    grid[row][col + i].placeShip(ship);
                } else {
                    grid[row + i][col].placeShip(ship);
                }
            }
            return true;
        }
        return false;
    }

    private boolean canPlaceShip(int row, int col, int size, boolean horizontal) {
        for (int i = 0; i < size; i++) {
            int r = horizontal ? row : row + i;
            int c = horizontal ? col + i : col;
            if (r >= SIZE || c >= SIZE || !grid[r][c].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public String shoot(int row, int col) {
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
