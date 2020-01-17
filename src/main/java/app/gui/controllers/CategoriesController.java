package app.gui.controllers;

import app.DatabaseController;
import app.gui.Application;
import app.models.Category;
import app.models.Subcategory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoriesController implements Initializable {

    @FXML
    public TextField categoryNameTextField;
    @FXML
    public Button addCategoryButton;
    @FXML
    public TableView<Category> categoriesTable;
    @FXML
    public TableColumn<Category, String> categoryNameColumn;
    @FXML
    public TableColumn<Category, String> categoryCreatedAtColumn;
    @FXML
    public TableColumn<Category, String> categoryUpdatedAtColumn;
    @FXML
    public TableColumn<Category, String> categoryTotalSpendColumn;
    @FXML
    public TextField subcategoryNameTextField;
    @FXML
    public ComboBox<Category> categorySelect;
    @FXML
    public Button addSubcategoryButton;
    @FXML
    public TableView<Subcategory> subcategoryTable;
    @FXML
    public TableColumn<Subcategory, String> subcategoryNameColumn;
    @FXML
    public TableColumn<Subcategory, String> subcategoryCategoryColumn;
    @FXML
    public TableColumn<Subcategory, String> subcategoryCreatedAtColumn;
    @FXML
    public TableColumn<Subcategory, String> subcategoryUpdatedAtColumn;
    @FXML
    public TableColumn<Subcategory, String> subcategoryTotalSpendColumn;

    DatabaseController databaseController = Application.databaseController;
    DialoguesController dialoguesController = new DialoguesController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categorySelect.setConverter(new StringConverter<>() {
            @Override
            public String toString(Category object) {
                return object.getName();
            }

            @Override
            public Category fromString(String string) {
                return null;
            }
        });

        //add categories to list
        setCategorySelectItems();

        setCategoryTable();
    }

    public void setCategorySelectItems() {
        ObservableList<Category> observableList = FXCollections.observableArrayList(
            databaseController.categoryRepository.list());
        categorySelect.setItems(observableList);
    }

    public void onAddCategoryButtonClicked() {
        String name = categoryNameTextField.getText();

        Category category = new Category(name);

        try {
            databaseController.categoryRepository.makePersistent(category);
            setCategoryTable();
            setCategorySelectItems();
            clearCategoryForm();
            dialoguesController.showDialogue(dialoguesController.successDialogue);
        } catch (Exception e) {
            dialoguesController.showDialogue(dialoguesController.failDialogue);
        }
    }

    public void onAddSubcategoryButtonClicked() {
        String name = subcategoryNameTextField.getText();
        Category category = categorySelect.getValue();

        Subcategory subcategory = new Subcategory(name, category);

        try {
            databaseController.subcategoryRepository.makePersistent(subcategory);
            setSubcategoryTable();
            clearSubcategoryForm();
            dialoguesController.showDialogue(dialoguesController.successDialogue);
        } catch (Exception e) {
            dialoguesController.showDialogue(dialoguesController.failDialogue);
        }
    }

    public void clearSubcategoryForm() {
        subcategoryNameTextField.setText("");
        categorySelect.valueProperty().set(null);
    }

    public void clearCategoryForm() {
        categoryNameTextField.setText("");
    }

    private void setCategoryTable() {
        categoriesTable.setItems(FXCollections.observableArrayList(
            databaseController.categoryRepository.list()));

        categoryNameColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getName()));
        categoryCreatedAtColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getCreatedAt()));
        categoryUpdatedAtColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getUpdatedAt()));
        categoryTotalSpendColumn.setCellValueFactory(c-> new SimpleStringProperty(String.format("%.2f", c.getValue().getTotal())));
    }

    private void setSubcategoryTable() {
        subcategoryTable.setItems(FXCollections.observableArrayList(
            databaseController.subcategoryRepository.list()));

        subcategoryNameColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getName()));
        subcategoryCreatedAtColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getCreatedAt()));
        subcategoryCategoryColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getCategory().getName()));
        subcategoryUpdatedAtColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getUpdatedAt()));
        subcategoryTotalSpendColumn.setCellValueFactory(c-> new SimpleStringProperty(String.format("%.2f", c.getValue().getTotal())));
    }

    public void onCategoryChanged() {
        setCategoryTable();
    }

    public void onSubcategoryChanged() {
        setSubcategoryTable();
    }
}
