package app.gui.components;

import app.core.Component;
import app.gui.controllers.PlanController;
import app.models.Subcategory;
import javafx.scene.layout.GridPane;

public class PlanComponent extends Component<PlanController, GridPane> {
    public PlanComponent(Subcategory subcategory) {
        super("views/components/plan.fxml");

        this.componentController.setSubcategory(subcategory);
    }
}
