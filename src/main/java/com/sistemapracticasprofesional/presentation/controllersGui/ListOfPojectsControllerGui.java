package com.sistemapracticasprofesional.presentation.controllersGui;

import java.io.IOException;

import com.sistemapracticasprofesional.logic.controllers.AssignProjectController;
import com.sistemapracticasprofesional.logic.dto.ProjectDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.presentation.util.IReceiveData;
import com.sistemapracticasprofesional.presentation.util.Navigation;
import com.sistemapracticasprofesional.presentation.util.ProjectCell;
import com.sistemapracticasprofesional.logic.dto.InternDto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class ListOfPojectsControllerGui implements IReceiveData {

    private InternDto internDto;
    private AssignProjectController assignProjectController = new AssignProjectController();
    private Alert alert;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginControllerGui.class);

    @FXML
    private ListView<ProjectDto> proyectsListView;

    @FXML
    private Label idInternLabelDynamic;

    @FXML
    private Label nameLabelDynamic;

    @FXML
    public void initialize(){

        getListProjects();

        proyectsListView.setCellFactory(list -> new ProjectCell(assignProjectController, internDto));

    }

    @Override
    public void setData(Object internData){

        this.internDto = (InternDto) internData;
        
        idInternLabelDynamic.setText(internDto.getStudentId());
        nameLabelDynamic.setText(internDto.getName());

    }

    public void getListProjects(){

        AssignProjectController assignProjectController = new AssignProjectController();

        try{

            proyectsListView.getItems().addAll(assignProjectController.getListProjects());

        } catch (BusinessLogicException e) {

            showAlert(AlertType.ERROR, e.getMessage());

        }
    }

    @FXML
    private void handleBack(ActionEvent event) {

        try {

            Navigation.changeScene(event, "GuiListOfInters.fxml", "Menú del coordinador");

        } catch (IOException e) {

            LOGGER.error("Ruta no encontrada", e);
            showAlert(Alert.AlertType.ERROR, "No se pudo volver al menú anterior.");

        }

    }

    private void showAlert(Alert.AlertType type, String messange){

            alert = new Alert(type);
            alert.setTitle(null);
            alert.setHeaderText(messange);
            alert.showAndWait();

    }

    
}
