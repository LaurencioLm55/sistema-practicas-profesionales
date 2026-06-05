package com.sistemapracticasprofesional.presentation.controllersGui;

import com.sistemapracticasprofesional.logic.dto.MessageDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.controllers.MessageController;
import com.sistemapracticasprofesional.presentation.util.EmailListCell;
import com.sistemapracticasprofesional.presentation.util.Navigation;
import com.sistemapracticasprofesional.presentation.util.ShowAlert;
import com.sistemapracticasprofesional.presentation.util.UserSession;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class MessageControllerGui {

    private Alert alert;
    private ShowAlert showAlert = new ShowAlert(alert);
    private MessageController messageController = new MessageController();
    private MessageDto messageDto = new MessageDto();
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginControllerGui.class);

    @FXML
    private ListView<MessageDto> emailList;

    @FXML
    private Label senderNameLabel;

    @FXML
    private Label subjectLabel;

    @FXML
    private Label contentLabel;

    @FXML
    private Label dataLabel;

    @FXML
    private Pane messagePane;

    @FXML
    private VBox contenteMessagePane;

    @FXML
    private TextField receiverUserTextField;

    @FXML
    private TextField subjectTextField;

    @FXML 
    private TextArea contentTextField;

    @FXML 
    private void initialize(){

        senderNameLabel.setVisible(false);
        subjectLabel.setVisible(false);
        contentLabel.setVisible(false);
        dataLabel.setVisible(false);
        
        messagePane.setVisible(false);

        emailList.setCellFactory(cellFactory -> new EmailListCell());

        try {
            
            emailList.getItems().addAll(messageController.getMessages(UserSession.getInstance().getIdUser()));

        } catch (BusinessLogicException e){

            showAlert.showAlertError(e.getMessage());
            
        }

        emailList.getSelectionModel().selectedItemProperty().addListener(
            (observerItem, lastItem, newItem) -> {

                if ( newItem != null){

                    try {
                        
                        System.out.println(newItem.getMessageId());
                        MessageDto completeMessage = messageController.openMessage(newItem.getMessageId());

                        senderNameLabel.setText(completeMessage.getSenderUserName());
                        subjectLabel.setText(completeMessage.getSubject());
                        contentLabel.setText(completeMessage.getContent());

                        String date = completeMessage.getMessageDate().format(dateTimeFormatter);
                        dataLabel.setText(date);

                        senderNameLabel.setVisible(true);
                        subjectLabel.setVisible(true);
                        contentLabel.setVisible(true);
                        dataLabel.setVisible(true);
                        
                    } catch (BusinessLogicException e){

                         showAlert.showAlertError(e.getMessage());

                    }

                }
            }
        );
    }

    @FXML
    public void handleWriteEmail(ActionEvent event){

        messagePane.setVisible(true);
        contenteMessagePane.setVisible(true);

        TranslateTransition transition = new TranslateTransition(Duration.millis(250), messagePane);
        transition.setFromY(30);
        transition.setToY(0);
        transition.play();

    }

    @FXML
    public void handleReplyEmail(){
        
    }

    @FXML
    public void handleCloseWriterEmail(){

        TranslateTransition transition = new TranslateTransition(Duration.millis(250), messagePane);
        transition.setFromY(0);
        transition.setToY(30);
        transition.setOnFinished( e -> {
             messagePane.setVisible(false);
        }
        );
        transition.play();

    }

    @FXML
    public void handleSendMessage(){

        getMessage();
        
        try{

            String result = messageController.sendMessage(messageDto);

            showAlert.showAlertInformation(result);

        } catch (BusinessLogicException e){

            showAlert.showAlertError(e.getMessage());

        }
    }

    @FXML
    public void hadleReturnBack(ActionEvent event){

        startPresentationWhitType(UserSession.getInstance().getRole(), event);
        
    }

    private void getMessage(){

        messageDto.setReciverUserName(receiverUserTextField.getText());
        messageDto.setSubject(subjectTextField.getText());
        messageDto.setContent(contentTextField.getText());
        messageDto.setSenderUserId(UserSession.getInstance().getIdUser());
        messageDto.setSenderUserName(UserSession.getInstance().getUserName());

    }

    private void startPresentationWhitType(String typeUser, ActionEvent event){


        switch (typeUser) {
            case "Practicante":

                startIntern(event);

                break;

            case "Profesor":

                startProfessor(event);

                break;
            
            case "Coordinador":

                startCoordinator(event);

                break;

            case "Administrador":

                startAdministrator(event);

                break;
        
            default:
                showAlert.showAlertInformation("Usuario no encontrado");
                break;
        }

    }

    private void startIntern(ActionEvent event){

        try{

            Navigation.changeScene(event, "GuiInternsMenu.fxml", "Menu de inicio");

        }catch (IOException e) {

            LOGGER.error("Ruta no encontrada", e);
            showAlert.showAlertError("Error no se encontro la vetana");
            
        }

    }

    private void startProfessor(ActionEvent event){

        try{

            Navigation.changeScene(event, "GuiProfessorMenu.fxml", "Menu de inicio");

        }catch (IOException e) {

            LOGGER.error("Ruta no encontrada", e);
            showAlert.showAlertError("Error no se encontro la vetana");
            
        }
    
    }

    private void startCoordinator(ActionEvent event){
        
        try{

            Navigation.changeScene(event, "GuiCoordinatorMenu.fxml", "Menu de inicio");

        }catch (IOException e) {

            LOGGER.error("Ruta no encontrada", e);
            showAlert.showAlertError("Error no se encontro la vetana");
            
        }

    }

    private void startAdministrator(ActionEvent event){
        
        try{

            Navigation.changeScene(event, "GuiAdministratorMenu.fxml", "Menu administrador");

        }catch (IOException e) {

            LOGGER.error("Ruta no encontrada", e);
            showAlert.showAlertError("Error no se encontro la vetana");
            
        }

    }
}