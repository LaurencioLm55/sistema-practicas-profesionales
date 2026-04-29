package com.sistemapracticasprofesional.presentation.controllersGui;
import com.sistemapracticasprofesional.logic.dto.AffiliatedOrganizationDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import com.sistemapracticasprofesional.logic.dao.AffiliatedOrganizationDao;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;


public class RegisterAffiliatedOrganizationController {
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
    private void registerAffiliatedOrganization(){

        AffiliatedOrganizationDao affiliatedOrganizationDao = new AffiliatedOrganizationDao();
        AffiliatedOrganizationDto affiliatedOrganizationDto = new AffiliatedOrganizationDto();
        getData(affiliatedOrganizationDto);

        try {
            
            boolean success = affiliatedOrganizationDao.insertOrganization(affiliatedOrganizationDto);

            if ( success == true ) {

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Exito");
                alert.setHeaderText(null);
                alert.setContentText("La organización se registro correctamente");
                alert.showAndWait();

            }else{

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Sin exito");
                alert.setHeaderText(null);
                alert.setContentText("La organización no pudo se registrada");
                alert.showAndWait();

            }

        }catch (NumberFormatException e){

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Valores incorrecto");
            alert.setContentText("Solo puede haber numeros en idOrganization");
            alert.showAndWait();

        } 
        catch (DaoException e) {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo registrar la organizacion");
            alert.setContentText("El sistema no puede registrar la organizacion por el momento intenete más tarde");
            alert.showAndWait();

        }
        
    }

    private void getData(AffiliatedOrganizationDto affiliatedOrganizationDto){

        affiliatedOrganizationDto.setIdOrganization(Integer.parseInt(idOrganizationTextField.getText()));
        affiliatedOrganizationDto.setName(nameTextField.getText());
        affiliatedOrganizationDto.setSector(sectorTextField.getText());
        affiliatedOrganizationDto.setAddress(addressTextField.getText());
        affiliatedOrganizationDto.setCity(cityTextField.getText());
        affiliatedOrganizationDto.setState(stateTextField.getText());
        affiliatedOrganizationDto.setPhoneNumber(phoneNumberTextField.getText());
        affiliatedOrganizationDto.setEmail(emailTextField.getText());


    }
}
