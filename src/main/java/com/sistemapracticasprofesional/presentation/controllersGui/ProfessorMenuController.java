package com.sistemapracticasprofesional.presentation.controllersGui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProfessorMenuController {

    @FXML
    private Label welcomeLabel;
    
    @FXML
    public void handleGradeReport(){

    }

    @FXML
    public void handleReportGradeForm () {

    }

    @FXML
    public void handleLogout () {
        
    }

    public void setWelcomeLabel( String userName ){

        welcomeLabel.setText( "¡Bienvenido " + userName + "!" );

    }
}
