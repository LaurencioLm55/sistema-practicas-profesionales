package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.logic.dto.InternDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.presentation.util.Navigation;
import com.sistemapracticasprofesional.logic.controllers.AssignProjectController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;  
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;

public class ListOfInternsControllerGui {

    @FXML
    private ListView<InternDto> internsListView;

    @FXML
    private TextField searchTextField;

    private FilteredList<InternDto> filteredList; 

    private ObservableList<InternDto> completeList = FXCollections.observableArrayList();

    private Alert alert;

    @FXML
    private void initialize(){

        getListIntern();

        filteredList = new FilteredList<>( completeList, p -> true );
        internsListView.setItems(filteredList);

        internsListView.getSelectionModel().selectedItemProperty().addListener(
            (observerItem, lastItem, newItem) -> {

            if (newItem != null){

                Stage stage = (Stage) internsListView.getScene().getWindow();

                try{
                    
                    Navigation.changeSceneData(stage, "GuiListOfProjects.fxml"
                    , "Asignat proyecto", 
                    internsListView.getSelectionModel().getSelectedItem());

                }catch(IOException e){

                    showAlert(AlertType.ERROR, "No se puedo abrir la ventana");

                }

            }

        });

    }

    public void getListIntern(){

        AssignProjectController assignProjectController = new AssignProjectController();

        try {

            completeList.addAll(assignProjectController.getListIntern());

        } catch (BusinessLogicException e) {

            showAlert(AlertType.ERROR, e.getMessage());

        }

    }

    @FXML
    public void  handleSearch (ActionEvent event) {

        String searchText = searchTextField.getText().trim().toLowerCase();

        filteredList.setPredicate(item -> {

            if (searchText.isEmpty()) {
                return true;
            }

            if ( item.getName().toLowerCase().contains(searchText)){
                return true;
            }

            if ( item.getStudentId().toLowerCase().contains(searchText)){
                return true;
            }

            return false;

        });

    }

    private void showAlert(Alert.AlertType type, String messange){

            alert = new Alert(type);
            alert.setTitle(null);
            alert.setHeaderText(messange);
            alert.showAndWait();

    }

}
