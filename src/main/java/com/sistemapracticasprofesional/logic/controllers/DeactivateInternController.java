package com.sistemapracticasprofesional.logic.controllers;

import java.util.ArrayList;
import java.util.List;

import com.sistemapracticasprofesional.logic.dao.InternDao;
import com.sistemapracticasprofesional.logic.dao.UserDao;
import com.sistemapracticasprofesional.logic.dto.InternDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.exception.DaoException;

public class DeactivateInternController {

    private InternDao internDao = new InternDao();
    private UserDao userDao = new UserDao();
    
    public List<InternDto> getListInterns(){

        List<InternDto> list = internDao.getInternsActive();

        return list;

    }

    public boolean deactivateInertn (int userId) 
    throws BusinessLogicException{

        boolean result = false;

        try{
            
            result = userDao.deactivateUser(userId);

        } catch (DaoException e){

            throw new BusinessLogicException("No se pudo desactiva el practicante");

        } 

        return result;
    }

    
}
