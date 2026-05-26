package com.sistemapracticasprofesional.logic.controllers;

import com.sistemapracticasprofesional.logic.dao.InternDao;
import com.sistemapracticasprofesional.logic.dto.InternDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import com.sistemapracticasprofesional.logic.exception.ControllerException;
import com.sistemapracticasprofesional.logic.exception.ValidationException;
import com.sistemapracticasprofesional.logic.validators.RegistrationValidator;

public class InternController {

    private final InternDao internDao = new InternDao();

    public void registerIntern(InternDto internDto)
            throws ValidationException, ControllerException {

        validateIntern(internDto);

        try {
            boolean registered = internDao.insertIntern(internDto);

            if (!registered) {

                throw new ControllerException("No se pudo registrar el practicante.",
                 null);

            }

        } catch (DaoException e) {

            throw new ControllerException("Ocurrio un error al registrar el practicante."
            , e);

        }
    }

    public InternDto getInternByUserId(int userId) throws BusinessLogicException {

        try {

            return internDao.getInternByUserId(userId);

        } catch (DaoException e) {

            throw new BusinessLogicException("Ocurrió un error al obtener los datos del practicante.");

        }

    }

    private void validateIntern(InternDto internDto)
            throws ValidationException {

        RegistrationValidator.validateIntern(internDto);

    }
}
