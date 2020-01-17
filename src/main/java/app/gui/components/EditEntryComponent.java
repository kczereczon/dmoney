package app.gui.components;

import app.core.Dialogue;
import app.gui.controllers.EditEntryController;
import app.gui.controllers.EntriesListController;
import app.models.Entry;

public class EditEntryComponent extends Dialogue<EditEntryController> {

    public EditEntryComponent(Entry entry, EntriesListController entriesListController) {
        super("views/dialogues/editEntry.fxml", "Entry edit");

        dialogueController.setEntry(entry);
        dialogueController.setEntriesListController(entriesListController);
    }
}
