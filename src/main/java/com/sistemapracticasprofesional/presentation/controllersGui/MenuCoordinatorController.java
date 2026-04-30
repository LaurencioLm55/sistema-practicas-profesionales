package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.presentation.util.Navigation;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuCoordinatorController {
    
    @FXML
    private void handleRegisterCourse(ActionEvent event) {
        try {
            Navigation.changeScene(
                    event,
                    "GuiRegistrerCourses.fxml",
                    "Registrar experiencia educativa"
            );
        } catch (IOException e) {
           
        }
    }
}

