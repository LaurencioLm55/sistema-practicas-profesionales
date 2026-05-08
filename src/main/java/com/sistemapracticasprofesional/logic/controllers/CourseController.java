package com.sistemapracticasprofesional.logic.controllers;

import com.sistemapracticasprofesional.logic.dao.CourseDao;
import com.sistemapracticasprofesional.logic.dto.CourseDto;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import com.sistemapracticasprofesional.logic.exception.ControllerException;
import com.sistemapracticasprofesional.logic.exception.ValidationException;

public class CourseController {

    private final CourseDao courseDao = new CourseDao();

    public void registerCourse(CourseDto courseDto) throws ValidationException, ControllerException {
        validateCourse(courseDto);

        try {
            boolean registered = courseDao.registerCourse(courseDto);

            if (!registered) {
                throw new ControllerException("No se pudo registrar la experiencia educativa.");
            }

        } catch (DaoException e) {
            throw new ControllerException("Ocurrio un error al registrar la experiencia educativa.", e);
        }
    }

    private void validateCourse(CourseDto courseDto) throws ValidationException {
        if (courseDto == null) {
            throw new ValidationException("Los datos de la experiencia educativa son obligatorios.");
        }

        if (courseDto.getNrc() <= 0) {
            throw new ValidationException("El NRC debe ser mayor que cero.");
        }

        if (courseDto.getStaffNumber() <= 0) {
            throw new ValidationException("El numero de personal debe ser mayor que cero.");
        }

        if (isBlank(courseDto.getStatus())
                || isBlank(courseDto.getPeriod())
                || isBlank(courseDto.getSection())) {
            throw new ValidationException("El estado, el periodo y la seccion son obligatorios.");
        }

        courseDto.setStatus(courseDto.getStatus().trim());
        courseDto.setPeriod(courseDto.getPeriod().trim());
        courseDto.setSection(courseDto.getSection().trim());
    }

    private boolean isBlank(String text) {
        return text == null || text.trim().isEmpty();
    }
}