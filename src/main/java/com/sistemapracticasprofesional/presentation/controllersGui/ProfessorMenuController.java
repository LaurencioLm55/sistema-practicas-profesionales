package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.presentation.util.Navigation;
import com.sistemapracticasprofesional.presentation.util.UserSession;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProfessorMenuController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfessorMenuController.class);

    private Alert alert;

    @FXML
    private BorderPane rootPane;

    @FXML
    private void initialize() {
        validateAccess();
    }

    private void validateAccess() {
        UserSession session = UserSession.getInstance();

        if (!session.isActive() || !session.hasRole("Profesor")) {
            Platform.runLater(this::redirectToLogin);
        }
    }

    private void redirectToLogin() {
        try {
            showAlert(AlertType.ERROR, "Acceso no autorizado");
            UserSession.getInstance().closeSession();
            Stage stage = (Stage) rootPane.getScene().getWindow();
            Navigation.changeScene(stage, "GuiUserLogin.fxml", "Inicio de sesion");
        } catch (IOException e) {
            LOGGER.error("No se pudo regresar al inicio de sesion", e);
            showAlert(AlertType.ERROR, "No se pudo regresar al inicio de sesion");
        }
    }
    
    @FXML
    public void handleGradeReport(){

    }

    @FXML
    public void handleReportGradeForm () {

    }

    @FXML
    public void handleLogout () {
        
    }

    @FXML
    private void handelStartRegistrerProjectAttendant(ActionEvent event) {

    }

    @FXML
    private void handelStartRegistrerAffiliatedOrganization(ActionEvent event) {

    }

    @FXML
    private void handleStartRegistrerIntern(ActionEvent event) {

    }

    @FXML
    private void handelStartDisableIntern(ActionEvent event) {

    }

    @FXML
    private void handleRegisterCourse(ActionEvent event) {
        try {
            Navigation.changeScene(event, "GuiRegistrerCourses.fxml", "Registrar experiencia educativa");
        } catch (IOException e) {
            LOGGER.error("Ruta no encontrada", e);
            showAlert(AlertType.ERROR, "No se pudo abrir la ventana.");
        }
    }

    @FXML
    private void handeStartUpdateDataUser(ActionEvent event) {
        try {
            Navigation.changeScene(event, "GuiUptadeDataUser.fxml", "Actualizar datos de usuario");
        } catch (IOException e) {
            LOGGER.error("Ruta no encontrada", e);
            showAlert(AlertType.ERROR, "No se pudo abrir la ventana.");
        }
    }

    @FXML
    private void handeCloseSession(ActionEvent event) {
        try {
            UserSession.getInstance().closeSession();
            Navigation.changeScene(event, "GuiUserLogin.fxml", "Inicio de sesion");
        } catch (IOException e) {
            LOGGER.error("Ruta no encontrada", e);
            showAlert(AlertType.ERROR, "No se pudo cerrar sesion");
        }
    }

    private void showAlert(Alert.AlertType type, String message) {
        alert = new Alert(type);
        alert.setTitle(null);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
