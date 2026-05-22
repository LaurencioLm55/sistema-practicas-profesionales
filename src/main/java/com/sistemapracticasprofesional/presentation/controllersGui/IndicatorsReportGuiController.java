package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.logic.controllers.IndicatorsReportController;
import com.sistemapracticasprofesional.logic.dto.IndicatorsReportDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.presentation.util.Navigation;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndicatorsReportGuiController implements Initializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndicatorsReportGuiController.class);

    @FXML
    private Label activeInternsValue;

    @FXML
    private Label inactiveInternsValue;

    @FXML
    private Label internsWithProjectValue;

    @FXML
    private Label internsWithoutProjectValue;

    @FXML
    private Label averageScoreValue;

    @FXML
    private Label totalProjectsValue;

    @FXML
    private Label totalOrganizationsValue;

    private final IndicatorsReportController indicatorsReportController = new IndicatorsReportController();
    private IndicatorsReportDto currentIndicators;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadIndicators();

    }

    private void loadIndicators() {

        try {

            currentIndicators = indicatorsReportController.getIndicators();

            activeInternsValue.setText(String.valueOf(currentIndicators.getTotalActiveInterns()));
            inactiveInternsValue.setText(String.valueOf(currentIndicators.getTotalInactiveInterns()));
            internsWithProjectValue.setText(String.valueOf(currentIndicators.getInternsWithProject()));
            internsWithoutProjectValue.setText(String.valueOf(currentIndicators.getInternsWithoutProject()));
            averageScoreValue.setText(String.format("%.2f", currentIndicators.getAverageMonthlyScore()));
            totalProjectsValue.setText(String.valueOf(currentIndicators.getTotalProjects()));
            totalOrganizationsValue.setText(String.valueOf(currentIndicators.getTotalAffiliatedOrganizations()));

        } catch (BusinessLogicException e) {

            showAlert(Alert.AlertType.ERROR, "Error al cargar los indicadores: " + e.getMessage());

        }

    }

    @FXML
    private void handleGeneratePdf(ActionEvent event) {

        if (currentIndicators == null) {
            showAlert(Alert.AlertType.WARNING, "No hay datos disponibles para generar el reporte.");
            return;
        }

        Window window = ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar reporte PDF");
        fileChooser.setInitialFileName("reporte_indicadores.pdf");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivo PDF", "*.pdf"));

        File file = fileChooser.showSaveDialog(window);
        if (file == null) {
            return;
        }

        try {

            indicatorsReportController.generatePdfReport(currentIndicators, file.getAbsolutePath());
            showAlert(Alert.AlertType.INFORMATION, "Reporte generado correctamente en:\n" + file.getAbsolutePath());

        } catch (BusinessLogicException e) {

            LOGGER.error("Error al generar el PDF", e);
            showAlert(Alert.AlertType.ERROR, "No se pudo generar el reporte PDF.");

        }

    }

    @FXML
    private void handleBack(ActionEvent event) {

        try {

            Navigation.changeScene(event, "GuiCoordinatorMenu.fxml", "Menú del coordinador");

        } catch (IOException e) {

            LOGGER.error("Ruta no encontrada", e);
            showAlert(Alert.AlertType.ERROR, "No se pudo volver al menú anterior.");

        }

    }

    private void showAlert(Alert.AlertType type, String message) {

        Alert alert = new Alert(type);
        alert.setTitle(null);
        alert.setHeaderText(message);
        alert.showAndWait();

    }

}
