package com.sistemapracticasprofesional.logic.validators;

import com.sistemapracticasprofesional.logic.dto.MonthlyReportDto;
import com.sistemapracticasprofesional.logic.exception.ValidationException;

public class MonthlyReportValidator {

    public static void validate(MonthlyReportDto monthlyReport) throws ValidationException {

        if (monthlyReport.getInternId() == null || monthlyReport.getInternId().isBlank()) {
            throw new ValidationException("La matrícula del practicante es requerida.");
        }

        if (monthlyReport.getDateOfCompletion() == null) {
            throw new ValidationException("La fecha de realización es requerida.");
        }

        if (monthlyReport.getDeliveryDate() == null) {
            throw new ValidationException("La fecha de entrega es requerida.");
        }

        if (monthlyReport.getDescription() == null || monthlyReport.getDescription().isBlank()) {
            throw new ValidationException("La descripción del reporte es requerida.");
        }

    }

    public static void validateScore(float score) throws ValidationException {

        if (score < 0 || score > 10) {
            throw new ValidationException("La calificación debe estar entre 0 y 10.");
        }

    }

}
