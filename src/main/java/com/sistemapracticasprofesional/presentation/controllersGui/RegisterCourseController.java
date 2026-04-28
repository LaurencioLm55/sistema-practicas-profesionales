package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.logic.dto.CourseDto;
import com.sistemapracticasprofesional.logic.exception.ServiceException;
import com.sistemapracticasprofesional.logic.exception.ValidationException;
import com.sistemapracticasprofesional.logic.service.CourseService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class RegisterCourseController {

   @FXML
   private TextField textFieldNrc;

   @FXML
   private TextField textFieldStaffNumber;

   @FXML
   private TextField textFieldStatus;

   @FXML
   private TextField textFieldPeriod;

   @FXML
   private TextField textFieldSection;

   private final CourseService courseService = new CourseService();

   @FXML
   private void handleRegisterCourse() {
      try {
         CourseDto courseDto = getCourseFromFields();

         courseService.registerCourse(courseDto);

         showAlert(Alert.AlertType.INFORMATION, "Curso registrado correctamente.");
         clearFields();

      } catch (NumberFormatException e) {
         showAlert(Alert.AlertType.WARNING, "El NRC y el numero de personal deben ser numericos.");

      } catch (ValidationException e) {
         showAlert(Alert.AlertType.WARNING, e.getMessage());

      } catch (ServiceException e) {
         showAlert(Alert.AlertType.ERROR, "No se pudo completar la operación.");
      }
   }

   @FXML
   private void handleCancel() {
      clearFields();
   }

   private CourseDto getCourseFromFields() {
      CourseDto courseDto = new CourseDto();

      courseDto.setNrc(Integer.parseInt(textFieldNrc.getText().trim()));
      courseDto.setStaffNumber(Integer.parseInt(textFieldStaffNumber.getText().trim()));
      courseDto.setStatus(textFieldStatus.getText());
      courseDto.setPeriod(textFieldPeriod.getText());
      courseDto.setSection(textFieldSection.getText());
      courseDto.setFormatFile(null);

      return courseDto;
   }

   private void clearFields() {
      textFieldNrc.clear();
      textFieldStaffNumber.clear();
      
      textFieldStatus.clear();
      textFieldPeriod.clear();
      textFieldSection.clear();
   }

   private void showAlert(Alert.AlertType type, String message) {
      Alert alert = new Alert(type);
      alert.setHeaderText(null);
      alert.setContentText(message);
      alert.showAndWait();
   }
}
