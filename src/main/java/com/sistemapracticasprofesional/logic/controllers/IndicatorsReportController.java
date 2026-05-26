package com.sistemapracticasprofesional.logic.controllers;

import com.itextpdf.text.DocumentException;
import com.sistemapracticasprofesional.logic.dao.IndicatorsReportDao;
import com.sistemapracticasprofesional.logic.dto.IndicatorsReportDto;
import com.sistemapracticasprofesional.logic.exception.BusinessLogicException;
import com.sistemapracticasprofesional.logic.exception.DaoException;
import com.sistemapracticasprofesional.logic.util.PdfReportGenerator;
import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndicatorsReportController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndicatorsReportController.class);

    private final IndicatorsReportDao indicatorsReportDao = new IndicatorsReportDao();

    public IndicatorsReportDto getIndicators() throws BusinessLogicException {

        try {

            return indicatorsReportDao.getIndicators();

        } catch (DaoException e) {

            throw new BusinessLogicException("Ocurrió un error al obtener los indicadores.");

        }

    }

    public void generatePdfReport(IndicatorsReportDto indicators, File outputFile) throws BusinessLogicException {

        try {

            PdfReportGenerator.generate(indicators, outputFile);

        } catch (DocumentException | IOException e) {

            LOGGER.error("Error al generar el PDF", e);
            throw new BusinessLogicException("No se pudo generar el reporte PDF.");

        }

    }

}
