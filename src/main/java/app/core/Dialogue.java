package app.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public abstract class Dialogue<T extends Controller, M extends Node> extends Component<T, M> {

    public String title;

    public Dialogue(String path, String title) {
        super(path);
        this.title = title;
    }

    public void showDialogue() {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle(title);
        Scene dialogScene = new Scene((Parent) component);
        dialogScene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("views/style.css")).toExternalForm());
        dialog.setScene(dialogScene);
        dialog.show();

        componentController.setStage(dialog);
    }
}
