package app.gui.controllers;

import app.gui.components.EditEntryComponent;
import app.models.Entry;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class EntriesListController implements Initializable {

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
    public TableColumn<Entry, String> createdAtColumn;
    @FXML
    public TableView<Entry> entries;
    @FXML
    public TableColumn<Entry, String> directionColumn;

    DialoguesController dialoguesController = new DialoguesController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindColumns();

        entries.setOnMousePressed(e -> {
            if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
                EditEntryComponent editEntryComponent = new EditEntryComponent(
                    entries.getSelectionModel().getSelectedItem(),
                    this);
                editEntryComponent.showDialogue();
            }
        });
    }

    public void bindColumns() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("subcategoryName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        directionColumn.setCellValueFactory(new PropertyValueFactory<>("direction"));
        createdAtColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
    }

    public void setEntries(ObservableList<Entry> list) {
        entries.setItems(list);
    }


}
