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

        Timestamp startDay = new Timestamp(atStartOfDay(new Date(System.currentTimeMillis())));
        Timestamp endDay = new Timestamp(atEndOfDay(new Date(System.currentTimeMillis())));

        ObservableList<Entry> observableList = FXCollections.observableArrayList(
            databaseController.entryRepository.list(new Criterion[]{Restrictions.between("createdAt",
                startDay,
                endDay
            )})
        );
        todayEntries.setItems(observableList);
    }

    public void setTotalTable() {
        ObservableList<Entry> observableList = FXCollections.observableArrayList(databaseController.entryRepository.list());
        allEntries.setItems(observableList);
    }

    public long atEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }

    public long atStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public TableView<Entry> loadTableViewComponent() {
        try {
            TableView<Entry> tableView;
            tableView = FXMLLoader.load(
                Objects.requireNonNull(
                    getClass().getClassLoader().getResource("views/entriesList.fxml")
                )
            );

            return tableView;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
