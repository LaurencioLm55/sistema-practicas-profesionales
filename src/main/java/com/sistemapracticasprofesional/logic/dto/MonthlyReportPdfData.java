package com.sistemapracticasprofesional.logic.dto;

import java.time.LocalDate;
import java.util.List;

public class MonthlyReportPdfData {

    private String internName;
    private String studentId;
    private LocalDate completionDate;
    private LocalDate deliveryDate;
    private String description;
    private List<MonthlyReportActivityDto> activities;

    public String getInternName() {
        return internName;
    }

    public void setInternName(String internName) {
        this.internName = internName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MonthlyReportActivityDto> getActivities() {
        return activities;
    }

    public void setActivities(List<MonthlyReportActivityDto> activities) {
        this.activities = activities;
    }

}
