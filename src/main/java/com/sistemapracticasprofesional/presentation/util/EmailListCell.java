package com.sistemapracticasprofesional.presentation.util;

import com.sistemapracticasprofesional.logic.dto.MessageDto;
import javafx.scene.control.ListCell;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class EmailListCell extends ListCell<MessageDto>{
    
    private VBox container;
    private Label sender;
    private Label subject;
    private Label content;

    public EmailListCell() {

        sender = new Label();

        subject = new Label();

        content = new Label();
        content.setMaxWidth(Double.MAX_VALUE);

        container = new VBox(4);
        container.setPadding(new Insets(8, 12, 8, 12));
        container.getChildren().addAll(sender, subject, content);

    }

    @Override
    protected void updateItem(MessageDto messageDto, boolean empty){

        super.updateItem(messageDto, empty);

        if (empty || messageDto == null){

            setGraphic(null);

        }else {

            sender.setText(messageDto.getSenderUserName());
            subject.setText(messageDto.getSubject());
            content.setText(messageDto.getContent());

            setGraphic(container);

        }


    }
}
