package com.sistemapracticasprofesional.logic.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sistemapracticasprofesional.logic.dto.IndicatorsReportDto;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

public class PdfReportGenerator {

    private static final BaseColor HEADER_COLOR = new BaseColor(0, 82, 158);
    private static final BaseColor BORDER_COLOR = new BaseColor(220, 220, 220);

    private PdfReportGenerator() {

    }

    public static void generate(IndicatorsReportDto indicators, File outputFile)
            throws DocumentException, IOException {

        Document document = new Document();

        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {

            PdfWriter.getInstance(document, outputStream);
            document.open();

            addTitle(document);
            addDate(document);
            addIndicatorsTable(document, indicators);

            document.close();

        }

    }

    private static void addTitle(Document document) throws DocumentException {

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.WHITE);
        Paragraph title = new Paragraph("Reporte de Indicadores\nSistema de Prácticas Profesionales", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);

        PdfPTable titleTable = new PdfPTable(1);
        titleTable.setWidthPercentage(100);
        PdfPCell titleCell = new PdfPCell();
        titleCell.addElement(title);
        titleCell.setBackgroundColor(HEADER_COLOR);
        titleCell.setPadding(16);
        titleCell.setBorder(PdfPCell.NO_BORDER);
        titleTable.addCell(titleCell);

        document.add(titleTable);
        document.add(new Paragraph(" "));

    }

    private static void addDate(Document document) throws DocumentException {

        Font dateFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.GRAY);
        Paragraph date = new Paragraph("Fecha de generación: " + LocalDate.now(), dateFont);
        date.setAlignment(Element.ALIGN_RIGHT);
        document.add(date);
        document.add(new Paragraph(" "));

    }

    private static void addIndicatorsTable(Document document, IndicatorsReportDto indicators)
            throws DocumentException {

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{3f, 1f});

        addHeaderCell(table, "Indicador");
        addHeaderCell(table, "Valor");

        addIndicatorRow(table, "Practicantes activos", String.valueOf(indicators.getTotalActiveInterns()));
        addIndicatorRow(table, "Practicantes inactivos", String.valueOf(indicators.getTotalInactiveInterns()));
        addIndicatorRow(table, "Con proyecto asignado", String.valueOf(indicators.getInternsWithProject()));
        addIndicatorRow(table, "Sin proyecto asignado", String.valueOf(indicators.getInternsWithoutProject()));
        addIndicatorRow(table, "Proyectos registrados", String.valueOf(indicators.getTotalProjects()));
        addIndicatorRow(table, "Organizaciones vinculadas", String.valueOf(indicators.getTotalAffiliatedOrganizations()));
        addIndicatorRow(table, "Promedio de calificaciones mensuales", String.format("%.2f", indicators.getAverageMonthlyScore()));

        document.add(table);

    }

    private static void addHeaderCell(PdfPTable table, String text) {

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);
        PdfPCell cell = new PdfPCell(new Phrase(text, headerFont));
        cell.setBackgroundColor(HEADER_COLOR);
        cell.setPadding(8);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

    }

    private static void addIndicatorRow(PdfPTable table, String indicatorLabel, String indicatorValue) {

        Font rowFont = FontFactory.getFont(FontFactory.HELVETICA, 11, BaseColor.DARK_GRAY);

        PdfPCell labelCell = new PdfPCell(new Phrase(indicatorLabel, rowFont));
        labelCell.setPadding(8);
        labelCell.setBorder(PdfPCell.BOX);
        labelCell.setBorderColor(BORDER_COLOR);
        table.addCell(labelCell);

        PdfPCell valueCell = new PdfPCell(new Phrase(indicatorValue, rowFont));
        valueCell.setPadding(8);
        valueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        valueCell.setBorder(PdfPCell.BOX);
        valueCell.setBorderColor(BORDER_COLOR);
        table.addCell(valueCell);

    }

}
