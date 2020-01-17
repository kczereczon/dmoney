package app.gui.controllers;

import app.DatabaseController;
import app.gui.Application;
import app.models.Entry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    public VBox allEntriesVBox;
    @FXML
    public VBox todayEntriesVBox;

    public TableView<Entry> allEntries;
    public TableView<Entry> todayEntries;

    private DatabaseController databaseController = Application.databaseController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //need to give access to this controller to application controller
        //for refreshing entries list
        ApplicationController.dashboardController = this;

        allEntries = loadTableViewComponent();
        todayEntries = loadTableViewComponent();

        allEntriesVBox.getChildren().setAll(allEntries);
        todayEntriesVBox.getChildren().setAll(todayEntries);

        setTodayTable();
        setTotalTable();
    }

    public void setTodayTable() {
        ObservableList<Entry> observableList = FXCollections.observableArrayList(
            databaseController.entryRepository.listToday());
        todayEntries.setItems(observableList);
    }

    public void setTotalTable() {
        ObservableList<Entry> observableList = FXCollections.observableArrayList(databaseController.entryRepository.list());
        allEntries.setItems(observableList);
    }

    public TableView<Entry> loadTableViewComponent() {
        try {
            TableView<Entry> tableView;
            tableView = FXMLLoader.load(
                Objects.requireNonNull(
                    getClass().getClassLoader().getResource("views/components/entriesList.fxml")
                )
            );

            return tableView;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
