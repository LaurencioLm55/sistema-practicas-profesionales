package com.sistemapracticasprofesional.logic.controllers;

import com.sistemapracticasprofesional.logic.dao.UserDao;
import com.sistemapracticasprofesional.logic.dto.UserDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.util.PasswordUtils;
import com.sistemapracticasprofesional.presentation.util.UserSession;


public class UserLoginController {

    private UserDao userDao = new UserDao();

    public String logginUser(UserDto userDto) throws BusinessLogicException{
        
        String type = null;

        userDto.setPassword(PasswordUtils.hashPassword(userDto.getPassword()));
           
        if(userDao.isUserRegistred(userDto) == true){
                
            userDto.setIdUser(userDao.getIdUser(userDto));

            type = userDao.getUserType(userDto);

            UserSession.getInstance().initializeSession(userDto, type);

        }else{

            throw new BusinessLogicException("El usuario o contreaseña no son validos");

        }

        return type;

    }


}