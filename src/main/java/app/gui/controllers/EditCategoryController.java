package app.gui.controllers;

import app.DatabaseController;
import app.core.Controller;
import app.gui.Application;
import app.models.Category;
import app.models.Entry;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

public class EditCategoryController extends Controller implements Initializable {

    @FXML
    public Button saveButton;
    @FXML
    public Button removeButton;
    @FXML
    public TextField nameTextField;

    DatabaseController databaseController = Application.databaseController;

    @Setter
    CategoriesController categoriesController;

    private Category category;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onSaveButtonClicked(MouseEvent mouseEvent) {
        category.setName(nameTextField.getText());

        databaseController.categoryRepository.update(category);
        categoriesController.resetCategoryTableItems();

        stage.close();
    }

    public void onRemoveButtonClicked(MouseEvent mouseEvent) {
        databaseController.categoryRepository.remove(category);
        categoriesController.resetCategoryTableItems();
        stage.close();
    }

    public void setCategory(Category category) {
        this.category = category;

        nameTextField.setText(category.getName());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
