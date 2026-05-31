package com.sistemapracticasprofesional.presentation.util;

import java.util.Optional;

import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;;

public class ShowAlert {

    private Alert alert;

    public ShowAlert(Alert alert){

        this.alert = alert;

    }

    @FunctionalInterface
    public interface RunnableShowAlert {
        
        boolean run() throws BusinessLogicException;
        
    }
    
    public void showAlertError(String message){
        
        this.alert = new Alert(AlertType.ERROR);
        this.alert.setTitle(null);
        this.alert.setHeaderText(message);
        this.alert.showAndWait();

    }

    public void showAlertInformation(String message){
        
        alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(message);
        alert.showAndWait();

    }

    public void showAlertWarning(String  message){

        alert = new Alert(AlertType.WARNING);
        alert.setTitle(null);
        alert.setHeaderText(message);
        alert.showAndWait();

    }

    public void showAlertConfirmation(String questionText, RunnableShowAlert acctionYes, RunnableShowAlert acctionNo){

        ButtonType buttonYes = new ButtonType("Si");
        ButtonType buttonNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(questionText);
        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        Optional<ButtonType> result = alert.showAndWait();

        
        if (result.isPresent()){
            try{ 
                if(result.get() == buttonYes){
                    acctionYes.run();
                } else {
                    acctionNo.run();
                }
            } catch (BusinessLogicException e){

                showAlertError(e.getMessage());

            }
        }

    }

    public void showAlertConfirmation(String questionText, RunnableShowAlert acctionYes){

        boolean result = false;
        ButtonType buttonYes = new ButtonType("Si");
        ButtonType buttonNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(questionText);
        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        Optional<ButtonType> resultButton = alert.showAndWait();

        
        if (resultButton.isPresent()){
            try{
                if(resultButton.get() == buttonYes){
                    result = acctionYes.run();
                }

                if (result) {

                    showAlertInformation("Se desactivo el usuario con exito");

                } else {

                    showAlertInformation("No se pudo desactivar el usuario");

                }
                
            } catch (BusinessLogicException e){

                showAlertError(e.getMessage());

            }
        }

    }


}
