package com.sistemapracticasprofesional.logic.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NavigationServices {

    private String resourcePath;
    private String namePath;
    private String title;

    public NavigationServices(String resourscePath, String namePath, String title){
    
        this.resourcePath = resourscePath;
        this.namePath = namePath;
        this.title = title;
        
    }

    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getCourseViewUrl());

        Scene scene = new Scene(loader.load());
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();

    }

    private URL getCourseViewUrl() throws IOException {

        URL resource = getClass().getResource( this.namePath );

        if (resource != null) {
            return resource;
        }

        File fxmlFile = new File( this.resourcePath );

        return fxmlFile.toURI().toURL();
    }

}
