package models;

public class Cell {
    private Ship ship;
    private boolean shot;

    public Cell() {
        this.ship = null;
        this.shot = false;
    }

    public boolean isEmpty() {
        return ship == null;
    }

    public void placeShip(Ship ship) {
        this.ship = ship; // Przypisanie statku do komórki
    }

    public boolean hasShip() {
        return ship != null; // Sprawdzenie, czy pole zawiera statek
    }

    public void shoot() {
        this.shot = true;
    }

    public boolean isShot() {
        return this.shot;
    }

    public Ship getShip() {
        return this.ship;
    }
}

