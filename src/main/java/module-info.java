module com.example.battleshipgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens com.example.battleshipgame to javafx.fxml;
    exports com.example.battleshipgame;
}