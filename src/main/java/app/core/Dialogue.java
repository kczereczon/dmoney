package app.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public abstract class Dialogue<T extends Controller> {

    public Parent dialogue;
    public T dialogueController;
    public String title;

    public Dialogue(String path, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(path));
            dialogue =  loader.load();
            dialogueController = loader.getController();
            this.title = title;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDialogue() {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle(title);
        Scene dialogScene = new Scene(dialogue);
        dialogScene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("views/style.css")).toExternalForm());
        dialog.setScene(dialogScene);
        dialog.show();

        dialogueController.setStage(dialog);
    }
}
