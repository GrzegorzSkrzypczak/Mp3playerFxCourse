package pl.grzegorz.mp3player.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.grzegorz.mp3player.mp3.Mp3Song;

public class ContentPaneController {

    private static final String TITLE_COLUMN = "Tytuł";
    private static final String AUTHOR_COLUMN = "Autor";
    private static final String ALBUM_COLUMN = "Album";

    @FXML
    private TableView<Mp3Song> contentTable;

    public void initialize() {
        configureTableColumns();
        createTestData();
    }

    private void createTestData() {
        TableColumn<Mp3Song, String> titleColumn = new TableColumn<>(TITLE_COLUMN);
        titleColumn.setCellValueFactory(new PropertyValueFactory<Mp3Song, String>("title"));

        TableColumn<Mp3Song, String> authorColumn =  new TableColumn<>(AUTHOR_COLUMN);
        authorColumn.setCellValueFactory(new PropertyValueFactory<Mp3Song, String>("author"));

        TableColumn<Mp3Song, String> albumColumn = new TableColumn<>(ALBUM_COLUMN);
        albumColumn.setCellValueFactory(new PropertyValueFactory<Mp3Song, String>("album"));

        contentTable.getColumns().add(titleColumn);
        contentTable.getColumns().add(authorColumn);
        contentTable.getColumns().add(albumColumn);
    }

    private void configureTableColumns() {
        ObservableList<Mp3Song> items = contentTable.getItems();

        items.add(new Mp3Song("a","a","a","a"));
        items.add(new Mp3Song("b","b","b","b"));
        items.add(new Mp3Song("c","c","c","c"));
        items.add(new Mp3Song("d","d","d","d"));
    }
}
