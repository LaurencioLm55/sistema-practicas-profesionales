package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.logic.controllers.ProjectRegisterController;
import com.sistemapracticasprofesional.logic.dto.ProjectDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.presentation.util.Navigation;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RegisterProjectController {

    @FXML private TextField projectIdField;
    @FXML private TextField organizationIdField;
    @FXML private TextField projectNameField;
    @FXML private TextArea descriptionArea;
    @FXML private TextArea generalObjectivesArea;
    @FXML private TextArea methodologyArea;
    @FXML private TextArea resourcesArea;
    @FXML private TextField attendantNameField;
    @FXML private TextField attendantEmailField;
    @FXML private TextField attendantPositionField;

    private final ProjectRegisterController projectRegisterController = new ProjectRegisterController();

    @FXML
    private void handleRegisterProject() {
        try {
            ProjectDto projectDto = createProjectDtoFromFields();
            projectRegisterController.registerProject(projectDto);
            showAlert(Alert.AlertType.INFORMATION, "Proyecto registrado correctamente.");
            clearFields();
        } catch (BusinessLogicException | NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error al registrar el proyecto: " + e.getMessage());
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        try {
            Navigation.changeScene(event, "GuiCoordinatorMenu.fxml", "Menú del Coordinador");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "No se pudo volver al menú anterior.");
        }
    }

    private ProjectDto createProjectDtoFromFields() {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectId(Integer.parseInt(projectIdField.getText()));
        projectDto.setLinkedOrganizationId(Integer.parseInt(organizationIdField.getText()));
        projectDto.setProjectName(projectNameField.getText());
        projectDto.setProjectDescription(descriptionArea.getText());
        projectDto.setProjectMethodology(methodologyArea.getText());
        projectDto.setProjectResources(resourcesArea.getText());
        projectDto.setGeneralProjectObjectives(generalObjectivesArea.getText());
        projectDto.setProjectAttendantName(attendantNameField.getText());
        projectDto.setProjectAttendantEmail(attendantEmailField.getText());
        projectDto.setProjectAttendantPosition(attendantPositionField.getText());

        return projectDto;
    }

    private void clearFields() {
        projectIdField.clear();
        organizationIdField.clear();
        projectNameField.clear();
        descriptionArea.clear();
        generalObjectivesArea.clear();
        methodologyArea.clear();
        resourcesArea.clear();
        attendantNameField.clear();
        attendantEmailField.clear();
        attendantPositionField.clear();
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Notificación");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
