package app.gui.controllers;

import app.DatabaseController;
import app.models.Entry;
import app.models.Subcategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public TableView<Entry> entriesList;

    public TableColumn<Entry, String> nameColumn;
    public TableColumn<Entry, String> categoryColumn;
    public TableColumn<Entry, String> quantityColumn;
    public TableColumn<Entry, String> totalColumn;
    public TableColumn<Entry, String> priceColumn;

    private DatabaseController databaseController = new DatabaseController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindColumns();

        ObservableList<Entry> observableList = FXCollections.observableArrayList(databaseController.entryRepository.list());

        entriesList.setItems(observableList);
    }

    public void bindColumns() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Entry, String>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Entry, String>("subcategoryName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Entry, String>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Entry, String>("value"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<Entry, String>("total"));
    }
}
