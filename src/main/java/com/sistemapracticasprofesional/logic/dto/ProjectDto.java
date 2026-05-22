package com.sistemapracticasprofesional.logic.dto;

public class ProjectDto {
    private int projectId;
    private int linkedOrganizationId;
    private String projectName;
    private String projectDescription;
    private String projectMethodology;
    private String projectResources;
    private String midtermProjectObjectives;
    private String generalProjectObjectives;
    private String inmediateProjectObjectives;
    private String projectResponsabilities;
    private String projectAttendantName;
    private String projectAttendantEmail;
    private String projectAttendantPosition;

    public ProjectDto() {
    }
    
    public int getProjectId() {
        return projectId;
    }

    public int getLinkedOrganizationId() {
        return linkedOrganizationId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public String getProjectMethodology() {
        return projectMethodology;
    }

    public String getProjectResources() {
        return projectResources;
    }

    public String getMidtermProjectObjectives() {
        return midtermProjectObjectives;
    }

    public String getGeneralProjectObjectives() {
        return generalProjectObjectives;
    }

    public String getInmediateProjectObjectives() {
        return inmediateProjectObjectives;
    }

    public String getProjectResponsabilities() {
        return projectResponsabilities;
    }

    public String getProjectAttendantName() {
        return projectAttendantName;
    }

    public String getProjectAttendantEmail() {
        return projectAttendantEmail;
    }

    public String getProjectAttendantPosition() {
        return projectAttendantPosition;
    }


    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setLinkedOrganizationId(int linkedOrganizationId) {
        this.linkedOrganizationId = linkedOrganizationId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public void setProjectMethodology(String projectMethodology) {
        this.projectMethodology = projectMethodology;
    }

    public void setProjectResources(String projectResources) {
        this.projectResources = projectResources;
    }

    public void setMidtermProjectObjectives(String midtermProjectObjectives) {
        this.midtermProjectObjectives = midtermProjectObjectives;
    }

    public void setGeneralProjectObjectives(String generalProjectObjectives) {
        this.generalProjectObjectives = generalProjectObjectives;
    }

    public void setInmediateProjectObjectives(String inmediateProjectObjectives) {
        this.inmediateProjectObjectives = inmediateProjectObjectives;
    }

    public void setProjectResponsabilities(String projectResponsabilities) {
        this.projectResponsabilities = projectResponsabilities;
    }

    public void setProjectAttendantName(String projectAttendantName) {
        this.projectAttendantName = projectAttendantName;
    }

    public void setProjectAttendantEmail(String projectAttendantEmail) {
        this.projectAttendantEmail = projectAttendantEmail;
    }

    public void setProjectAttendantPosition(String projectAttendantPosition) {
        this.projectAttendantPosition = projectAttendantPosition;
    }

    public void setProjectAttendantSchedule(String projectAttendantPosition) {
        this.projectAttendantPosition = projectAttendantPosition;
    }

    @Override
    public String toString(){
        return projectAttendantName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + projectId;
        result = prime * result + linkedOrganizationId;
        result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
        result = prime * result + ((projectDescription == null) ? 0 : projectDescription.hashCode());
        result = prime * result + ((projectMethodology == null) ? 0 : projectMethodology.hashCode());
        result = prime * result + ((projectResources == null) ? 0 : projectResources.hashCode());
        result = prime * result + ((midtermProjectObjectives == null) ? 0 : midtermProjectObjectives.hashCode());
        result = prime * result + ((generalProjectObjectives == null) ? 0 : generalProjectObjectives.hashCode());
        result = prime * result + ((inmediateProjectObjectives == null) ? 0 : inmediateProjectObjectives.hashCode());
        result = prime * result + ((projectResponsabilities == null) ? 0 : projectResponsabilities.hashCode());
        result = prime * result + ((projectAttendantName == null) ? 0 : projectAttendantName.hashCode());
        result = prime * result + ((projectAttendantEmail == null) ? 0 : projectAttendantEmail.hashCode());
        result = prime * result + ((projectAttendantPosition == null) ? 0 : projectAttendantPosition.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProjectDto other = (ProjectDto) obj;
        if (projectId != other.projectId)
            return false;
        if (linkedOrganizationId != other.linkedOrganizationId)
            return false;
        if (projectName == null) {
            if (other.projectName != null)
                return false;
        } else if (!projectName.equals(other.projectName))
            return false;
        if (projectDescription == null) {
            if (other.projectDescription != null)
                return false;
        } else if (!projectDescription.equals(other.projectDescription))
            return false;
        if (projectMethodology == null) {
            if (other.projectMethodology != null)
                return false;
        } else if (!projectMethodology.equals(other.projectMethodology))
            return false;
        if (projectResources == null) {
            if (other.projectResources != null)
                return false;
        } else if (!projectResources.equals(other.projectResources))
            return false;
        if (midtermProjectObjectives == null) {
            if (other.midtermProjectObjectives != null)
                return false;
        } else if (!midtermProjectObjectives.equals(other.midtermProjectObjectives))
            return false;
        if (generalProjectObjectives == null) {
            if (other.generalProjectObjectives != null)
                return false;
        } else if (!generalProjectObjectives.equals(other.generalProjectObjectives))
            return false;
        if (inmediateProjectObjectives == null) {
            if (other.inmediateProjectObjectives != null)
                return false;
        } else if (!inmediateProjectObjectives.equals(other.inmediateProjectObjectives))
            return false;
        if (projectResponsabilities == null) {
            if (other.projectResponsabilities != null)
                return false;
        } else if (!projectResponsabilities.equals(other.projectResponsabilities))
            return false;
        if (projectAttendantName == null) {
            if (other.projectAttendantName != null)
                return false;
        } else if (!projectAttendantName.equals(other.projectAttendantName))
            return false;
        if (projectAttendantEmail == null) {
            if (other.projectAttendantEmail != null)
                return false;
        } else if (!projectAttendantEmail.equals(other.projectAttendantEmail))
            return false;
        if (projectAttendantPosition == null) {
            if (other.projectAttendantPosition != null)
                return false;
        } else if (!projectAttendantPosition.equals(other.projectAttendantPosition))
            return false;
        return true;
    }

    
}   
