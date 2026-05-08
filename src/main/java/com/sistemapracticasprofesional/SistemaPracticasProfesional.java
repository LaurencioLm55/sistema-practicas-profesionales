package com.sistemapracticasprofesional;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.sistemapracticasprofesional.presentation.controllersGui.MenuCoordinatorController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SistemaPracticasProfesional extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getCourseViewUrl());   

        Scene scene = new Scene(loader.load());
        stage.setTitle("Inicio de sesion");
        stage.setScene(scene);
        stage.show();

    }

    private URL getCourseViewUrl() throws IOException {
        URL resource = getClass().getResource(
                "/com/sistemapracticasprofesional/presentation/views/GuiCoordinatorMenu.fxml"
        );

        if (resource != null) {
            return resource;
        }

        File fxmlFile = new File(
                "src/main/java/com/sistemapracticasprofesional/presentation/viewsGuiCoordinatorMenu.fxml"
        );

        return fxmlFile.toURI().toURL();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
