package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.logic.dto.ProjectAttendantDto;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegistrerProjectAttendantControllerGui {

    private ProjectAttendantDto projectAttendantDto = new ProjectAttendantDto();
    
    @FXML
    TextField nameProjectAttendatTextField;

    @FXML
    TextField projectAttendantPositionTextField;

    @FXML
    TextField emailProjectAttendantTextField;

    @FXML
    public void handelRegistrerProjectAttendantButton () {
        
    }

    private void getregistrerProjectData(){
        projectAttendantDto.setProjectAttendantName(nameProjectAttendatTextField.getText());
        projectAttendantDto.setProjectAttendantPosition(null);
    }

    
}
