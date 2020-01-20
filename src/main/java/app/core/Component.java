package app.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.IOException;

public abstract class Component<T, M extends Node> {

    public M component;
    public T componentController;
    public String title;

    public Component(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(path));
            component =  loader.load();
            componentController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
