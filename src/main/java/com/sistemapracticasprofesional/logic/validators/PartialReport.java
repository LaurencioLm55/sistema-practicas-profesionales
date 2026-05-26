package com.sistemapracticasprofesional.logic.validators;

import com.sistemapracticasprofesional.logic.dto.PartialReportDto;
import com.sistemapracticasprofesional.logic.exception.ValidationException;

public class PartialReport {

    public static void validate(PartialReportDto partialReport) throws ValidationException {

        if (partialReport.getPlannedTime() == null || partialReport.getPlannedTime().isBlank()) {
            throw new ValidationException("El tiempo planeado es requerido.");
        }

        if (partialReport.getRealTime() == null || partialReport.getRealTime().isBlank()) {
            throw new ValidationException("El tiempo real es requerido.");
        }

        if (partialReport.getResults() == null || partialReport.getResults().isBlank()) {
            throw new ValidationException("Los resultados son requeridos.");
        }

        if (partialReport.getObservations() == null || partialReport.getObservations().isBlank()) {
            throw new ValidationException("Las observaciones son requeridas.");
        }

    }

}
