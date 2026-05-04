package com.sistemapracticasprofesional.presentation.controllersGui;

import java.io.IOException;

import org.slf4j.LoggerFactory;

import com.sistemapracticasprofesional.logic.dto.UserDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.service.UserLoginService;
import com.sistemapracticasprofesional.presentation.util.Navigation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import org.slf4j.Logger;


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
            
            String typeUser = userLoginService.logginUser(userDto);

            startPresentationWhitType( typeUser, event );

        }catch(BusinessLogicException e){

            showAlert(AlertType.INFORMATION, "No se puede iniciar sesion");

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

    private void startPresentationWhitType(String typeUser, ActionEvent event){


        switch (typeUser) {
            case "Practicante":

                startIntern(event);

                break;

            case "Profesor":

                startProfessor(event);

                break;
            
            case "Coordinador":

                startCoordinator(event);

                break;
        
            default:
                showAlert(AlertType.INFORMATION, "Usuario no encontrado");
                break;
        }

    }

    private void startIntern(ActionEvent event){

        try{

            Navigation.changeScene(event, "GuiInternsMenu.fxml", "Menu de inicio");

        }catch (IOException e) {

            LOGGER.error("Ruta no encontrada", e);
            showAlert(AlertType.ERROR, "Error no se encontro la vetana");
            
        }

    }

    private void startProfessor(ActionEvent event){

        try{

            Navigation.changeScene(event, "GuiProfessorMenu.fxml", "Menu de inicio");

        }catch (IOException e) {

            LOGGER.error("Ruta no encontrada", e);
            showAlert(AlertType.ERROR, "Error no se encontro la vetana");
            
        }
    
    }

    private void startCoordinator(ActionEvent event){
        
        try{

            Navigation.changeScene(event, "GuiCoordinatorMenu.fxml", "Menu de inicio");

        }catch (IOException e) {

            LOGGER.error("Ruta no encontrada", e);
            showAlert(AlertType.ERROR, "Error no se encontro la vetana");
            
        }

    }

    @FXML
    private void handleOpenRegisterUser(ActionEvent event) {
        try {
            Navigation.changeScene(event, "GuiRegisterUser.fxml", "Registrar usuario");
        } catch (IOException e) {
            LOGGER.error("Ruta no encontrada", e);
            showAlert(AlertType.ERROR, "No se pudo abrir la ventana de registro");
        }
    }

}
