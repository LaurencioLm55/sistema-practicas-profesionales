package com.sistemapracticasprofesional.presentation.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigation {

    private static final String VIEW_BASE_PATH = "/com/sistemapracticasprofesional/presentation/views/";
    private static final String LOCAL_VIEW_BASE_PATH = "src/main/java/com/sistemapracticasprofesional/presentation/views/";

    public static void changeScene(ActionEvent event, String fxmlName, String title)
            throws IOException {

        URL resource = Navigation.class.getResource(VIEW_BASE_PATH + fxmlName);

        if (resource == null) {
            File fxmlFile = new File(LOCAL_VIEW_BASE_PATH + fxmlName);
            resource = fxmlFile.toURI().toURL();
        }

        FXMLLoader loader = new FXMLLoader(resource);
        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

}
