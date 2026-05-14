package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.presentation.util.Navigation;
import com.sistemapracticasprofesional.presentation.util.UserSession;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdministratorMenuController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdministratorMenuController.class);

    @FXML
    private BorderPane rootPane;

    @FXML
    private void initialize() {
        validateAccess();
    }

    private void validateAccess() {
        UserSession session = UserSession.getInstance();

        if (!session.isActive() || !session.hasRole("Administrador")) {
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
    private void handleOpenRegisterUser(ActionEvent event) {
        try {
            Navigation.changeScene(event, "GuiRegisterUser.fxml", "Registrar usuario");
        } catch (IOException e) {
            LOGGER.error("Ruta no encontrada", e);
            showAlert(AlertType.ERROR, "No se pudo abrir la ventana de registro");
        }
    }

    @FXML
    private void handleConsultProfessors(ActionEvent event) {
        try {
            Navigation.changeScene(event, "GuiProfessorList.fxml", "Consultar Profesores");
        } catch (IOException e) {
            LOGGER.error("Ruta no encontrada", e);
            showAlert(AlertType.ERROR, "No se pudo abrir la ventana de consulta de profesores");
        }
    }

    @FXML
    private void handleCloseSession(ActionEvent event) {
        try {
            UserSession.getInstance().closeSession();
            Navigation.changeScene(event, "GuiUserLogin.fxml", "Inicio de sesion");
        } catch (IOException e) {
            LOGGER.error("Ruta no encontrada", e);
            showAlert(AlertType.ERROR, "No se pudo cerrar sesion");
        }
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(null);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
