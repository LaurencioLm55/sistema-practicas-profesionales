package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.logic.controllers.CourseController;
import com.sistemapracticasprofesional.logic.dto.CourseDto;
import com.sistemapracticasprofesional.logic.exception.ControllerException;
import com.sistemapracticasprofesional.logic.exception.ValidationException;
import com.sistemapracticasprofesional.presentation.util.Navigation;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterCourseController {

   private static final Logger LOGGER = LoggerFactory.getLogger(RegisterCourseController.class);

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

   private final CourseController courseController = new CourseController();

   @FXML
   private void handleRegisterCourse() {

      try {

         CourseDto courseDto = getCourseFromFields();

         courseController.registerCourse(courseDto);

         showAlert(Alert.AlertType.INFORMATION, "Curso registrado correctamente.");
         clearFields();

      } catch (NumberFormatException e) {

         showAlert(Alert.AlertType.WARNING, "El NRC y el numero de personal deben ser numericos.");

      } catch (ValidationException e) {

         showAlert(Alert.AlertType.WARNING, e.getMessage());

      } catch (ControllerException e) {

         showAlert(Alert.AlertType.ERROR, "No se pudo completar la operación.");

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
