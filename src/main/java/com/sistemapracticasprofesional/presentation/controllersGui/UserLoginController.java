package com.sistemapracticasprofesional.presentation.controllersGui;

import java.io.IOException;

import org.slf4j.LoggerFactory;

import com.sistemapracticasprofesional.logic.dao.AffiliatedOrganizationDao;
import com.sistemapracticasprofesional.logic.dto.UserDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.service.NavigationServices;
import com.sistemapracticasprofesional.logic.service.UserLoginService;
import com.sistemapracticasprofesional.presentation.util.Navigation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserLoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordField;

    private Alert alert;

    @FXML
    private void handelLogin(ActionEvent event){

        UserLoginService userLoginService = new UserLoginService();
        UserDto userDto = new UserDto();
        createUserDto(userDto);

          try{

            Navigation navigation = new Navigation();
            navigation.changeScene(event, "GuiCoordinatorMenu.fxml", "Test");

        }catch (IOException e){

            LOGGER.error("Ruta no encontrada", e);
            showAlert(Alert.AlertType.ERROR, "No se pudo abrir la ventan");

        }

        try{
            
            userLoginService.logginUser(userDto);

        }catch(BusinessLogicException e){

            alert = new Alert(AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText("No se puede iniciar secion");
            alert.setContentText(null);

        }
    }

    private void createUserDto(UserDto userDto){

        userDto.setUserName(userNameTextField.getText());
        userDto.setPassword(passwordField.getText()); 

    }

    

    private void showAlert(Alert.AlertType type, String messange){

            alert = new Alert(type);
            alert.setTitle(null);
            alert.setHeaderText(messange);
            alert.showAndWait();

    }

}
