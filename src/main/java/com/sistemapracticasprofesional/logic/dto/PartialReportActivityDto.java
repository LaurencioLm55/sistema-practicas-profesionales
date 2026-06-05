package com.sistemapracticasprofesional.logic.dto;

public class PartialReportActivityDto {

    private String activityName;
    private String description;
    private String plannedTime;
    private String realTime;
    private String month;
    private String week;

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlannedTime() {
        return plannedTime;
    }

    public void setPlannedTime(String plannedTime) {
        this.plannedTime = plannedTime;
    }

    public String getRealTime() {
        return realTime;
    }

    public void setRealTime(String realTime) {
        this.realTime = realTime;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

}
