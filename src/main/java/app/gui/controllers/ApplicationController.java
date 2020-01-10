package app.gui.controllers;

import app.DatabaseController;
import app.models.Entry;
import app.models.Subcategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.hibernate.dialect.Database;

import javax.persistence.PostLoad;

public class ApplicationController {
    @FXML
    public Button statistics;
    @FXML
    public Button dashboard;
    @FXML
    public Button plans;
    @FXML
    public Button categories;
    @FXML
    public TextField quantity;
    @FXML
    public ComboBox<Subcategory> categoriesList;
    @FXML
    public TextField value;
    @FXML
    public ComboBox<String> direction;
    @FXML
    public TextField name;

    DatabaseController databaseController = new DatabaseController();

    public void onDashboardClick(MouseEvent mouseEvent) {

    }

    public void onStatisticsClick(MouseEvent mouseEvent) {
    }

    public void onPlansClick(MouseEvent mouseEvent) {
    }

    public void onCategoriesClick(MouseEvent mouseEvent) {
    }

    public void onAddEntryClick(MouseEvent mouseEvent) {
        Entry entry = new Entry(name.getText(),
            Float.parseFloat(value.getText()),
            Integer.parseInt(quantity.getText()),
            categoriesList.getValue(),
            false);

        Entry entry1 = databaseController.entryRepository.makePersistent(entry);

        if(entry1 != null) {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("This is a Dialog"));
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        }

        name.setText("");
        value.setText("");
        quantity.setText("");
        categoriesList.valueProperty().set(null);

    }

    public ApplicationController() {


    }

    @FXML
    private void initialize()
    {
        categoriesList.setConverter(new StringConverter<Subcategory>() {
            @Override
            public String toString(Subcategory object) {
                return "["+object.getCategory().getName().toUpperCase() + "]  "+ object.getName();
            }

            @Override
            public Subcategory fromString(String string) {
                return null;
            }
        });

        ObservableList<Subcategory> observableList = FXCollections.observableArrayList(databaseController.subcategoryRepository.list());
        categoriesList.setItems(observableList);
    }
}
