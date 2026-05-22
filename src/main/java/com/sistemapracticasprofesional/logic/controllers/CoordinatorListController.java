package com.sistemapracticasprofesional.logic.controllers;

import com.sistemapracticasprofesional.logic.dao.CoordinatorDao;
import com.sistemapracticasprofesional.logic.dto.CoordinatorDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import java.util.List;

public class CoordinatorListController {

    private final CoordinatorDao coordinatorDao = new CoordinatorDao();

    public List<CoordinatorDto> getAllCoordinators() throws BusinessLogicException {
        try {
            return coordinatorDao.getAllCoordinators();
        } catch (DaoException e) {
            throw new BusinessLogicException("Ocurrió un error al recuperar la lista de coordinadores.");
        }
    }
}
