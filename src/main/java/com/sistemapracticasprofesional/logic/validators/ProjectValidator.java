package com.sistemapracticasprofesional.logic.validators;

import com.sistemapracticasprofesional.logic.dto.ProjectDto;
import com.sistemapracticasprofesional.logic.exception.ValidationException;

public class ProjectValidator {

    public static void validateProject(ProjectDto project) throws ValidationException {
        if (project == null) {
            throw new ValidationException("Los datos del proyecto son nulos.");
        }
        if (project.getProjectId() <= 0) {
            throw new ValidationException("El ID del proyecto debe ser un número positivo.");
        }
        if (project.getLinkedOrganizationId() <= 0) {
            throw new ValidationException("El ID de la organización vinculada debe ser un número positivo.");
        }
        if (isBlank(project.getProjectName())) {
            throw new ValidationException("El nombre del proyecto es obligatorio.");
        }
        if (isBlank(project.getGeneralProjectObjectives())) {
            throw new ValidationException("El objetivo general del proyecto es obligatorio.");
        }
    }

    private static boolean isBlank(String text) {
        return text == null || text.trim().isEmpty();
    }
}