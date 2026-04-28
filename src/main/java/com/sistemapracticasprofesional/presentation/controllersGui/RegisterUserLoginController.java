package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.logic.dto.UserDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.service.UserLoginService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;

public class RegisterUserLoginController {

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordField;

    private Alert alert;

    @FXML
    private void handelLogin(){
        
        UserLoginService userLoginService = new UserLoginService();
        UserDto userDto = new UserDto();
        createUserDto(userDto);

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

}
