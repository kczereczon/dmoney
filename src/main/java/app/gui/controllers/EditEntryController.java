package app.gui.controllers;

import app.DatabaseController;
import app.core.Controller;
import app.gui.Application;
import app.models.Entry;
import app.models.Subcategory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

public class EditEntryController extends Controller implements Initializable{
    @FXML
    public TextField nameTextField;
    @FXML
    public TextField quantityTextField;
    @FXML
    public TextField valueTextField;
    @FXML
    public ComboBox<Subcategory> categoryComboBox;
    @FXML
    public ComboBox<String> directionComboBox;
    @FXML
    public Button removeButton;
    @FXML
    public Button saveButton;

    private Entry entry;
    private DatabaseController databaseController = Application.databaseController;

    @Setter
    private EntriesListController entriesListController;
    @Setter
    private DashboardController dashboardController;

    private Stage stage;

    public void onRemoveButtonClicked(MouseEvent mouseEvent) {
        databaseController.entryRepository.remove(entry);
        dashboardController.resetTables();

        stage.close();
    }

    public void onSaveButtonClicked(MouseEvent mouseEvent) {
        entry.setName(nameTextField.getText());
        entry.setSubcategory(categoryComboBox.getValue());
        entry.setQuantity(Integer.parseInt(quantityTextField.getText()));
        entry.setValue(Float.parseFloat(valueTextField.getText()));
        entry.setIsIncome(directionComboBox.getValue().equals("In"));

        databaseController.entryRepository.update(entry);
        dashboardController.resetTables();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Subcategory object) {
                return "[" + object.getCategory().getName().toUpperCase() + "]  " + object.getName();
            }

            @Override
            public Subcategory fromString(String string) {
                return null;
            }
        });

        directionComboBox.setItems(FXCollections.observableArrayList("In", "Out"));
        directionComboBox.setValue("Out");
    }

    public void setEntry(Entry entry) {
        this.entry = entry;

        nameTextField.setText(entry.getName());
        categoryComboBox.setValue(entry.getSubcategory());
        quantityTextField.setText(entry.getQuantity().toString());
        valueTextField.setText(entry.getValue().toString());
        directionComboBox.setValue(entry.getDirection());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
