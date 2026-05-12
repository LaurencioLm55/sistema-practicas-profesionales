package com.sistemapracticasprofesional.presentation.controllersGui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InternsMenuController {

    @FXML
    private Label welcomeLabel;
    
    @FXML
    public void handleRegisterIntern(){

    }

    @FXML
    public void handleConsultInterns(){

    }

    @FXML
    public void handleBack (){

    }

    public void setWelcomeLabel( String userName ){

        welcomeLabel.setText( "¡Bienvenido " + userName + "!" );

    }
}
