package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.logic.controllers.ProfessorListController;
import com.sistemapracticasprofesional.logic.dto.ProfessorDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.presentation.util.Navigation;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProfessorListGuiController implements Initializable {

    @FXML
    private TableView<ProfessorDto> professorsTableView;
    @FXML
    private TableColumn<ProfessorDto, Integer> staffNumberColumn;
    @FXML
    private TableColumn<ProfessorDto, String> nameColumn;
    @FXML
    private TableColumn<ProfessorDto, String> shiftColumn;

    private final ProfessorListController professorListController = new ProfessorListController();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureTableColumns();
        loadProfessors();
    }

    private void configureTableColumns() {
        staffNumberColumn.setCellValueFactory(new PropertyValueFactory<>("staffNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        shiftColumn.setCellValueFactory(new PropertyValueFactory<>("shift"));
    }

    private void loadProfessors() {
        try {
            List<ProfessorDto> professorList = professorListController.getAllProfessors();
            ObservableList<ProfessorDto> observableProfessorList = FXCollections.observableArrayList(professorList);
            professorsTableView.setItems(observableProfessorList);
        } catch (BusinessLogicException e) {
            showAlert(Alert.AlertType.ERROR, "Error al cargar profesores: " + e.getMessage());
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        try {
            Navigation.changeScene(event, "GuiAdministratorMenu.fxml", "Menu administrador");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "No se pudo volver al menú anterior.");
        }
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(null);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}