package app.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/application.fxml"));
        BorderPane root = (BorderPane) loader.load();
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getClassLoader().getResource("views/style.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }
}
