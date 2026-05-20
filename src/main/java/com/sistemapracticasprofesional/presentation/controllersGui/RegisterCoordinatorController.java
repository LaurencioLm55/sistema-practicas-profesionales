package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.logic.controllers.CoordinatorController;
import com.sistemapracticasprofesional.logic.dto.CoordinatorDto;
import com.sistemapracticasprofesional.logic.dto.UserDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.exception.ControllerException;
import com.sistemapracticasprofesional.logic.exception.ValidationException;
import com.sistemapracticasprofesional.presentation.util.Navigation;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterCoordinatorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterCoordinatorController.class);

    private final CoordinatorController coordinatorController = new CoordinatorController();

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField personalNumberTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleRegisterCoordinator() {
        try {
            CoordinatorDto coordinatorDto = buildCoordinatorDto();
            UserDto userDto = buildUserDto(coordinatorDto);

            coordinatorController.registerCoordinator(userDto, coordinatorDto);

            showAlert(Alert.AlertType.INFORMATION, "Coordinador registrado exitosamente");
            clearFields();

        } catch (ValidationException | BusinessLogicException e) {
            showAlert(Alert.AlertType.WARNING, e.getMessage());
        } catch (ControllerException e) {
            LOGGER.error("Error al registrar coordinador", e);
            showAlert(Alert.AlertType.ERROR, "Ocurrió un error al registrar el coordinador. Intente nuevamente.");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING, "El número de personal debe ser un valor numérico válido");
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        try {
            Navigation.changeScene(event, "GuiAdministratorMenu.fxml", "Menú administrador");
        } catch (IOException e) {
            LOGGER.error("Error al navegar al menú administrador", e);
            showAlert(Alert.AlertType.ERROR, "No se pudo volver al menú administrador");
        }
    }

    private CoordinatorDto buildCoordinatorDto() {
        CoordinatorDto coordinatorDto = new CoordinatorDto();
        coordinatorDto.setName(nameTextField.getText().trim());
        coordinatorDto.setPersonnelNumber(parsePersonnelNumber(personalNumberTextField.getText()));
        return coordinatorDto;
    }

    private UserDto buildUserDto(CoordinatorDto coordinatorDto) {
        UserDto userDto = new UserDto();
        userDto.setUserName(String.valueOf(coordinatorDto.getPersonnelNumber()));
        userDto.setPassword(passwordField.getText());
        return userDto;
    }

    private int parsePersonnelNumber(String rawPersonnelNumber) {
        try {
            return Integer.parseInt(rawPersonnelNumber.trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El número de personal debe ser numérico");
        }
    }

    private void clearFields() {
        nameTextField.clear();
        personalNumberTextField.clear();
        passwordField.clear();
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(null);
        alert.setHeaderText(message);
        alert.setContentText(null);
        alert.showAndWait();
    }
}
