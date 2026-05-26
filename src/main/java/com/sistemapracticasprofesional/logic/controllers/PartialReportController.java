package com.sistemapracticasprofesional.logic.controllers;

import com.sistemapracticasprofesional.logic.dao.PartialReportDao;
import com.sistemapracticasprofesional.logic.dto.PartialReportDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import com.sistemapracticasprofesional.logic.exception.ValidationException;
import com.sistemapracticasprofesional.logic.validators.PartialReport;

public class PartialReportController {

    private final PartialReportDao partialReportDao = new PartialReportDao();

    public void registerPartialReport(PartialReportDto partialReport) throws BusinessLogicException {

        try {

            PartialReport.validate(partialReport);
            partialReportDao.insertPartialReport(partialReport);

        } catch (ValidationException e) {

            throw new BusinessLogicException(e.getMessage());

        } catch (DaoException e) {

            throw new BusinessLogicException("Ocurrió un error al registrar el reporte parcial.");

        }

    }

}
