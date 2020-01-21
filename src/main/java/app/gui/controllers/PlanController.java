package app.gui.controllers;

import app.models.Category;
import app.models.Plan;
import app.models.Subcategory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class PlanController implements Initializable {

    @FXML
    public TextArea descriptionTextArea;
    @FXML
    public Label categoryLabel;
    @FXML
    public TextField plannedValueTextField;
    @FXML
    public Label subcategoryLabel;
    @FXML
    public Button saveButton;

    public Plan plan;
    public Subcategory subcategory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onSaveButtonClick(MouseEvent mouseEvent) {
    }

    public void setPlan(Plan plan) {
        this.plan = plan;

        descriptionTextArea.setText(plan.getDescription());
        plannedValueTextField.setText(plan.getValue().toString());
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;

        categoryLabel.setText(subcategory.getCategory().getName());
        subcategoryLabel.setText(subcategory.getName());
    }
}
