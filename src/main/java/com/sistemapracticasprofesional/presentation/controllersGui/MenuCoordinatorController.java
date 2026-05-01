package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.presentation.util.Navigation;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import javafx.scene.control.Alert;


public class MenuCoordinatorController{

    private Alert alert;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);

    @FXML
    public void handelStartRegistrerProjectAttendant( ActionEvent event) {

        try {
            
            Navigation.changeScene( event, "GuiRegistrerProjectAttendant.fxml","Registrar encargado" );

        } catch ( IOException e ) {

            LOGGER.error("Ruta no encontrada", e);
            showAlert(AlertType.ERROR, "Error no se encontro la vetana");

        }
    }
    
    @FXML
    public void handelStartRegistrerAffiliatedOrganization( ActionEvent event ) {

        try{

            Navigation.changeScene(event, "GuiRegisterAffiliatedOrganization.fxml", "Registro de organizacion vinculada" );

        }catch( IOException e ){

            LOGGER.error("Ruta no encontrada", e);
            showAlert(AlertType.ERROR, "Error no se encontro la vetana");

        }
    }

    @FXML
    public void handelStartAssignProject( ActionEvent event) {

        try{

            Navigation.changeScene(event, "GuiListOfActiveUsers.fxml", "Asignar proyecto");

        }catch( IOException e ){
            
            LOGGER.error("Ruta no encontrada", e);
            showAlert(AlertType.ERROR, "Error no se encontro la vetana");

        }

    }

    @FXML 
    public void handleStartRegistrerIntern( ActionEvent event ){
        
        try{

            Navigation.changeScene(event, "GuiRegisterIntern.fxml", "Registrar practicante");

        }catch( IOException e ){

            LOGGER.error("Ruta no encontrada", e);
            showAlert(AlertType.ERROR, "Error no se encontro la vetana");

        }

    }

    @FXML
    public void handelStartDisableIntern ( ActionEvent event ){

        try{

            Navigation.changeScene(event, "GuiListOfActiveUsers.fxml", "Desabilitar interno");

        }catch( IOException e ){

            LOGGER.error("Ruta no encontrada", e);
            showAlert(AlertType.ERROR, "Error no se encontro la vetana");

        }

    }

    
    @FXML
    public void handeStartGenerateReports ( ActionEvent event ){

        try{

            Navigation.changeScene(event, null, null);

        }catch( IOException e ){

            LOGGER.error("Ruta no encontrada", e);
            showAlert(AlertType.ERROR, "Error no se encontro la vetana");

        }

    }

    @FXML
    public void handeStartUpdateDataUser( ActionEvent event ){

        try{

            Navigation.changeScene(event, "GuiUptadeDataUser.fxml", "Actualizar datos de usuario");

        }catch( IOException e ){
            
            LOGGER.error("Ruta no encontrada", e);
            showAlert(AlertType.ERROR, "Error no se encontro la vetana");

        }

    }

    @FXML
    public void handeCloseSession ( ActionEvent event ){



    }

    private void showAlert(Alert.AlertType type, String messange){

        alert = new Alert(type);
        alert.setTitle(null);
        alert.setHeaderText(messange);
        alert.showAndWait();

    }

   @FXML
    private void handleRegisterCourse(ActionEvent event) {
        try {
            Navigation.changeScene(
                    event,
                    "GuiRegistrerCourses.fxml",
                    "Registrar experiencia educativa"
            );
        } catch (IOException e) {
           
        }
    }

}

