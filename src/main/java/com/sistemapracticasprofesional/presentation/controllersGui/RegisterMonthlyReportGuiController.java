package com.sistemapracticasprofesional.presentation.controllersGui;

import com.itextpdf.text.DocumentException;
import com.sistemapracticasprofesional.logic.controllers.InternController;
import com.sistemapracticasprofesional.logic.controllers.MonthlyReportController;
import com.sistemapracticasprofesional.logic.dto.InternDto;
import com.sistemapracticasprofesional.logic.dto.MonthlyReportActivityDto;
import com.sistemapracticasprofesional.logic.dto.MonthlyReportDto;
import com.sistemapracticasprofesional.logic.dto.MonthlyReportPdfData;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.util.MonthlyReportPdfGenerator;
import com.sistemapracticasprofesional.presentation.util.Navigation;
import com.sistemapracticasprofesional.presentation.util.UserSession;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterMonthlyReportGuiController implements Initializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterMonthlyReportGuiController.class);

    @FXML
    private DatePicker completionDatePicker;

    @FXML
    private DatePicker deliveryDatePicker;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private TextField activityNameField;

    @FXML
    private TextArea activityDescriptionArea;

    @FXML
    private ListView<String> activitiesListView;

    private final ObservableList<String> activitySummaries = FXCollections.observableArrayList();
    private final List<MonthlyReportActivityDto> activities = new ArrayList<>();

    private final MonthlyReportController monthlyReportController = new MonthlyReportController();
    private final InternController internController = new InternController();

    private InternDto intern;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        activitiesListView.setItems(activitySummaries);
        loadIntern();

    }

    private void loadIntern() {

        try {

            int userId = UserSession.getInstance().getIdUser();
            intern = internController.getInternByUserId(userId);

        } catch (BusinessLogicException e) {

            LOGGER.error("No se pudo obtener el practicante en sesión", e);
            showAlert(Alert.AlertType.ERROR, "No se pudo cargar la información del practicante.");

        }

    }

    @FXML
    private void handleAddActivity(ActionEvent event) {

        String activityName = activityNameField.getText().trim();
        String activityDescription = activityDescriptionArea.getText().trim();

        if (activityName.isEmpty() || activityDescription.isEmpty()) {

            showAlert(Alert.AlertType.WARNING, "Completa el nombre y la descripción de la actividad.");
            return;

        }

        MonthlyReportActivityDto activity = new MonthlyReportActivityDto();
        activity.setActivityName(activityName);
        activity.setDescription(activityDescription);

        activities.add(activity);
        activitySummaries.add(buildActivitySummary(activities.size(), activity));

        activityNameField.clear();
        activityDescriptionArea.clear();

    }

    @FXML
    private void handleRemoveActivity(ActionEvent event) {

        int selectedIndex = activitiesListView.getSelectionModel().getSelectedIndex();

        if (selectedIndex < 0) {

            showAlert(Alert.AlertType.WARNING, "Selecciona una actividad de la lista para eliminarla.");
            return;

        }

        activities.remove(selectedIndex);
        activitySummaries.remove(selectedIndex);
        renumberActivitySummaries();

    }

    @FXML
    private void handleGenerateReport(ActionEvent event) {

        if (intern == null) {

            showAlert(Alert.AlertType.ERROR, "No se pudo identificar al practicante en sesión.");
            return;

        }

        if (completionDatePicker.getValue() == null || deliveryDatePicker.getValue() == null) {

            showAlert(Alert.AlertType.WARNING, "Selecciona las fechas de realización y entrega.");
            return;

        }

        if (activities.isEmpty()) {

            showAlert(Alert.AlertType.WARNING, "Agrega al menos una actividad antes de generar el reporte.");
            return;

        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Reporte Mensual");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
        fileChooser.setInitialFileName("ReporteMensual_" + intern.getStudentId() + ".pdf");
        File outputFile = fileChooser.showSaveDialog(null);

        if (outputFile == null) {
            return;
        }

        MonthlyReportPdfData reportData = buildReportData();

        try {

            MonthlyReportPdfGenerator.generate(reportData, outputFile);

        } catch (DocumentException | IOException e) {

            LOGGER.error("Error al generar el PDF del reporte mensual", e);
            showAlert(Alert.AlertType.ERROR, "No se pudo generar el PDF: " + e.getMessage());
            return;

        }

        saveReportToDatabase(outputFile.getAbsolutePath());

        showAlert(Alert.AlertType.INFORMATION, "Reporte mensual generado y registrado correctamente.");

        try {

            Navigation.changeScene(event, "GuiInternsMenu.fxml", "Menú del practicante");

        } catch (IOException e) {

            LOGGER.error("Ruta no encontrada", e);

        }

    }

    private MonthlyReportPdfData buildReportData() {

        MonthlyReportPdfData reportData = new MonthlyReportPdfData();
        reportData.setInternName(intern.getName());
        reportData.setStudentId(intern.getStudentId());
        reportData.setCompletionDate(completionDatePicker.getValue());
        reportData.setDeliveryDate(deliveryDatePicker.getValue());
        reportData.setDescription(descriptionArea.getText().trim());
        reportData.setActivities(activities);

        return reportData;

    }

    private void saveReportToDatabase(String reportFilePath) {

        MonthlyReportDto monthlyReportDto = new MonthlyReportDto();
        monthlyReportDto.setMonthlyReportId((int) (System.currentTimeMillis() / 1000));
        monthlyReportDto.setInternId(intern.getStudentId());
        monthlyReportDto.setMonthlyReportFile(reportFilePath);
        monthlyReportDto.setDateOfCompletion(completionDatePicker.getValue());
        monthlyReportDto.setDeliveryDate(deliveryDatePicker.getValue());
        monthlyReportDto.setDescription(descriptionArea.getText().trim());

        try {

            monthlyReportController.registerMonthlyReport(monthlyReportDto);

        } catch (BusinessLogicException e) {

            LOGGER.error("Error al guardar el reporte mensual en BD", e);
            showAlert(Alert.AlertType.WARNING, "El reporte se generó pero no se pudo guardar en la base de datos.");

        }

    }

    private void renumberActivitySummaries() {

        for (int activityIndex = 0; activityIndex < activities.size(); activityIndex++) {

            String updatedSummary = buildActivitySummary(activityIndex + 1, activities.get(activityIndex));
            activitySummaries.set(activityIndex, updatedSummary);

        }

    }

    private String buildActivitySummary(int position, MonthlyReportActivityDto activity) {

        return position + ". " + activity.getActivityName();

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
