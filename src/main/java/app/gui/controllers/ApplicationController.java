package app.gui.controllers;

import app.DatabaseController;
import app.models.Entry;
import app.models.Subcategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.hibernate.dialect.Database;

import javax.persistence.PostLoad;
import java.io.IOException;
import java.util.Collection;

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
    @FXML
    public BorderPane borderPane;

    double pageWidth;
    double pageHeight;

    DatabaseController databaseController = new DatabaseController();

    public void onDashboardClick(MouseEvent mouseEvent) {
       clearActivatedButtons();
       dashboard.getStyleClass().add("button-active");
       loadPage("dashboard");
    }

    public void onStatisticsClick(MouseEvent mouseEvent) {
        clearActivatedButtons();
        statistics.getStyleClass().add("button-active");
        loadPage("statistics");
    }

    public void onPlansClick(MouseEvent mouseEvent) {
        clearActivatedButtons();
        plans.getStyleClass().add("button-active");
        loadPage("plans");
    }

    public void onCategoriesClick(MouseEvent mouseEvent) {
        clearActivatedButtons();
        categories.getStyleClass().add("button-active");
        loadPage("categories");
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
        loadPage("dashboard");

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

    private void clearActivatedButtons() {
        statistics.getStyleClass().remove("button-active");
        dashboard.getStyleClass().remove("button-active");
        plans.getStyleClass().remove("button-active");
        categories.getStyleClass().remove("button-active");
    }

    private void loadPage(String name) {
        try {
            Node node;
            node = (Node)FXMLLoader.load(getClass().getClassLoader().getResource("views/"+name+".fxml"));
            borderPane.setCenter(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
