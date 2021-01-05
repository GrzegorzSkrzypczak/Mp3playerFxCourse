package pl.grzegorz.mp3player.controller;


import javafx.fxml.FXML;

public class MainController {
    @FXML
    ControlPaneController controlPaneController;
    @FXML
    MenuPaneController menuPaneController;
    @FXML
    private ContentPaneController contentPaneController;

    public void initialize() {
        System.out.println("Main controller created");
        System.out.println(contentPaneController);
        System.out.println(controlPaneController);
        System.out.println(menuPaneController);
    }
}
