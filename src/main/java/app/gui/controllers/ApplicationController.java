package app.gui.controllers;

import app.DatabaseController;
import app.gui.Application;
import app.models.Entry;
import app.models.Subcategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import java.io.IOException;
import java.util.Objects;

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

    public String currentStage;

    public static DashboardController dashboardController;
    public DialoguesController dialoguesController = new DialoguesController();


    DatabaseController databaseController = Application.databaseController;

    public void onDashboardClick() {
       clearActivatedButtons();
       dashboard.getStyleClass().add("button-active");
       loadPage("dashboard");
    }

    public void onStatisticsClick() {
        clearActivatedButtons();
        statistics.getStyleClass().add("button-active");
        loadPage("statistics");
    }

    public void onPlansClick() {
        clearActivatedButtons();
        plans.getStyleClass().add("button-active");
        loadPage("plans");
    }

    public void onCategoriesClick() {
        clearActivatedButtons();
        categories.getStyleClass().add("button-active");
        loadPage("categories");
    }

    public void onAddEntryClick() {
        try {
            Entry entry = new Entry(name.getText(),
                Float.parseFloat(value.getText()),
                Integer.parseInt(quantity.getText()),
                categoriesList.getValue(),
                direction.getValue().equals("In")
            );

            databaseController.entryRepository.makePersistent(entry);
            dialoguesController.showDialogue(dialoguesController.successDialogue);
        } catch (Exception exception) {
            dialoguesController.showDialogue(dialoguesController.failDialogue);
        }


        name.setText("");
        value.setText("");
        quantity.setText("");
        categoriesList.valueProperty().set(null);

        if(dashboardController != null) {
            dashboardController.setTotalTable();
            dashboardController.setTodayTable();
        }

        if(!currentStage.equals("dashboard")) {
            loadPage("dashboard");
        }
    }

    public ApplicationController() {


    }

    @FXML
    private void initialize()
    {
        loadPage("dashboard");

        categoriesList.setConverter(new StringConverter<>() {
            @Override
            public String toString(Subcategory object) {
                return "[" + object.getCategory().getName().toUpperCase() + "]  " + object.getName();
            }

            @Override
            public Subcategory fromString(String string) {
                return null;
            }
        });

        direction.setItems(FXCollections.observableArrayList("In", "Out"));
        direction.setValue("Out");

        setCategoriesListItems();
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
            node = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("views/screens/" + name + ".fxml")));
            borderPane.setCenter(node);
            currentStage = name;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void onCategoriesListClicked() {
        setCategoriesListItems();
    }

    public void setCategoriesListItems() {
        ObservableList<Subcategory> observableList = FXCollections.observableArrayList(
            databaseController.subcategoryRepository.list());
        categoriesList.setItems(observableList);
    }
}
