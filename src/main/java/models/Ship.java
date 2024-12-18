package models;

public class Ship {
    private final int size;
    private int hits;

    public Ship(int size) {
        this.size = size;
        this.hits = 0;
    }

    public boolean isSunk() {
        return hits >= size;
    }

    public void hit() {
        hits++;
    }
}
