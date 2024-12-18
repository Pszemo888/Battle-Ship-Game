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
        this.ship = ship;
    }

    public boolean isShot() {
        return shot;
    }

    public void shoot() {
        shot = true;
    }

    public boolean hasShip() {
        return ship != null;
    }

    public Ship getShip() {
        return ship;
    }
}
