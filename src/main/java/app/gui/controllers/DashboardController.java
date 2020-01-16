package app.gui.controllers;

import app.DatabaseController;
import app.gui.Application;
import app.models.Entry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    public TableColumn<Entry, String> nameColumn;
    @FXML
    public TableColumn<Entry, String> categoryColumn;
    @FXML
    public TableColumn<Entry, String> quantityColumn;
    @FXML
    public TableColumn<Entry, String> totalColumn;
    @FXML
    public TableColumn<Entry, String> priceColumn;
    @FXML
    public TableColumn<Entry, String> totalColumn2;
    @FXML
    public TableColumn<Entry, String> priceColumn2;
    @FXML
    public TableColumn<Entry, String> quantityColumn2;
    @FXML
    public TableColumn<Entry, String> categoryColumn2;
    @FXML
    public TableColumn<Entry, String> nameColumn2;
    @FXML
    public TableView<Entry> todayEntries;
    @FXML
    public TableView<Entry> totalEntries;

    private DatabaseController databaseController = Application.databaseController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindColumns();

        setTodayTable();
        setTotalTable();
    }

    public void bindColumns() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("subcategoryName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        nameColumn2.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn2.setCellValueFactory(new PropertyValueFactory<>("subcategoryName"));
        quantityColumn2.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn2.setCellValueFactory(new PropertyValueFactory<>("value"));
        totalColumn2.setCellValueFactory(new PropertyValueFactory<>("total"));
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
        totalEntries.setItems(observableList);
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
}
