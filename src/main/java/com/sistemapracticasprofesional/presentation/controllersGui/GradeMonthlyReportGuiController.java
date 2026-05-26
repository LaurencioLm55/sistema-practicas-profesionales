package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.logic.controllers.MonthlyReportController;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.presentation.util.Navigation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GradeMonthlyReportGuiController implements Initializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(GradeMonthlyReportGuiController.class);

    @FXML
    private TextField activityIdField;

    @FXML
    private TextField scoreField;

    private final MonthlyReportController monthlyReportController = new MonthlyReportController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void handleGrade(ActionEvent event) {

        int reportId;

        try {

            reportId = Integer.parseInt(activityIdField.getText().trim());

        } catch (NumberFormatException e) {

            showAlert(Alert.AlertType.WARNING, "El ID de actividad debe ser un número entero.");
            return;

        }

        float score;

        try {

            score = Float.parseFloat(scoreField.getText().trim());

        } catch (NumberFormatException e) {

            showAlert(Alert.AlertType.WARNING, "La calificación debe ser un número (ej. 8.5).");
            return;

        }

        try {

            monthlyReportController.gradeMonthlyReport(reportId, score);
            showAlert(Alert.AlertType.INFORMATION, "Calificación registrada correctamente.");

        } catch (BusinessLogicException e) {

            showAlert(Alert.AlertType.ERROR, e.getMessage());
            return;

        }

        try {

            Navigation.changeScene(event, "GuiProfessorMenu.fxml", "Menú del profesor");

        } catch (IOException e) {

            LOGGER.error("Ruta no encontrada", e);

        }

    }

    @FXML
    private void handleBack(ActionEvent event) {

        try {

            Navigation.changeScene(event, "GuiProfessorMenu.fxml", "Menú del profesor");

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
