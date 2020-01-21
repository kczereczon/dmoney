package app.gui.controllers;

import app.DatabaseController;
import app.gui.Application;
import app.gui.components.PlanComponent;
import app.models.Subcategory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PlansController implements Initializable {
    @FXML
    public VBox creatorVBox;

    DatabaseController databaseController = Application.databaseController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Subcategory> subcategoryList = databaseController.subcategoryRepository.list();

        for (Subcategory subcategory :
            subcategoryList) {
            PlanComponent planComponent = new PlanComponent(subcategory);
            creatorVBox.getChildren().add(planComponent.component);
        }
    }
}
