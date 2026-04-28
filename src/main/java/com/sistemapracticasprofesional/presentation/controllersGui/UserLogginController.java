package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.logic.dto.UserDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import com.sistemapracticasprofesional.logic.validators.GuiValidator;
import com.sistemapracticasprofesional.logic.dao.UserDao;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;

public class UserLogginController {

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordField;

    private Alert alert;

    @FXML
    private void handelLogin(){
        
        UserDto userDto = new UserDto();
        UserLogginController userLogginController = new UserLogginController();
        getData(userDto);

        try{
          
            String type = userLogginController.logginUser(userDto);

            switch (type) {
                case "Practicante":
                    
                    break;
                case "Profesor":

                    break;
                case "Coordinador":

                    break;

                default:
                    
                    break;
            }

        }catch(BusinessLogicException e){

            alert = new Alert(AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText("No se puede iniciar secion");
            alert.setContentText(null);

        }
    }

    private void getData( UserDto userDto ){

        GuiValidator validator = new GuiValidator();

        String userName = userNameTextField.getText();
        String userPassword = passwordField.getText();

        if ( validator.validateText( userNameTextField.getText() ) && validator.validateText( userPassword ) ){ 

            userDto.setUserName(userNameTextField.getText());
            userDto.setPassword(passwordField.getText());

        }

    }

}
