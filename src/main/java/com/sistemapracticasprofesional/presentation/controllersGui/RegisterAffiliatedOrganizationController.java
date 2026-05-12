package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.logic.controllers.RegistrerAffiliatedOrganizationController;
import com.sistemapracticasprofesional.logic.dto.AffiliatedOrganizationDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;


public class RegisterAffiliatedOrganizationController {

    private AffiliatedOrganizationDto affiliatedOrganizationDto = new AffiliatedOrganizationDto();
    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginControllerGui.class);
    private Alert alert;

    @FXML
    private TextField idOrganizationTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField sectorTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField cityTextField;

    @FXML
    private TextField stateTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private void handleRegisterAffiliatedOrganization(){
        
        getData(affiliatedOrganizationDto);
        RegistrerAffiliatedOrganizationController registrerAffiliatedOrganizationController = new RegistrerAffiliatedOrganizationController();
        boolean result;

        try{

            result = registrerAffiliatedOrganizationController.RegistrerAffiliatedOrganization(affiliatedOrganizationDto);

            if (result) {
                
                showAlert(AlertType.CONFIRMATION, "Se registro la organización correctamente");

            }else{

                showAlert(AlertType.ERROR, "No se pudo registrar la organización");
            
            }

        }catch (BusinessLogicException e){

            LOGGER.warn("Se intento registrar una organizacón con datos invalidos", e);
            showAlert(AlertType.ERROR, "Los datos no son validos");

        }
    }

    private void getData(AffiliatedOrganizationDto affiliatedOrganizationDto){

        affiliatedOrganizationDto.setIdOrganization(idOrganizationTextField.getText());
        affiliatedOrganizationDto.setName(nameTextField.getText());
        affiliatedOrganizationDto.setSector(sectorTextField.getText());
        affiliatedOrganizationDto.setAddress(addressTextField.getText());
        affiliatedOrganizationDto.setCity(cityTextField.getText());
        affiliatedOrganizationDto.setState(stateTextField.getText());
        affiliatedOrganizationDto.setPhoneNumber(phoneNumberTextField.getText());
        affiliatedOrganizationDto.setEmail(emailTextField.getText());


    }

     private void showAlert(Alert.AlertType type, String messange){

            alert = new Alert(type);
            alert.setTitle(null);
            alert.setHeaderText(messange);
            alert.showAndWait();

    }
}
