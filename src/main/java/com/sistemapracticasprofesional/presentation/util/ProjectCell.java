package com.sistemapracticasprofesional.presentation.util;

import java.io.IOException;

import com.sistemapracticasprofesional.logic.dto.InternDto;
import com.sistemapracticasprofesional.logic.dto.ProjectDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.presentation.controllersGui.UserLoginControllerGui;
import com.sistemapracticasprofesional.logic.controllers.AssignProjectController;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class ProjectCell extends ListCell<ProjectDto>{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginControllerGui.class);
    private final Button button = new Button("Asignar");
    private final Label label = new Label();
    private final HBox hbox = new HBox(10, label, button);
    private final AssignProjectController assignProjectController;
    private final InternDto internDto;

    public ProjectCell(AssignProjectController assignProjectController, InternDto internDto) {
        this.assignProjectController = assignProjectController;
        this.internDto = internDto;
    }

    protected void updateItem(ProjectDto item, boolean empty) {
        super.updateItem(item, empty);
                    
        if (empty || item == null) {

            setGraphic(null);
        
        }else {

            label.setText(item.getProjectName());
            button.setOnAction(e -> handleAssign(e, item));
            setGraphic(hbox);

        }

    }

    private void handleAssign(ActionEvent e, ProjectDto item) {
        try {

            int result = assignProjectController.assingProject(internDto.getStudentId(), item.getProjectId());

            if (result == 1) {

                showAlert(AlertType.CONFIRMATION, "Se asigno el proyecto correctamente");

                try {

                    Navigation.changeScene(e, "GuiListOfInters.fxml", "Menú del coordinador");
                
                } catch (IOException ex) {

                    LOGGER.error("Ruta no encontrada", ex);
                    showAlert(Alert.AlertType.ERROR, "No se pudo volver al menú anterior.");
                
                }

            } else {

                if (result == 0) {

                    showAlert(AlertType.INFORMATION, "El proyecto no tiene cupo");

                } else {

                    showAlert(AlertType.ERROR, "Erro al asignar el proyecto");

                }
            }
            
        } catch (BusinessLogicException ex) {

            showAlert(AlertType.ERROR, ex.getMessage());

        }

    }

    private void showAlert(Alert.AlertType type, String message) {

        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.showAndWait();

    }


    

}
