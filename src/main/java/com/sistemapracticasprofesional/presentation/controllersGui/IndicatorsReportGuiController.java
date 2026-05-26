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
    private Label totalActiveInternsLabel;

    @FXML
    private Label totalInactiveInternsLabel;

    @FXML
    private Label internsWithProjectLabel;

    @FXML
    private Label internsWithoutProjectLabel;

    @FXML
    private Label totalProjectsLabel;

    @FXML
    private Label totalAffiliatedOrganizationsLabel;

    @FXML
    private Label averageMonthlyScoreLabel;

    private final IndicatorsReportController indicatorsReportController = new IndicatorsReportController();

    private IndicatorsReportDto currentIndicators;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadIndicators();

    }

    private void loadIndicators() {

        try {

            currentIndicators = indicatorsReportController.getIndicators();
            populateLabels(currentIndicators);

        } catch (BusinessLogicException e) {

            showAlert(Alert.AlertType.ERROR, "Error al cargar los indicadores: " + e.getMessage());

        }

    }

    private void populateLabels(IndicatorsReportDto indicators) {

        totalActiveInternsLabel.setText(String.valueOf(indicators.getTotalActiveInterns()));
        totalInactiveInternsLabel.setText(String.valueOf(indicators.getTotalInactiveInterns()));
        internsWithProjectLabel.setText(String.valueOf(indicators.getInternsWithProject()));
        internsWithoutProjectLabel.setText(String.valueOf(indicators.getInternsWithoutProject()));
        totalProjectsLabel.setText(String.valueOf(indicators.getTotalProjects()));
        totalAffiliatedOrganizationsLabel.setText(String.valueOf(indicators.getTotalAffiliatedOrganizations()));
        averageMonthlyScoreLabel.setText(String.format("%.2f", indicators.getAverageMonthlyScore()));

    }

    @FXML
    private void handleGeneratePdf(ActionEvent event) {

        if (currentIndicators == null) {

            showAlert(Alert.AlertType.WARNING, "No hay indicadores cargados para generar el reporte.");
            return;

        }

        File outputFile = selectOutputFile(event);
        if (outputFile == null) {
            return;
        }

        try {

            indicatorsReportController.generatePdfReport(currentIndicators, outputFile);
            showAlert(Alert.AlertType.INFORMATION, "Reporte generado correctamente en:\n" + outputFile.getAbsolutePath());

        } catch (BusinessLogicException e) {

            LOGGER.error("Error al generar el PDF", e);
            showAlert(Alert.AlertType.ERROR, "No se pudo generar el reporte PDF.");

        }

    }

    private File selectOutputFile(ActionEvent event) {

        Window window = ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar reporte PDF");
        fileChooser.setInitialFileName("reporte_indicadores.pdf");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivo PDF", "*.pdf"));

        return fileChooser.showSaveDialog(window);

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
