module mp3player {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires jid3lib;

    exports pl.grzegorz.mp3player.main to javafx.graphics;
    opens pl.grzegorz.mp3player.controller to javafx.fxml;
    opens pl.grzegorz.mp3player.mp3 to javafx.base;
}