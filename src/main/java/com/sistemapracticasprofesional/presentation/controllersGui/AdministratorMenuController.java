package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.presentation.util.Navigation;
import com.sistemapracticasprofesional.presentation.util.UserSession;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdministratorMenuController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdministratorMenuController.class);

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
