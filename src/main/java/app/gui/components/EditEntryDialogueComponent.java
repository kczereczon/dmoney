package app.gui.components;

import app.core.Dialogue;
import app.gui.controllers.EditEntryController;
import app.gui.controllers.EntriesListController;
import app.models.Entry;
import javafx.scene.Parent;

public class EditEntryDialogueComponent extends Dialogue<EditEntryController, Parent> {

    public EditEntryDialogueComponent(Entry entry, EntriesListController entriesListController) {
        super("views/dialogues/editEntry.fxml", "Entry edit");

        componentController.setEntry(entry);
        componentController.setEntriesListController(entriesListController);
    }
}
