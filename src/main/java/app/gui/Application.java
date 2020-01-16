package app.gui;

import app.DatabaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Objects;

public class Application extends javafx.application.Application {

    public static void main(String[] args) {
        launch();
    }

    public static DatabaseController databaseController;

    @Override
    public void start(Stage stage) throws Exception {
        databaseController = new DatabaseController();

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/application.fxml"));
        BorderPane root = loader.load();
        Scene scene = new Scene(root);

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("views/style.css")).toExternalForm());

        stage.setScene(scene);
        stage.show();
    }
}
