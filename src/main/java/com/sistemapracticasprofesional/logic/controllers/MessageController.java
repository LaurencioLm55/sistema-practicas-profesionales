package com.sistemapracticasprofesional.logic.controllers;

import java.util.ArrayList;
import java.util.List;

import com.sistemapracticasprofesional.logic.dao.MessageDao;
import com.sistemapracticasprofesional.logic.dao.UserDao;
import com.sistemapracticasprofesional.logic.dto.MessageDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import com.sistemapracticasprofesional.logic.validators.MessageValidator;
import java.time.LocalDate;

public class MessageController {

    private MessageDao messageDao = new MessageDao();
    private MessageValidator messageValidator = new MessageValidator();

    public List<MessageDto> getMessages(int userId) throws BusinessLogicException{
        
        List<MessageDto> listMessages = new ArrayList<>();

        try{

            listMessages = messageDao.getInboxByUserId(userId);

        }catch (DaoException e){

            throw new BusinessLogicException("No se pudo recuperar los mensajes", null);

        }

        return listMessages;

    }

    public String sendMessage(MessageDto messageDto) 
    throws BusinessLogicException{

        String result = "";

        try{
            if (!messageValidator.isRecipientMessageValid(messageDto.getReciverUserName())) {

                result = "El nombre de usuario no es valido";

            } else {
                if (messageValidator.isMessageValid(messageDto)) {
                    
                    fillInformation(messageDto);

                    if (messageDao.insertMessage(messageDto)) {

                        result = "Mesaje enviado con exito";
                    
                    }

                } else {

                    result = "El formato no es correcto";
                
                }
            
            }

        } catch (DaoException e){

            throw new BusinessLogicException("No se pudo enviar el mensaje");

        }
        
        return result;

    }

    private void fillInformation(MessageDto messageDto) 
    throws BusinessLogicException{
        
        UserDao userDao = new UserDao();

        try {

            messageDto.setReceiverUserId(userDao.getIdUserByUserName(messageDto.getReciverUserName()));
            messageDto.setMessageDate(LocalDate.now());

        } catch (DaoException e) {
            
            throw new BusinessLogicException("No se pudo enviar el mensaje");

        }
        
    }

    public MessageDto openMessage(int messageId)
    throws BusinessLogicException{

        MessageDto messageDto;

        System.out.println(messageId);

        try{

            messageDto = messageDao.getMessageById(messageId);

            

        } catch (DaoException e){

           throw new BusinessLogicException("No se pudo recuperar el mensaje", e);

        }

        return messageDto;

    }

    
}
