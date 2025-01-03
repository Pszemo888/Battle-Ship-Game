package models;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private final int size;
    private int hits;
    private final List<Position> positions; // Pozycje zajmowane przez statek

    public Ship(int size) {
        this.size = size;
        this.hits = 0;
        this.positions = new ArrayList<>();
    }

    public boolean isSunk() {
        return hits >= size;
    }

    public void hit() {
        hits++;
    }
    public int getHits() {
        return hits;
    }

    public int getSize() {
        return size;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void addPosition(Position position) {
        positions.add(position);
    }
}
