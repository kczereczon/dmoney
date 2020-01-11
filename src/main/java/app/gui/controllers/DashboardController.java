package app.gui.controllers;

import app.DatabaseController;
import app.models.Entry;
import app.models.Subcategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public TableView<Entry> entriesList;

    private DatabaseController databaseController = new DatabaseController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Entry> observableList = FXCollections.observableArrayList(databaseController.entryRepository.list());

        entriesList.setItems(observableList);
    }
}
