package com.sistemapracticasprofesional.logic.validators;

public class CourseValidator {

    public CourseValidator(){

    }

    public boolean isNRCValid(String nrc){
        
        boolean result = false;

        if ( nrc != null && !nrc.isBlank() ){
            
            if ( nrc.matches("//d{5}") ){

                result = true;
                
            }

        }

        return result;

    }

    public boolean isPeriodValid(String period){

        boolean result = false;

        if ( period != null && !period.isBlank() ){
            
            if ( period.matches("^[//d{2}]-[//d{2}]-[//d{2}]$") ){

                result = true;
            }

        }

        return result;

    }

    public boolean isSectionValid(String section){

        boolean result = false;

        if ( section != null && !section.isBlank() ){
            
            if ( section.matches("") ){

                result = true;
            }

        }

        return result;

    }

    

}
