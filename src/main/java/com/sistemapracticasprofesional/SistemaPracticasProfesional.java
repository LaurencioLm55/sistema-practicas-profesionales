package com.sistemapracticasprofesional;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SistemaPracticasProfesional extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/sistemapracticasprofesional/presentation/views/GuiUserLogin.fxml")
        );

        Scene scene = new Scene(loader.load());
        stage.setTitle("Course Registration");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
