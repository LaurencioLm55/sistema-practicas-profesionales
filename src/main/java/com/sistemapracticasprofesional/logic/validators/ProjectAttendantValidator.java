package com.sistemapracticasprofesional.logic.validators;

import com.sistemapracticasprofesional.logic.dto.ProjectAttendantDto;

public class ProjectAttendantValidator {

    private ProjectAttendantDto projectAttendantDto;

    public ProjectAttendantValidator () {
    }
    
    public ProjectAttendantValidator (ProjectAttendantDto projectAttendantDto) {

        this.projectAttendantDto = projectAttendantDto;

    }

    public boolean isProjectAttendantValid(){

        boolean result = false;

        if ( isNameProjectAttendant(projectAttendantDto.getProjectAttendantName())){

            if ( isPositionProjectAttendant(projectAttendantDto.getProjectAttendantPosition())){

                if ( isEmailProjectAttendant(projectAttendantDto.getProjectAttendantEmail())) {
                    
                    result = true;

                }

            }

        }

        return result;
        
    }
  
    public boolean isNameProjectAttendant (String nameProjectAttendant) {

        boolean result = false;

        if (nameProjectAttendant != null && !nameProjectAttendant.isBlank()){
            
            if (nameProjectAttendant.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")){

                result = true;

            }

        }

        return result;

    }

    public boolean isPositionProjectAttendant (String positionProjectAttendant) {

        boolean result = false;

        if (positionProjectAttendant != null && !positionProjectAttendant.isBlank()) {

            if (positionProjectAttendant.matches("[a-zA-ZáéíóúÁÉÍÓÚ ]+")) {

                result = true;

            }

        }

        return result;

    }

    public boolean isEmailProjectAttendant (String emailProjectAttendant) {

        boolean result = false;

        if (emailProjectAttendant != null && !emailProjectAttendant.isBlank()) {
            
            if (emailProjectAttendant.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                
                result = true;
            
            }

        }

        return result;
    }

}
