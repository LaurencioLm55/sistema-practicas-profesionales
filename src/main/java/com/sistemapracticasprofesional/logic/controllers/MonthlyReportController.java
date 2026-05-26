package com.sistemapracticasprofesional.logic.controllers;

import com.sistemapracticasprofesional.logic.dao.MonthlyReportDao;
import com.sistemapracticasprofesional.logic.dto.MonthlyReportDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import com.sistemapracticasprofesional.logic.exception.ValidationException;
import com.sistemapracticasprofesional.logic.validators.MonthlyReportValidator;

public class MonthlyReportController {

    private final MonthlyReportDao monthlyReportDao = new MonthlyReportDao();

    public void registerMonthlyReport(MonthlyReportDto monthlyReport) throws BusinessLogicException {

        try {

            MonthlyReportValidator.validate(monthlyReport);
            monthlyReportDao.insertMonthlyReport(monthlyReport);

        } catch (ValidationException e) {

            throw new BusinessLogicException(e.getMessage());

        } catch (DaoException e) {

            throw new BusinessLogicException("Ocurrió un error al registrar el reporte mensual.");

        }

    }

    public void gradeMonthlyReport(int reportId, float score) throws BusinessLogicException {

        try {

            MonthlyReportValidator.validateScore(score);
            monthlyReportDao.updateMonthlyReport("Calificacion", String.valueOf(score), reportId);

        } catch (ValidationException e) {

            throw new BusinessLogicException(e.getMessage());

        } catch (DaoException e) {

            throw new BusinessLogicException("Ocurrió un error al calificar el reporte mensual.");

        }

    }

}
