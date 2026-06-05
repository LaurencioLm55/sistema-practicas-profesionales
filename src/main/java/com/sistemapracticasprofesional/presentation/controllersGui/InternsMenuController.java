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

public class InternsMenuController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InternsMenuController.class);

    private Alert alert;

    @FXML
    private BorderPane rootPane;

    @FXML
    private void initialize() {
        validateAccess();
    }

    private void validateAccess() {
        UserSession session = UserSession.getInstance();

        if (!session.isActive() || !session.hasRole("Practicante")) {
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
    public void handleRegisterIntern(){

    }

    @FXML
    public void handleConsultInterns(){

    }

    @FXML
    public void handleBack (){

    }

    @FXML
    private void handelStartRegistrerProjectAttendant(ActionEvent event) {

    }

    @FXML
    private void handelStartAssignProject(ActionEvent event) {

    }

    @FXML
    private void handleStartRegistrerIntern(ActionEvent event) {

    }

    @FXML
    private void handleOpenMonthlyReport(ActionEvent event) {

        try {

            Navigation.changeScene(event, "GuiRegisterMonthlyReport.fxml", "Registrar reporte mensual");

        } catch (IOException e) {

            LOGGER.error("Ruta no encontrada", e);
            showAlert(AlertType.ERROR, "No se pudo abrir el formulario de reporte mensual.");

        }

    }

    @FXML
    private void handleOpenPartialReport(ActionEvent event) {

        try {

            Navigation.changeScene(event, "GuiRegisterPartialReport.fxml", "Registrar reporte parcial");

        } catch (IOException e) {

            LOGGER.error("Ruta no encontrada", e);
            showAlert(AlertType.ERROR, "No se pudo abrir el formulario de reporte parcial.");

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

    @FXML
    public void handleOpenEmailBox(ActionEvent event){
        try{
            
            Navigation.changeScene(event, "GuiEmailBox.fxml", "Buzon de correo");

        } catch (IOException e){

            showAlert(AlertType.ERROR, "Error: no se encontró la ventana de buzon");

        }
    }

    private void showAlert(Alert.AlertType type, String message) {
        alert = new Alert(type);
        alert.setTitle(null);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
