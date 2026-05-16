package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.logic.dto.ProjectAttendantDto;
import com.sistemapracticasprofesional.presentation.util.Navigation;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrerProjectAttendantControllerGui {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrerProjectAttendantControllerGui.class);

    private Alert alert;
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

    @FXML
    public void handleBack(ActionEvent event) {
        try {
            Navigation.changeScene(event, "GuiCoordinatorMenu.fxml", "Menú del coordinador");
        } catch (IOException e) {
            LOGGER.error("Ruta no encontrada", e);
            showAlert(AlertType.ERROR, "No se pudo regresar al menú.");
        }
    }

    private void getregistrerProjectData(){
        projectAttendantDto.setProjectAttendantName(nameProjectAttendatTextField.getText());
        projectAttendantDto.setProjectAttendantPosition(null);
    }

    private void showAlert(Alert.AlertType type, String message) {
        alert = new Alert(type);
        alert.setTitle(null);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
