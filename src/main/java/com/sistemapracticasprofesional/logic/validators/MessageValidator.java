package com.sistemapracticasprofesional.logic.validators;

import com.sistemapracticasprofesional.logic.dao.UserDao;
import com.sistemapracticasprofesional.logic.dto.MessageDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.exception.DaoException;

public class MessageValidator {
    
    public MessageValidator(){
    }

    public boolean isMessageValid(MessageDto messageDto){

        boolean result = false;

        if (isContentMessageValid(messageDto.getContent())){
            if (isSubjectValid(messageDto.getSubject())) {
                result = true;
            }
        }

        return result;
    }

    public boolean isContentMessageValid(String contentMessage){

        boolean result = false;

        if ( contentMessage != null && !contentMessage.isBlank()){

            if ( contentMessage.matches("[\\s\\S-a-zA-ZáéíóúÁÉÍÓÚñÑ0-9!$%&/()=#?¿¡.,:_{};<>\\\\ ]+") ){
                
                result = true;

            }

        }

        return result;

    }

    public boolean isSubjectValid(String subject){

        boolean result = false;

        if ( subject != null && !subject.isBlank()){

            if ( subject.trim().length() <= 70){

                if ( subject.matches("[\\s\\S-a-zA-ZáéíóúÁÉÍÓÚñÑ0-9!$%&/()=#?¿¡.,:_{};<>\\\\ ]+") ){
                
                    result = true;

                }

            }
            
        }

        return result;

    }

    public boolean isRecipientMessageValid(String nameUser) throws BusinessLogicException{

        boolean result = false;
        UserDao userDao = new UserDao();

        try{

            if(userDao.isRecipientRegistred(nameUser)){

                result = true;

            }

        } catch (DaoException e){

            throw new BusinessLogicException("No se pudo encontrar el usuario");

        }

        return result;

    }
}
