package app.gui.components;

import app.core.Dialogue;
import app.gui.controllers.EditCategoryController;
import javafx.scene.Parent;

public class EditCategoryComponent extends Dialogue<EditCategoryController, Parent> {
    public EditCategoryComponent() {
        super("views/dialogues/categoryEdit.fxml", "Edit category");
    }
}
