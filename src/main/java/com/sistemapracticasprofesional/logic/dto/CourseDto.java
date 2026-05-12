package com.sistemapracticasprofesional.logic.dto;

public class CourseDto {
    private int nrc;
    private int staffNumber;
    private String status;
    private String period;
    private String section;
    private String formatFile;
    
    public CourseDto(){
    }

    public CourseDto(int nrc, int staffNumber, String status, String period, String section, String formatFile) {
        this.nrc = nrc;
        this.staffNumber = staffNumber;
        this.status = status;
        this.period = period;
        this.section = section;
        this.formatFile = formatFile;
    }

    public int getNrc() {
        return nrc;
    }

    public void setNrc(int nrc) {
        this.nrc = nrc;
    }

    public int getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(int staffNumber) {
        this.staffNumber = staffNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getFormatFile() {
        return formatFile;
    }

    public void setFormatFile(String formatFile) {
        this.formatFile = formatFile;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        
        result = prime * result + nrc;
        result = prime * result + staffNumber;
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((period == null) ? 0 : period.hashCode());
        result = prime * result + ((section == null) ? 0 : section.hashCode());
        result = prime * result + ((formatFile == null) ? 0 : formatFile.hashCode());

        return result;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }

        if (obj == null){
            return false;
        }
        if (getClass() != obj.getClass()){
            return false;
        }

        CourseDto other = (CourseDto) obj;

        if (nrc != other.nrc){
            return false;
        }

        if (staffNumber != other.staffNumber){
            return false;
        }

        if (status == null) {

            if (other.status != null){
                return false;
            }

        } else if (!status.equals(other.status)){

            return false;

        }
        if (period == null) {

            if (other.period != null){
                return false;
            }

        } else if (!period.equals(other.period)){
         
            return false;
        
        }
        if (section == null) {

            if (other.section != null)
                return false;

        } else if (!section.equals(other.section)){

            return false;

        }
        if (formatFile == null) {

            if (other.formatFile != null){
                return false;
            }

        } else if (!formatFile.equals(other.formatFile)){
         
            return false;

        }
        return true;
    }
    
    
    
}
        
   