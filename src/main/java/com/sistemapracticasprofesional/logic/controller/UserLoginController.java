package com.sistemapracticasprofesional.logic.controller;

import com.sistemapracticasprofesional.logic.dao.UserDao;
import com.sistemapracticasprofesional.logic.dto.UserDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.validators.UserValidator;

public class UserLoginController {

    private UserDao userDao = new UserDao();
    private UserValidator validator = new UserValidator(null);

    public String logginUser(UserDto userDto) throws BusinessLogicException{

        String type = null;

        if(validator.isUserValid()){

            if(userDao.isUserRegistred(userDto)){

                type = userDao.getUserType(userDto);

            }else{

                throw new BusinessLogicException("El usuario o contreaseña no son validos");

            }

        }else{

            throw new BusinessLogicException("El usuario o contreaseña no son validos");

        }

        return type;

    }

}
