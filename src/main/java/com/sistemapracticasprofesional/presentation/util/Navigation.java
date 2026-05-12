package com.sistemapracticasprofesional.presentation.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Window;
import javafx.stage.Stage;

public class Navigation {

    private static final String VIEW_BASE_PATH = "/com/sistemapracticasprofesional/presentation/views/";
    private static final String LOCAL_VIEW_BASE_PATH = "src/main/java/com/sistemapracticasprofesional/presentation/views/";

    public static void changeScene(ActionEvent event, String fxmlName, String title)
            throws IOException {

        URL resource = getViewUrl(fxmlName);

        FXMLLoader loader = new FXMLLoader(resource);
        Scene scene = new Scene(loader.load());

        Stage stage = getStageFromEvent(event);
        changeScene(stage, scene, title);
    }

    public static void changeScene(Stage stage, String fxmlName, String title)
            throws IOException {

        URL resource = getViewUrl(fxmlName);
        FXMLLoader loader = new FXMLLoader(resource);
        Scene scene = new Scene(loader.load());

        changeScene(stage, scene, title);
    }

    private static URL getViewUrl(String fxmlName) throws IOException {
        if (fxmlName == null || fxmlName.isBlank()) {
            throw new IOException("El nombre del archivo FXML no puede estar vacio");
        }

        URL resource = Navigation.class.getResource(VIEW_BASE_PATH + fxmlName);

        if (resource != null) {
            return resource;
        }

        File fxmlFile = new File(LOCAL_VIEW_BASE_PATH + fxmlName);

        if (fxmlFile.exists()) {
            return fxmlFile.toURI().toURL();
        }

        throw new IOException("No se encontro el archivo FXML: " + fxmlName);
    }

    private static void changeScene(Stage stage, Scene scene, String title) {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    private static Stage getStageFromEvent(ActionEvent event) throws IOException {
        Object source = event.getSource();

        if (source instanceof MenuItem menuItem) {
            Window window = menuItem.getParentPopup().getOwnerWindow();
            return (Stage) window;
        }

        if (source instanceof Node node) {
            return (Stage) node.getScene().getWindow();
        }

        throw new IOException("No se pudo obtener la ventana desde el evento");
    }

}
