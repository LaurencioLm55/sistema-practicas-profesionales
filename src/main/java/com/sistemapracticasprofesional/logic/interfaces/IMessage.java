package com.sistemapracticasprofesional.logic.interfaces;

import com.sistemapracticasprofesional.logic.dto.MessageDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.util.List;

public interface IMessage {

    boolean insertMessage(MessageDto message) throws DaoException;

    MessageDto getMessageById(int messageId) throws DaoException;

    List<MessageDto> getInboxByUserId(int receiverUserId) throws DaoException;

    List<MessageDto> getSentMessagesByUserId(int senderUserId) throws DaoException;

    boolean deleteMessage(int messageId) throws DaoException;
    
}
