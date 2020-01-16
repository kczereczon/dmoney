package app.gui.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.Console;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DialoguesController implements Initializable {

    //dialogues declaration
    public Pane successDialogue;
    public Pane failDialogue;

    public DialoguesController() {
        loadDialogues();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDialogues();
    }

    private void loadDialogues() {
        try {
            successDialogue = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("views/success.fxml")));
            failDialogue = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("views/fail.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDialogue(Pane dialogue) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Scene dialogScene = new Scene(dialogue);
        dialogScene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("views/style.css")).toExternalForm());
        dialog.setScene(dialogScene);
        dialog.show();
    }
}
