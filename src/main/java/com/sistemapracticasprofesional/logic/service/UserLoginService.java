package com.sistemapracticasprofesional.logic.service;

import com.sistemapracticasprofesional.logic.dao.UserDao;
import com.sistemapracticasprofesional.logic.dto.UserDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.validators.UserValidator;
import com.sistemapracticasprofesional.presentation.util.Navigation;

public class UserLoginService {

    private String resourcePath;
    private String namePath;
    private String title;
    private UserDao userDao = new UserDao();
    private UserValidator validator;

    public String logginUser(UserDto userDto) throws BusinessLogicException{
        
        String type = null;

        validator = new UserValidator(userDto);
        

        if(validator.isUserValid() == true){
           
            if(userDao.isUserRegistred(userDto) == true){
                
                userDto.setIdUser(userDao.getIdUser(userDto));

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
