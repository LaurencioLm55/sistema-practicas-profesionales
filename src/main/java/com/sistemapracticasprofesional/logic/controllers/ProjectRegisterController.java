package com.sistemapracticasprofesional.logic.controllers;

import com.sistemapracticasprofesional.logic.dao.ProjectDao;
import com.sistemapracticasprofesional.logic.dto.ProjectDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import com.sistemapracticasprofesional.logic.exception.ValidationException;
import com.sistemapracticasprofesional.logic.validators.ProjectValidator;

public class ProjectRegisterController {

    private final ProjectDao projectDao = new ProjectDao();

    public void registerProject(ProjectDto projectDto) throws BusinessLogicException {
        try {
            ProjectValidator.validateProject(projectDto);

            if (projectDao.getProjectById(projectDto.getProjectId()) != null) {
                throw new BusinessLogicException("El ID del proyecto ya existe. Por favor, ingrese uno diferente.");
            }

            boolean registered = projectDao.insertProject(projectDto);
            if (!registered) {
                throw new BusinessLogicException("No se pudo registrar el proyecto por un error inesperado.");
            }
        } catch (ValidationException | DaoException e) {
            throw new BusinessLogicException(e.getMessage(), e);
        }
    }
}
