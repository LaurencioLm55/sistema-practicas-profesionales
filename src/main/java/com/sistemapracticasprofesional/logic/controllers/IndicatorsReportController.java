package com.sistemapracticasprofesional.logic.controllers;

import com.itextpdf.text.DocumentException;
import com.sistemapracticasprofesional.logic.dao.IndicatorsReportDao;
import com.sistemapracticasprofesional.logic.dto.IndicatorsReportDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import com.sistemapracticasprofesional.logic.util.PdfReportGenerator;
import java.io.IOException;

public class IndicatorsReportController {

    private final IndicatorsReportDao indicatorsReportDao = new IndicatorsReportDao();

    public IndicatorsReportDto getIndicators() throws BusinessLogicException {
        try {
            return indicatorsReportDao.getIndicators();
        } catch (DaoException e) {
            throw new BusinessLogicException("Ocurrió un error al obtener los indicadores.");
        }
    }

    public void generatePdfReport(IndicatorsReportDto indicators, String filePath) throws BusinessLogicException {
        try {
            PdfReportGenerator.generate(indicators, filePath);
        } catch (DocumentException | IOException e) {
            throw new BusinessLogicException("No se pudo generar el reporte PDF.");
        }
    }
}
