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
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

public class PdfReportGenerator {

    private static final BaseColor HEADER_COLOR = new BaseColor(0, 82, 158);
    private static final BaseColor ROW_ALT_COLOR = new BaseColor(240, 244, 248);

    public static void generate(IndicatorsReportDto indicators, String filePath)
            throws DocumentException, IOException {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        addTitle(document);
        addDate(document);
        addIndicatorsTable(document, indicators);

        document.close();
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

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, BaseColor.WHITE);
        Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 11, BaseColor.BLACK);
        Font valueFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, BaseColor.BLACK);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{3f, 1f});

        addHeaderCell(table, "Indicador", headerFont);
        addHeaderCell(table, "Valor", headerFont);

        addRow(table, "Practicantes activos", String.valueOf(indicators.getTotalActiveInterns()), cellFont, valueFont, false);
        addRow(table, "Practicantes inactivos", String.valueOf(indicators.getTotalInactiveInterns()), cellFont, valueFont, true);
        addRow(table, "Practicantes con proyecto asignado", String.valueOf(indicators.getInternsWithProject()), cellFont, valueFont, false);
        addRow(table, "Practicantes sin proyecto asignado", String.valueOf(indicators.getInternsWithoutProject()), cellFont, valueFont, true);
        addRow(table, "Promedio de calificaciones (reportes mensuales)",
                String.format("%.2f", indicators.getAverageMonthlyScore()), cellFont, valueFont, false);
        addRow(table, "Proyectos registrados", String.valueOf(indicators.getTotalProjects()), cellFont, valueFont, true);
        addRow(table, "Organizaciones vinculadas", String.valueOf(indicators.getTotalAffiliatedOrganizations()), cellFont, valueFont, false);

        document.add(table);
    }

    private static void addHeaderCell(PdfPTable table, String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBackgroundColor(HEADER_COLOR);
        cell.setPadding(8);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

    private static void addRow(PdfPTable table, String label, String value,
            Font labelFont, Font valueFont, boolean alternate) {
        BaseColor bg = alternate ? ROW_ALT_COLOR : BaseColor.WHITE;

        PdfPCell labelCell = new PdfPCell(new Phrase(label, labelFont));
        labelCell.setBackgroundColor(bg);
        labelCell.setPadding(8);
        table.addCell(labelCell);

        PdfPCell valueCell = new PdfPCell(new Phrase(value, valueFont));
        valueCell.setBackgroundColor(bg);
        valueCell.setPadding(8);
        valueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(valueCell);
    }
}
