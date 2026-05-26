package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.logic.controllers.InternController;
import com.sistemapracticasprofesional.logic.controllers.MonthlyReportController;
import com.sistemapracticasprofesional.logic.dto.InternDto;
import com.sistemapracticasprofesional.logic.dto.MonthlyReportDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.presentation.util.Navigation;
import com.sistemapracticasprofesional.presentation.util.UserSession;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterMonthlyReportGuiController implements Initializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterMonthlyReportGuiController.class);

    @FXML
    private TextField activityIdField;

    @FXML
    private TextField reportFileField;

    @FXML
    private DatePicker completionDatePicker;

    @FXML
    private DatePicker deliveryDatePicker;

    @FXML
    private TextArea descriptionArea;

    private final MonthlyReportController monthlyReportController = new MonthlyReportController();
    private final InternController internController = new InternController();

    private String internStudentId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadInternStudentId();

    }

    private void loadInternStudentId() {

        try {

            int userId = UserSession.getInstance().getIdUser();
            InternDto intern = internController.getInternByUserId(userId);

            if (intern != null) {
                internStudentId = intern.getStudentId();
            }

        } catch (BusinessLogicException e) {

            LOGGER.error("No se pudo obtener la matrícula del practicante", e);
            showAlert(Alert.AlertType.ERROR, "No se pudo cargar la información del practicante.");

        }

    }

    @FXML
    private void handleRegister(ActionEvent event) {

        if (internStudentId == null) {

            showAlert(Alert.AlertType.ERROR, "No se pudo identificar al practicante en sesión.");
            return;

        }

        int activityId;

        try {

            activityId = Integer.parseInt(activityIdField.getText().trim());

        } catch (NumberFormatException e) {

            showAlert(Alert.AlertType.WARNING, "El ID de actividad debe ser un número entero.");
            return;

        }

        MonthlyReportDto monthlyReport = new MonthlyReportDto();
        monthlyReport.setMonthlyReportId(activityId);
        monthlyReport.setInternId(internStudentId);
        monthlyReport.setMonthlyReportFile(reportFileField.getText().trim());
        monthlyReport.setDateOfCompletion(completionDatePicker.getValue());
        monthlyReport.setDeliveryDate(deliveryDatePicker.getValue());
        monthlyReport.setDescription(descriptionArea.getText().trim());

        try {

            monthlyReportController.registerMonthlyReport(monthlyReport);
            showAlert(Alert.AlertType.INFORMATION, "Reporte mensual registrado correctamente.");

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
