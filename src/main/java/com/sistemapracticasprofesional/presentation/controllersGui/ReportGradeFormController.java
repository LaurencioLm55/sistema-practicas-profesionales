package com.sistemapracticasprofesional.presentation.controllersGui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ReportGradeFormController implements Initializable {

    @FXML
    private ImageView logoImageView;
    @FXML
    private TextArea txtObservationBox;
    @FXML
    private TextField txtGradeField;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnFinish;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void onClickCancel(ActionEvent event) {
    }

    @FXML
    private void onClickFinish(ActionEvent event) {
    }
    
}
