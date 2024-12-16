package models;

import javafx.scene.control.Button;

public class Cell extends Button {
    private Position position;
    private boolean isHit = false;

    public Cell(Position position) {
        this.position = position;
        this.setMinSize(40, 40);
        this.setStyle("-fx-background-color: lightblue;");
        this.setOnAction(e -> handleHit());
    }

    private void handleHit() {
        if (!isHit) {
            isHit = true;
            this.setStyle("-fx-background-color: red;");
            this.setText("X");
        }
    }

    public Position getPosition() {
        return position;
    }
}
