package com.sistemapracticasprofesional.logic.controllers;

import com.sistemapracticasprofesional.logic.dto.ProjectAttendantDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.validators.ProjectAttendantValidator;
import com.sistemapracticasprofesional.logic.dao.ProjectAttendantDao;

public class RegistrerProjectAttendantController {

    private ProjectAttendantValidator validator = new ProjectAttendantValidator();
    private ProjectAttendantDao projectAttendantDao = new ProjectAttendantDao();

    public void registrerProjectAttendant(ProjectAttendantDto projectAttendantDto) throws BusinessLogicException{

        if ( validator.isProjectAttendantValid() ){

            projectAttendantDao.insertProjectAttendant(projectAttendantDto);

        }else{

            throw new BusinessLogicException("Hay campos invalidos");

        }
        
    }
}
