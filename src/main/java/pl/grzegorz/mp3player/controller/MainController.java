package pl.grzegorz.mp3player.controller;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import pl.grzegorz.mp3player.mp3.Mp3Song;
import pl.grzegorz.mp3player.mp3.player.Mp3Player;

import java.io.File;
import java.io.IOException;

public class MainController {
    @FXML
    ControlPaneController controlPaneController;
    @FXML
    MenuPaneController menuPaneController;
    @FXML
    private ContentPaneController contentPaneController;

    private Mp3Player player;

    public void initialize() {
        createPlayer();
        configureTableClick();
        configureButtons();
        addMp3Test();
    }

    private void createPlayer() {
        ObservableList<Mp3Song> items = contentPaneController.getContentTable().getItems();
        player = new Mp3Player(items);
    }

    private void configureTableClick() {
        TableView<Mp3Song> contentTable = contentPaneController.getContentTable();
        contentTable.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(event.getClickCount() == 2){
                int selectIndex = contentTable.getSelectionModel().getSelectedIndex();
                playSelectedSong(selectIndex);
            }
        });
    }

    public void playSelectedSong(int selectIndex){
        player.loadSong(selectIndex);
        configureProgressBar();
        configureVolume();
        controlPaneController.getPlayButton().setSelected(true);
    }

    private void configureProgressBar() {
        Slider progressSlider = controlPaneController.getProgressSlider();
        //ustawienie długości suwaka postępu
        player.getMediaPlayer().setOnReady(() -> progressSlider.setMax(player.getLoadedSongLenght()));
        //zmiana czasu w odtwarzaczu automatycznie będzie aktualizowała suwak
        player.getMediaPlayer().currentTimeProperty().addListener((arg, oldVal, newVal) ->
                progressSlider.setValue(newVal.toSeconds()));
        //przesunięcie suwaka spowoduje przewinięcie piosenki do wskazanego miejsca
        progressSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(progressSlider.isValueChanging()) {
                player.getMediaPlayer().seek(Duration.seconds(newValue.doubleValue()));
            }

        });
    }

    private void configureVolume() {
        Slider volumeSlider = controlPaneController.getVolumeSlider();;
        volumeSlider.valueProperty().unbind();
        volumeSlider.setMax(1.0);
        volumeSlider.valueProperty().bindBidirectional(player.getMediaPlayer().volumeProperty());
    }

    private void configureButtons(){
        TableView<Mp3Song> contentTable = contentPaneController.getContentTable();
        ToggleButton playButton = controlPaneController.getPlayButton();
        Button prevButton = controlPaneController.getPreviousButton();
        Button nextButton = controlPaneController.getNextButton();

        playButton.setOnAction(event -> {
            if(playButton.isSelected()) {
                player.play();
            } else {
                player.stop();
            }
        });

        nextButton.setOnAction(event -> {
            contentTable.getSelectionModel().select(contentTable.getSelectionModel().getSelectedIndex() -1);
            playSelectedSong(contentTable.getSelectionModel().getSelectedIndex());
        });
    }

    private void addMp3Test() {
        ObservableList<Mp3Song> items = contentPaneController.getContentTable().getItems();
        Mp3Song mp3SongFromFile = createMp3SongFromPath("Halo.mp3");
        items.add(mp3SongFromFile);
        items.add(mp3SongFromFile);
        items.add(mp3SongFromFile);
    }

    private Mp3Song createMp3SongFromPath(String path) {
        File file = new File(path);

        try {
            MP3File mp3File = new MP3File(file);
            String absolutePath = file.getAbsolutePath();
            String title = mp3File.getID3v2Tag().getSongTitle();
            String author = mp3File.getID3v2Tag().getLeadArtist();
            String album = mp3File.getID3v2Tag().getAlbumTitle();
            return new Mp3Song(title,author,album,absolutePath);

        } catch (IOException | TagException e) {
            e.printStackTrace();
            return null;
        }
    }
}
