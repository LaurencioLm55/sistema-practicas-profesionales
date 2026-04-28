package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.logic.dto.InternDto;
import com.sistemapracticasprofesional.logic.exception.ServiceException;
import com.sistemapracticasprofesional.logic.exception.ValidationException;
import com.sistemapracticasprofesional.logic.service.InternService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class RegisterInternController {

   @FXML
   private TextField textFieldStudentId;

   @FXML
   private TextField textFieldAge;

   @FXML
   private TextField textFieldName;

   @FXML
   private TextField textFieldIndigenousLanguage;

   @FXML
   private TextField textFieldGender;

   @FXML
   private TextField textFieldMajor;

   private final InternService internService = new InternService();

   @FXML
   private void handleRegisterIntern() {
      try {
         InternDto internDto = getInternFromFields();

         internService.registerIntern(internDto);

         showAlert(Alert.AlertType.INFORMATION, "Practicante registrado correctamente.");
         clearFields();

      } catch (NumberFormatException e) {
         showAlert(Alert.AlertType.WARNING, "La edad debe ser numerica.");

      } catch (ValidationException e) {
         showAlert(Alert.AlertType.WARNING, e.getMessage());

      } catch (ServiceException e) {
         showAlert(Alert.AlertType.ERROR, "No se pudo completar la operacion.");
      }
   }

   @FXML
   private void handleCancel() {
      clearFields();
   }

   private void clearFields() {
      textFieldStudentId.clear();
      textFieldAge.clear();
      textFieldName.clear();
      textFieldIndigenousLanguage.clear();
      textFieldGender.clear();
      textFieldMajor.clear();
   }

   private InternDto getInternFromFields() {
      String indigenousLanguage = textFieldIndigenousLanguage.getText();

      if (indigenousLanguage != null && indigenousLanguage.trim().isEmpty()) {
         indigenousLanguage = null;
      }

      return new InternDto(
               textFieldStudentId.getText(),
               Integer.parseInt(textFieldAge.getText().trim()),
               textFieldName.getText(),
               indigenousLanguage,
               textFieldGender.getText(),
               textFieldMajor.getText()
      );
   }

   private void showAlert(Alert.AlertType type, String message) {
      Alert alert = new Alert(type);
      alert.setHeaderText(null);
      alert.setContentText(message);
      alert.showAndWait();
   }
}
