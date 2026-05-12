package com.sistemapracticasprofesional.logic.dto;

public class ProjectAttendantDto {
    private int IdProjectAttendant;
    private String projectAttendantName;
    private String projectAttendantPosition; 
    private String projectAttendantEmail;

    public ProjectAttendantDto(){

    }

    public ProjectAttendantDto(int IdProjectAttendant, String projectAttendantName, String projectAttendantPosition, String projectAttendantEmail){
        
        this.IdProjectAttendant = IdProjectAttendant;
        this.projectAttendantName = projectAttendantName;
        this.projectAttendantPosition = projectAttendantPosition;
        this.projectAttendantEmail = projectAttendantEmail;

    }

     public int getIdProjectAttendant() {
        return IdProjectAttendant;
    }

    public void setIdProjectAttendant(int idProjectAttendant) {
        IdProjectAttendant = idProjectAttendant;
    }

    public String getProjectAttendantName() {
        return projectAttendantName;
    }

    public void setProjectAttendantName(String projectAttendantName) {
        this.projectAttendantName = projectAttendantName;
    }

    public String getProjectAttendantPosition() {
        return projectAttendantPosition;
    }

    public void setProjectAttendantPosition(String projectAttendantPosition) {
        this.projectAttendantPosition = projectAttendantPosition;
    }

    public String getProjectAttendantEmail() {
        return projectAttendantEmail;
    }

    public void setProjectAttendantEmail(String projectAttendantEmail) {
        this.projectAttendantEmail = projectAttendantEmail;
    }
    
}
