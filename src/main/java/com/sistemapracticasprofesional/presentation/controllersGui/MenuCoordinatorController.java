package com.sistemapracticasprofesional.presentation.controllersGui;

<<<<<<< HEAD

=======
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
>>>>>>> 7a9df57abf7701f57db5978ea4974e7f5155b7a9
import com.sistemapracticasprofesional.presentation.util.Navigation;
import com.sistemapracticasprofesional.presentation.util.UserSession;

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
    private void handleRegisterCourse(ActionEvent event) {

        try {
            Navigation.changeScene(
                    event,
                    "GuiRegistrerCourses.fxml",
                    "Registrar experiencia educativa"
            );
        } catch (IOException e) {
<<<<<<< HEAD
        
        }
    }

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;

public class MenuCoordinatorController{
    

=======
           
            LOGGER.error("Ruta no encontrada", e);
            showAlert(AlertType.ERROR, "Error no se encontro la vetana");

        }

    }

    @FXML
    public void handeCloseSession ( ActionEvent event ){

       try{

            UserSession.getInstance().closeSession();

            Navigation.changeScene(event, "GuiUserLogin.fxml", "Inicio de sesion");

       }catch (IOException e){

            LOGGER.error("Ruta no encontrada", e);
            showAlert(AlertType.ERROR, "Error no se encontro la vetana");

       }


    }

    private void showAlert(Alert.AlertType type, String messange){

        alert = new Alert(type);
        alert.setTitle(null);
        alert.setHeaderText(messange);
        alert.showAndWait();

    }


>>>>>>> 7a9df57abf7701f57db5978ea4974e7f5155b7a9
}

