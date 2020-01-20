package app.gui.components;

import app.core.Component;
import app.gui.controllers.EntriesListController;
import app.models.Entry;
import javafx.scene.control.TableView;

public class EntryListComponent extends Component<EntriesListController, TableView<Entry>> {
    public EntryListComponent() {
        super("views/components/entriesList.fxml");

    }
}
