package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.presentation.util.Navigation;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListOfActiveUsersControllerGui {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListOfActiveUsersControllerGui.class);

    private Alert alert;

    @FXML
    private ListView<String> listUsers;

    @FXML
    private void handleSelectIntern(ActionEvent event) {

    }

    @FXML
    private void handleBack(ActionEvent event) {
        try {
            Navigation.changeScene(event, "GuiCoordinatorMenu.fxml", "Menú del coordinador");
        } catch (IOException e) {
            LOGGER.error("Ruta no encontrada", e);
            showAlert(AlertType.ERROR, "No se pudo regresar al menú.");
        }
    }

    private void showAlert(Alert.AlertType type, String message) {
        alert = new Alert(type);
        alert.setTitle(null);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
