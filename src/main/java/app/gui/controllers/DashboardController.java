package app.gui.controllers;

import app.DatabaseController;
import app.gui.Application;
import app.gui.components.EntryListComponent;
import app.models.Entry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    public VBox allEntriesVBox;
    @FXML
    public VBox todayEntriesVBox;

    public EntryListComponent allEntriesComponent;
    public EntryListComponent todayEntriesComponent;

    private DatabaseController databaseController = Application.databaseController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //need to give access to this controller to application controller
        //for refreshing entries list
        ApplicationController.dashboardController = this;

        allEntriesComponent = new EntryListComponent();
        todayEntriesComponent = new EntryListComponent();


        //setting up communication between controllers
        allEntriesComponent.componentController.setDashboardController(this);
        todayEntriesComponent.componentController.setDashboardController(this);

        //adding fxml view to gui
        allEntriesVBox.getChildren().setAll(allEntriesComponent.component);
        todayEntriesVBox.getChildren().setAll(todayEntriesComponent.component);

        setTodayTable();
        setTotalTable();
    }

    public void setTodayTable() {
        ObservableList<Entry> observableList = FXCollections.observableArrayList(
            databaseController.entryRepository.listToday());
        todayEntriesComponent.component.setItems(observableList);
    }

    public void setTotalTable() {
        ObservableList<Entry> observableList = FXCollections.observableArrayList(databaseController.entryRepository.list());
        allEntriesComponent.component.setItems(observableList);
    }

    public void resetTables() {
        allEntriesComponent.component.getItems().clear();
        todayEntriesComponent.component.getItems().clear();
        setTotalTable();
        setTodayTable();
    }
}
