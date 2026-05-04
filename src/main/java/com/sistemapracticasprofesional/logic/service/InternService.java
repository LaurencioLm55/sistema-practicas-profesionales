package com.sistemapracticasprofesional.logic.service;

import com.sistemapracticasprofesional.logic.dao.InternDao;
import com.sistemapracticasprofesional.logic.dto.InternDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import com.sistemapracticasprofesional.logic.exception.ServiceException;
import com.sistemapracticasprofesional.logic.exception.ValidationException;
import com.sistemapracticasprofesional.logic.validators.RegistrationValidator;

public class InternService {

    private final InternDao internDao = new InternDao();

    public void registerIntern(InternDto internDto)
            throws ValidationException, ServiceException {

        validateIntern(internDto);

        try {
            boolean registered = internDao.insertIntern(internDto);

            if (!registered) {
                throw new ServiceException("No se pudo registrar el practicante.", null);
            }

        } catch (DaoException e) {
            throw new ServiceException("Ocurrio un error al registrar el practicante.", e);
        }
    }

    private void validateIntern(InternDto internDto)
            throws ValidationException {

        RegistrationValidator.validateIntern(internDto);
    }
}
