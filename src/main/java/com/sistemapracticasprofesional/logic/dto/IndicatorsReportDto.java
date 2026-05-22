package com.sistemapracticasprofesional.logic.dto;

public class IndicatorsReportDto {

    private int totalActiveInterns;
    private int totalInactiveInterns;
    private int internsWithProject;
    private int internsWithoutProject;
    private float averageMonthlyScore;
    private int totalProjects;
    private int totalAffiliatedOrganizations;

    public IndicatorsReportDto() {
    }

    public int getTotalActiveInterns() {
        return totalActiveInterns;
    }

    public void setTotalActiveInterns(int totalActiveInterns) {
        this.totalActiveInterns = totalActiveInterns;
    }

    public int getTotalInactiveInterns() {
        return totalInactiveInterns;
    }

    public void setTotalInactiveInterns(int totalInactiveInterns) {
        this.totalInactiveInterns = totalInactiveInterns;
    }

    public int getInternsWithProject() {
        return internsWithProject;
    }

    public void setInternsWithProject(int internsWithProject) {
        this.internsWithProject = internsWithProject;
    }

    public int getInternsWithoutProject() {
        return internsWithoutProject;
    }

    public void setInternsWithoutProject(int internsWithoutProject) {
        this.internsWithoutProject = internsWithoutProject;
    }

    public float getAverageMonthlyScore() {
        return averageMonthlyScore;
    }

    public void setAverageMonthlyScore(float averageMonthlyScore) {
        this.averageMonthlyScore = averageMonthlyScore;
    }

    public int getTotalProjects() {
        return totalProjects;
    }

    public void setTotalProjects(int totalProjects) {
        this.totalProjects = totalProjects;
    }

    public int getTotalAffiliatedOrganizations() {
        return totalAffiliatedOrganizations;
    }

    public void setTotalAffiliatedOrganizations(int totalAffiliatedOrganizations) {
        this.totalAffiliatedOrganizations = totalAffiliatedOrganizations;
    }
}
