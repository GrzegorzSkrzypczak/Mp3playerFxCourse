package pl.javastart.mp3player.controller;

import javafx.scene.control.*;

public class MainController {
    public MenuItem fileMenuItem;
    public MenuItem dirMenuItem;
    public MenuItem closeMenuItem;
    public MenuItem aboutMenuItem;
    public TableView<?> contentTable;
    public Button previousButton;
    public ToggleButton playButton;
    public Button nextButton;
    public Slider volumeSlider;
    public Slider progressSlider;

    public void initialize () {
        System.out.println("MainController created");
    }
}
