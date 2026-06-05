package com.sistemapracticasprofesional.logic.dto;

import java.util.List;

public class PartialReportPdfData {

    private String internName;
    private String studentId;
    private String educationalExperienceName;
    private String courseRegistrationNumber;
    private String projectName;
    private String organizationName;
    private List<PartialReportActivityDto> activities;

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

    public String getEducationalExperienceName() {
        return educationalExperienceName;
    }

    public void setEducationalExperienceName(String educationalExperienceName) {
        this.educationalExperienceName = educationalExperienceName;
    }

    public String getCourseRegistrationNumber() {
        return courseRegistrationNumber;
    }

    public void setCourseRegistrationNumber(String courseRegistrationNumber) {
        this.courseRegistrationNumber = courseRegistrationNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public List<PartialReportActivityDto> getActivities() {
        return activities;
    }

    public void setActivities(List<PartialReportActivityDto> activities) {
        this.activities = activities;
    }

}
