package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.logic.controllers.UserRegisterController;
import com.sistemapracticasprofesional.logic.dto.InternDto;
import com.sistemapracticasprofesional.logic.dto.UserDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.presentation.util.Navigation;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterInternController {

   private static final Logger LOGGER = LoggerFactory.getLogger(RegisterInternController.class);
   private static final int INTERN_ROLE_ID = 3;

   @FXML
   private TextField textFieldUserName;

   @FXML
   private PasswordField passwordField;

   @FXML
   private PasswordField confirmPasswordField;

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

   private final UserRegisterController userRegisterController = new UserRegisterController();

   @FXML
   private void handleRegisterIntern() {
      try {
         UserDto userDto = getUserFromFields();
         InternDto internDto = getInternFromFields();

         userRegisterController.registerIntern(userDto, confirmPasswordField.getText(), internDto);

         showAlert(Alert.AlertType.INFORMATION, "Practicante registrado correctamente.");
         clearFields();

      } catch (BusinessLogicException | NumberFormatException e) {
         showAlert(Alert.AlertType.ERROR, e.getMessage());
      }
   }

   @FXML
   private void handleCancel(ActionEvent event) {
      try {
         Navigation.changeScene(event, "GuiCoordinatorMenu.fxml", "Menú del coordinador");
      } catch (IOException e) {
         LOGGER.error("Ruta no encontrada", e);
         showAlert(Alert.AlertType.ERROR, "No se pudo regresar al menú.");
      }
   }

   private void clearFields() {
      textFieldUserName.clear();
      passwordField.clear();
      confirmPasswordField.clear();
      textFieldStudentId.clear();
      textFieldAge.clear();
      textFieldName.clear();
      textFieldIndigenousLanguage.clear();
      textFieldGender.clear();
      textFieldMajor.clear();
   }

   private UserDto getUserFromFields() {
      UserDto userDto = new UserDto();
      userDto.setUserName(textFieldUserName.getText());
      userDto.setPassword(passwordField.getText());
      userDto.setIdRole(INTERN_ROLE_ID);
      return userDto;
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
