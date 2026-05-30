package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.logic.controllers.DeactivateInternController;
import com.sistemapracticasprofesional.logic.dto.InternDto;
import com.sistemapracticasprofesional.presentation.util.Navigation;
import com.sistemapracticasprofesional.presentation.util.ShowAlert;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListOfActiveInternsControllerGui {

    private DeactivateInternController deactivateInternController =
    new DeactivateInternController();
    private FilteredList<InternDto> filteredList;
    private ObservableList<InternDto> completeList = FXCollections.observableArrayList();
    private Alert alert;
    private ShowAlert showAlert = new ShowAlert(alert);

    private static final Logger LOGGER = LoggerFactory.getLogger( 
        ListOfActiveInternsControllerGui.class );

    @FXML
    private ListView<InternDto> listInternsUsers;


    @FXML
    private void initialize(){

        getListIntern();

        filteredList = new FilteredList<>( completeList, predicate -> true );
        listInternsUsers.setItems(filteredList);

        listInternsUsers.getSelectionModel().selectedItemProperty().addListener(
            (observerItem, lastItem, newItem) -> {
                    if (newItem != null) {
                        showAlert.showAlertConfirmation("¿Esta seguro de desactivar al practicante",
                        () -> deactivateInternController.deactivateInertn(newItem.getUserId()));
                    }
            });

    }

    @FXML
    private void handleSelectIntern(ActionEvent event) {



    }

    public void getListIntern(){

        completeList.addAll(deactivateInternController.getListInterns());

    }

    @FXML
    private void handleBack(ActionEvent event) {
        
        try {

            Navigation.changeScene(event, "GuiCoordinatorMenu.fxml",
             "Menú del coordinador");

        } catch (IOException e) {

            LOGGER.error("Ruta no encontrada", e);
            showAlert.showAlertError("No se pudo regresar al menú.");

        }
    }

    
}
