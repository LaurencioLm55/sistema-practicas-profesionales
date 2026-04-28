package com.sistemapracticasprofesional.logic.service;

import com.sistemapracticasprofesional.logic.dao.InternDao;
import com.sistemapracticasprofesional.logic.dto.InternDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import com.sistemapracticasprofesional.logic.exception.ServiceException;
import com.sistemapracticasprofesional.logic.exception.ValidationException;

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

        if (internDto == null) {
            throw new ValidationException("Los datos del practicante son obligatorios.");
        }

        if (isBlank(internDto.getStudentId())) {
            throw new ValidationException("La matricula es obligatoria.");
        }

        if (internDto.getAge() <= 0) {
            throw new ValidationException("La edad debe ser mayor que cero.");
        }

        if (isBlank(internDto.getName())
                || isBlank(internDto.getGender())
                || isBlank(internDto.getMajor())) {
            throw new ValidationException("El nombre, el genero y la carrera son obligatorios.");
        }

        internDto.setStudentId(internDto.getStudentId().trim());
        internDto.setName(internDto.getName().trim());
        internDto.setGender(internDto.getGender().trim());
        internDto.setMajor(internDto.getMajor().trim());

        if (!isBlank(internDto.getIndigenousLanguage())) {
            internDto.setIndigenousLanguage(internDto.getIndigenousLanguage().trim());
        } else {
            internDto.setIndigenousLanguage(null);
        }
    }

    private boolean isBlank(String text) {
        return text == null || text.trim().isEmpty();
    }
}