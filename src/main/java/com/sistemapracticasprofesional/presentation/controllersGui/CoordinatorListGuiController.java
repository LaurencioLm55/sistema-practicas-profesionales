package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.logic.controllers.CoordinatorListController;
import com.sistemapracticasprofesional.logic.dto.CoordinatorDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.presentation.util.Navigation;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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

public class CoordinatorListGuiController implements Initializable {

    @FXML
    private TableView<CoordinatorDto> coordinatorsTableView;
    @FXML
    private TableColumn<CoordinatorDto, Integer> personnelNumberColumn;
    @FXML
    private TableColumn<CoordinatorDto, String> nameColumn;
    @FXML
    private TableColumn<CoordinatorDto, String> stateColumn;
    @FXML
    private TableColumn<CoordinatorDto, LocalDate> entryDateColumn;
    @FXML
    private TableColumn<CoordinatorDto, LocalDate> exitDateColumn;

    private final CoordinatorListController coordinatorListController = new CoordinatorListController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureTableColumns();
        loadCoordinators();
    }

    private void configureTableColumns() {
        personnelNumberColumn.setCellValueFactory(new PropertyValueFactory<>("personnelNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
        entryDateColumn.setCellValueFactory(new PropertyValueFactory<>("entryDate"));
        exitDateColumn.setCellValueFactory(new PropertyValueFactory<>("exitDate"));
    }

    private void loadCoordinators() {
        try {
            List<CoordinatorDto> coordinatorList = coordinatorListController.getAllCoordinators();
            ObservableList<CoordinatorDto> observableCoordinatorList = FXCollections.observableArrayList(coordinatorList);
            coordinatorsTableView.setItems(observableCoordinatorList);
        } catch (BusinessLogicException e) {
            showAlert(Alert.AlertType.ERROR, "Error al cargar coordinadores: " + e.getMessage());
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
