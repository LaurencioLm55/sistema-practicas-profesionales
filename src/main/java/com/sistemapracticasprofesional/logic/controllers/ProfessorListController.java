package com.sistemapracticasprofesional.logic.controllers;

import com.sistemapracticasprofesional.logic.dao.ProfessorDao;
import com.sistemapracticasprofesional.logic.dto.ProfessorDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.util.List;

public class ProfessorListController {

    private final ProfessorDao professorDao = new ProfessorDao();

    public List<ProfessorDto> getAllProfessors() throws BusinessLogicException {
        try {
            return professorDao.getAllProfessors();
        } catch (DaoException e) {
            throw new BusinessLogicException("Ocurrió un error al recuperar la lista de profesores.");
        }
    }
}