package app.gui.components;

import app.core.Dialogue;
import app.gui.controllers.EditCategoryController;
import app.gui.controllers.EditSubcategoryController;
import app.models.Subcategory;
import javafx.scene.Parent;

public class EditSubcategoryComponent extends Dialogue<EditSubcategoryController, Parent> {
    public EditSubcategoryComponent() {
        super("views/dialogues/subcategoryEdit.fxml", "Edit subcategory");
    }
}
