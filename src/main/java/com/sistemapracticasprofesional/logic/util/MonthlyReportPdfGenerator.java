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
import com.sistemapracticasprofesional.logic.dto.MonthlyReportActivityDto;
import com.sistemapracticasprofesional.logic.dto.MonthlyReportPdfData;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class MonthlyReportPdfGenerator {

    private static final BaseColor HEADER_COLOR = new BaseColor(0, 82, 158);
    private static final BaseColor BORDER_COLOR = new BaseColor(220, 220, 220);
    private static final BaseColor SUBHEADER_COLOR = new BaseColor(200, 220, 240);

    private MonthlyReportPdfGenerator() {

    }

    public static void generate(MonthlyReportPdfData reportData, File outputFile)
            throws DocumentException, IOException {

        Document document = new Document();

        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {

            PdfWriter.getInstance(document, outputStream);
            document.open();

            addTitle(document);
            addGenerationDate(document);
            addInternSection(document, reportData.getInternName(), reportData.getStudentId());
            addDatesSection(document, reportData.getCompletionDate(), reportData.getDeliveryDate());
            addDescriptionSection(document, reportData.getDescription());
            addActivitiesTable(document, reportData.getActivities());

            document.close();

        }

    }

    private static void addTitle(Document document) throws DocumentException {

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.WHITE);
        Paragraph title = new Paragraph("Reporte Mensual de Prácticas Profesionales", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);

        PdfPTable titleTable = new PdfPTable(1);
        titleTable.setWidthPercentage(100);
        PdfPCell titleCell = new PdfPCell();
        titleCell.addElement(title);
        titleCell.setBackgroundColor(HEADER_COLOR);
        titleCell.setPadding(14);
        titleCell.setBorder(PdfPCell.NO_BORDER);
        titleTable.addCell(titleCell);

        document.add(titleTable);
        document.add(new Paragraph(" "));

    }

    private static void addGenerationDate(Document document) throws DocumentException {

        Font dateFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.GRAY);
        Paragraph date = new Paragraph("Fecha de generación: " + LocalDate.now(), dateFont);
        date.setAlignment(Element.ALIGN_RIGHT);
        document.add(date);
        document.add(new Paragraph(" "));

    }

    private static void addInternSection(Document document, String internName, String studentId)
            throws DocumentException {

        addSectionHeader(document, "Datos del Practicante");
        addInfoRow(document, "Nombre:", internName);
        addInfoRow(document, "Matrícula:", studentId);
        document.add(new Paragraph(" "));

    }

    private static void addDatesSection(Document document, LocalDate completionDate, LocalDate deliveryDate)
            throws DocumentException {

        addSectionHeader(document, "Fechas del Reporte");
        addInfoRow(document, "Fecha de realización:", dateToString(completionDate));
        addInfoRow(document, "Fecha de entrega:", dateToString(deliveryDate));
        document.add(new Paragraph(" "));

    }

    private static void addDescriptionSection(Document document, String description) throws DocumentException {

        addSectionHeader(document, "Descripción General");

        String safeDescription = nullSafeText(description);
        Font valueFont = FontFactory.getFont(FontFactory.HELVETICA, 11, BaseColor.DARK_GRAY);
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase(safeDescription, valueFont));
        cell.setPadding(8);
        cell.setBorder(PdfPCell.BOX);
        cell.setBorderColor(BORDER_COLOR);
        table.addCell(cell);
        document.add(table);
        document.add(new Paragraph(" "));

    }

    private static void addSectionHeader(Document document, String sectionTitle) throws DocumentException {

        Font sectionFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, HEADER_COLOR);
        PdfPTable sectionTable = new PdfPTable(1);
        sectionTable.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase(sectionTitle, sectionFont));
        cell.setBackgroundColor(SUBHEADER_COLOR);
        cell.setPadding(6);
        cell.setBorder(PdfPCell.NO_BORDER);
        sectionTable.addCell(cell);
        document.add(sectionTable);

    }

    private static void addInfoRow(Document document, String label, String value) throws DocumentException {

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{2f, 5f});

        Font labelFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, BaseColor.DARK_GRAY);
        Font valueFont = FontFactory.getFont(FontFactory.HELVETICA, 11, BaseColor.DARK_GRAY);

        PdfPCell labelCell = new PdfPCell(new Phrase(label, labelFont));
        labelCell.setPadding(5);
        labelCell.setBorder(PdfPCell.BOX);
        labelCell.setBorderColor(BORDER_COLOR);
        table.addCell(labelCell);

        PdfPCell valueCell = new PdfPCell(new Phrase(value, valueFont));
        valueCell.setPadding(5);
        valueCell.setBorder(PdfPCell.BOX);
        valueCell.setBorderColor(BORDER_COLOR);
        table.addCell(valueCell);

        document.add(table);

    }

    private static void addActivitiesTable(Document document, List<MonthlyReportActivityDto> activities)
            throws DocumentException {

        addSectionHeader(document, "Actividades del Mes");
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{2f, 5f});

        addHeaderCell(table, "Actividad");
        addHeaderCell(table, "Descripción");

        Font rowFont = FontFactory.getFont(FontFactory.HELVETICA, 11, BaseColor.DARK_GRAY);

        for (MonthlyReportActivityDto activity : activities) {

            addDataCell(table, activity.getActivityName(), rowFont);
            addDataCell(table, activity.getDescription(), rowFont);

        }

        document.add(table);

    }

    private static void addHeaderCell(PdfPTable table, String text) {

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, BaseColor.WHITE);
        PdfPCell cell = new PdfPCell(new Phrase(text, headerFont));
        cell.setBackgroundColor(HEADER_COLOR);
        cell.setPadding(7);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

    }

    private static void addDataCell(PdfPTable table, String text, Font font) {

        String safeText = nullSafeText(text);
        PdfPCell cell = new PdfPCell(new Phrase(safeText, font));
        cell.setPadding(6);
        cell.setBorder(PdfPCell.BOX);
        cell.setBorderColor(BORDER_COLOR);
        table.addCell(cell);

    }

    private static String nullSafeText(String text) {

        if (text == null) {
            return "";
        }

        return text;

    }

    private static String dateToString(LocalDate date) {

        if (date == null) {
            return "";
        }

        return date.toString();

    }

}
