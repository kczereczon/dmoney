package app.gui.controllers;

import app.DatabaseController;
import app.core.Controller;
import app.gui.Application;
import app.models.Category;
import app.models.Subcategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class EditSubcategoryController extends Controller implements Initializable {

    @FXML
    public Button saveButton;
    @FXML
    public Button removeButton;
    @FXML
    public TextField nameTextField;
    @FXML
    public ComboBox<Category> categoryComboBox;

    DatabaseController databaseController = Application.databaseController;

    @Setter
    CategoriesController categoriesController;

    private Subcategory subcategory;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Category> observableList = FXCollections.observableArrayList(
            databaseController.categoryRepository.list());

        categoryComboBox.setItems(observableList);

        categoryComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Category object) {
                return object.getName();
            }

            @Override
            public Category fromString(String string) {
                return null;
            }
        });
    }

    public void onSaveButtonClicked(MouseEvent mouseEvent) {
        subcategory.setName(nameTextField.getText());
        subcategory.setCategory(categoryComboBox.getValue());

        databaseController.subcategoryRepository.update(subcategory);
        categoriesController.resetSubcategoryTableItems();

        stage.close();
    }

    public void onRemoveButtonClicked(MouseEvent mouseEvent) {
        databaseController.subcategoryRepository.remove(subcategory);
        categoriesController.resetSubcategoryTableItems();

        stage.close();
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;

        nameTextField.setText(subcategory.getName());
        categoryComboBox.setValue(subcategory.getCategory());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
