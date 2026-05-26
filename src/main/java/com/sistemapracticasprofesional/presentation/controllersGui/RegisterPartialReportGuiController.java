package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.logic.controllers.PartialReportController;
import com.sistemapracticasprofesional.logic.dto.PartialReportDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.presentation.util.Navigation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterPartialReportGuiController implements Initializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterPartialReportGuiController.class);

    @FXML
    private TextField activityIdField;

    @FXML
    private TextField plannedTimeField;

    @FXML
    private TextField realTimeField;

    @FXML
    private TextArea resultsArea;

    @FXML
    private TextArea observationsArea;

    private final PartialReportController partialReportController = new PartialReportController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void handleRegister(ActionEvent event) {

        int activityId;

        try {

            activityId = Integer.parseInt(activityIdField.getText().trim());

        } catch (NumberFormatException e) {

            showAlert(Alert.AlertType.WARNING, "El ID de actividad debe ser un número entero.");
            return;

        }

        PartialReportDto partialReport = new PartialReportDto();
        partialReport.setProyectActivityId(activityId);
        partialReport.setPlannedTime(plannedTimeField.getText().trim());
        partialReport.setRealTime(realTimeField.getText().trim());
        partialReport.setResults(resultsArea.getText().trim());
        partialReport.setObservations(observationsArea.getText().trim());

        try {

            partialReportController.registerPartialReport(partialReport);
            showAlert(Alert.AlertType.INFORMATION, "Reporte parcial registrado correctamente.");

        } catch (BusinessLogicException e) {

            showAlert(Alert.AlertType.ERROR, e.getMessage());
            return;

        }

        try {

            Navigation.changeScene(event, "GuiInternsMenu.fxml", "Menú del practicante");

        } catch (IOException e) {

            LOGGER.error("Ruta no encontrada", e);

        }

    }

    @FXML
    private void handleBack(ActionEvent event) {

        try {

            Navigation.changeScene(event, "GuiInternsMenu.fxml", "Menú del practicante");

        } catch (IOException e) {

            LOGGER.error("Ruta no encontrada", e);
            showAlert(Alert.AlertType.ERROR, "No se pudo volver al menú.");

        }

    }

    private void showAlert(Alert.AlertType type, String message) {

        Alert alert = new Alert(type);
        alert.setTitle(null);
        alert.setHeaderText(message);
        alert.showAndWait();

    }

}
