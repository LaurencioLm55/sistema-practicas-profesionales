package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.logic.dao.RoleDao;
import com.sistemapracticasprofesional.logic.dto.CoordinatorDto;
import com.sistemapracticasprofesional.logic.dto.InternDto;
import com.sistemapracticasprofesional.logic.dto.ProfessorDto;
import com.sistemapracticasprofesional.logic.dto.RoleDto;
import com.sistemapracticasprofesional.logic.dto.UserDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.service.UserRegisterService;
import com.sistemapracticasprofesional.presentation.util.Navigation;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class RegisterUserController {

    private UserRegisterService userRegisterService = new UserRegisterService();
    private RoleDao roleDao = new RoleDao();

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private ComboBox<RoleDto> roleComboBox;

    @FXML
    private VBox coordinatorFields;

    @FXML
    private TextField coordinatorPersonnelNumberTextField;

    @FXML
    private TextField coordinatorNameTextField;

    @FXML
    private TextField coordinatorStateTextField;

    @FXML
    private DatePicker coordinatorEntryDatePicker;

    @FXML
    private DatePicker coordinatorExitDatePicker;

    @FXML
    private VBox professorFields;

    @FXML
    private TextField professorStaffNumberTextField;

    @FXML
    private TextField professorNameTextField;

    @FXML
    private TextField professorShiftTextField;

    @FXML
    private VBox internFields;

    @FXML
    private TextField internStudentIdTextField;

    @FXML
    private TextField internNameTextField;

    @FXML
    private TextField internAgeTextField;

    @FXML
    private TextField internGenderTextField;

    @FXML
    private TextField internMajorTextField;

    @FXML
    private TextField internIndigenousLanguageTextField;

    @FXML
    private void initialize() {
        loadRoles();
        roleComboBox.getSelectionModel().selectFirst();
        updateRoleFields();
    }

    @FXML
    private void handleRoleSelection() {
        updateRoleFields();
    }

    @FXML
    private void handleRegisterUser() {
        if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            showAlert(Alert.AlertType.ERROR, "Las contrasenas no coinciden");
            return;
        }

        try {
            UserDto userDto = createUserDto();
            RoleDto selectedRole = roleComboBox.getValue();

            if (selectedRole == null) {
                showAlert(Alert.AlertType.ERROR, "Selecciona un tipo de usuario");
                return;
            }

            userDto.setIdRole(selectedRole.getIdRole());

            switch (selectedRole.getName()) {
                case "Coordinador":
                    userRegisterService.registerCoordinator(userDto, createCoordinatorDto());
                    break;
                case "Profesor":
                    userRegisterService.registerProfessor(userDto, createProfessorDto());
                    break;
                case "Practicante":
                    userRegisterService.registerIntern(userDto, createInternDto());
                    break;
                default:
                    showAlert(Alert.AlertType.ERROR, "Selecciona un tipo de usuario");
                    return;
            }

            showAlert(Alert.AlertType.INFORMATION, "Usuario registrado correctamente");
            clearFields();
        } catch (BusinessLogicException | NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        try {
            Navigation.changeScene(event, "GuiUserLogin.fxml", "Inicio de sesion");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "No se pudo volver al inicio de sesion");
        }
    }

    private UserDto createUserDto() {
        UserDto userDto = new UserDto();
        userDto.setUserName(userNameTextField.getText());
        userDto.setPassword(passwordField.getText());
        return userDto;
    }

    private CoordinatorDto createCoordinatorDto() {
        CoordinatorDto coordinatorDto = new CoordinatorDto();
        coordinatorDto.setPersonnelNumber(parseInteger(coordinatorPersonnelNumberTextField.getText(),
                "Numero de personal invalido"));
        coordinatorDto.setName(coordinatorNameTextField.getText());
        coordinatorDto.setState(coordinatorStateTextField.getText());
        coordinatorDto.setEntryDate(coordinatorEntryDatePicker.getValue());
        coordinatorDto.setExitDate(coordinatorExitDatePicker.getValue());
        return coordinatorDto;
    }

    private ProfessorDto createProfessorDto() {
        ProfessorDto professorDto = new ProfessorDto();
        professorDto.setStaffNumber(parseInteger(professorStaffNumberTextField.getText(),
                "Numero de personal invalido"));
        professorDto.setName(professorNameTextField.getText());
        professorDto.setShift(professorShiftTextField.getText());
        return professorDto;
    }

    private InternDto createInternDto() {
        InternDto internDto = new InternDto(
                internStudentIdTextField.getText(),
                parseInteger(internAgeTextField.getText(), "Edad invalida"),
                internNameTextField.getText(),
                internIndigenousLanguageTextField.getText(),
                internGenderTextField.getText(),
                internMajorTextField.getText()
        );
        return internDto;
    }

    private int parseInteger(String value, String errorMessage) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(errorMessage);
        }
    }

    private void updateRoleFields() {
        RoleDto selectedRole = roleComboBox.getValue();
        String roleName = selectedRole != null ? selectedRole.getName() : "";

        setSectionVisible(coordinatorFields, "Coordinador".equals(roleName));
        setSectionVisible(professorFields, "Profesor".equals(roleName));
        setSectionVisible(internFields, "Practicante".equals(roleName));
    }

    private void loadRoles() {
        roleComboBox.getItems().clear();

        try {
            roleComboBox.getItems().addAll(roleDao.getAllRoles());
        } catch (RuntimeException e) {
            roleComboBox.getItems().addAll(
                    new RoleDto(1, "Coordinador"),
                    new RoleDto(2, "Profesor"),
                    new RoleDto(3, "Practicante")
            );
        }
    }

    private void setSectionVisible(VBox section, boolean visible) {
        section.setVisible(visible);
        section.setManaged(visible);
    }

    private void clearFields() {
        userNameTextField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(null);
        alert.setHeaderText(message);
        alert.setContentText(null);
        alert.showAndWait();
    }
}
