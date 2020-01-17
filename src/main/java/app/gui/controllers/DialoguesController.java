package app.gui.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DialoguesController implements Initializable {

    //dialogues declaration
    public Node successDialogue;
    public Node failDialogue;
    public Node editEntryDialogue;

    public DialoguesController() {
        loadDialogues();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDialogues();
    }

    private void loadDialogues() {
        try {
            successDialogue = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("views/dialogues/success.fxml")));
            failDialogue = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("views/dialogues/fail.fxml")));
            editEntryDialogue = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("views/dialogues/editEntry.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDialogue(Node dialogue) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Scene dialogScene = new Scene((Parent) dialogue);
        dialogScene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("views/style.css")).toExternalForm());
        dialog.setScene(dialogScene);
        dialog.show();
    }
}
