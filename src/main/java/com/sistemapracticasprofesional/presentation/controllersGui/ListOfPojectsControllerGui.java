package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.logic.controllers.AssignProjectController;
import com.sistemapracticasprofesional.logic.dto.ProjectDto;
import com.sistemapracticasprofesional.presentation.util.IReceiveData;
import com.sistemapracticasprofesional.logic.dto.InternDto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

public class ListOfPojectsControllerGui implements IReceiveData {

    private InternDto internDto;
    private AssignProjectController assignProjectController = new AssignProjectController();


    @FXML
    private ListView<ProjectDto> proyectsListView;

    @FXML
    private Label idInternLabelDynamic;

    @FXML
    private Label nameLabelDynamic;

    @FXML
    public void initialize(){
        proyectsListView.getItems().addAll(assignProjectController.getListProjects());

        proyectsListView.setCellFactory(lv -> new ListCell<ProjectDto>() {
            private final Button button = new Button("Asignar");
            private final Label label = new Label();
            private final HBox hbox = new HBox(10, label, button);

            @Override
            protected void updateItem(ProjectDto item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    label.setText(item.getProjectName());
                    button.setOnAction(e -> {
                        assignProjectController.assingProject(internDto.getStudentId(), item.getProjectId());
                    });
                    setGraphic(hbox);
                }
            }
        });
    }

    @Override
    public void setData(Object internData){

        this.internDto = (InternDto) internData;
        
        idInternLabelDynamic.setText(internDto.getStudentId());
        nameLabelDynamic.setText(internDto.getName());

    }

    public void setData(InternDto internDto){
        this.internDto = internDto;
    }
}
