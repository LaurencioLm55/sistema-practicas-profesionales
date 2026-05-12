package com.sistemapracticasprofesional.logic.validators;

import com.sistemapracticasprofesional.logic.dto.AffiliatedOrganizationDto;

public class AffiliatedOrganizationValidator {

    private AffiliatedOrganizationDto affiliatedOrganizationDto;

    public AffiliatedOrganizationValidator(){
    }
    
    public AffiliatedOrganizationValidator(
        AffiliatedOrganizationDto affiliatedOrganizationDto){

        this.affiliatedOrganizationDto = affiliatedOrganizationDto;

    }

    public boolean isAffiliatedOrganizationValid () {
        
        boolean result = false;

        if (isAddressAffiliatedOrganizationValid(affiliatedOrganizationDto.getAddress())) {
            if (isPhoneNumberAffiliatedOrganizationValid(affiliatedOrganizationDto.getPhoneNumeber())) {
                if (isEmailAffiliatedOrganizationValid(affiliatedOrganizationDto.getEmail())) {
                    if (isGenaralTextValid()) {
                        result = true;
                    }
                }
            }
        }
        
        return result;
        
    }

    public boolean isGenaralTextValid(){
        
        boolean result = false;

        if (isTextAffiliatedOrganizationValid(affiliatedOrganizationDto.getCity())) {
            if (isTextAffiliatedOrganizationValid(affiliatedOrganizationDto.getState())){
                if (isTextAffiliatedOrganizationValid(affiliatedOrganizationDto.getSector())) {
                    if (isTextAffiliatedOrganizationValid(affiliatedOrganizationDto.getName())){
                        result = true;
                    }
                }
            }
        }

        return result;

    }

    public boolean isTextAffiliatedOrganizationValid(String text){

        boolean result = false;

        if ( text != null && !text.isBlank()){

            if ( text.matches("[-a-zA-ZáéíóúÁÉÍÓÚñÑ0-9#.,\\ ]+") ){
                
                result = true;

            }

        }

        return result;
        
    }

    public boolean isAddressAffiliatedOrganizationValid( String address ){

        boolean result = false;
        
        if ( address != null && !address.isBlank() ){
             

            if ( address.matches("[-a-zA-ZáéíóúÁÉÍÓÚñÑ0-9#.,\\ ]+")){
                
                result = true;

            }

        }

        return result;

    }

    public boolean isPhoneNumberAffiliatedOrganizationValid( String phoneNumber ){

        boolean result = false;

        if ( phoneNumber != null && !phoneNumber.isBlank()){

            if ( phoneNumber.matches("\\d{7,15}")) {
                
                result = true;

            }

        }

        return result;

    }

    public boolean isEmailAffiliatedOrganizationValid( String email ){

        boolean result = false;

        if ( email != null && !email.isBlank()){

            if ( email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
            || email.matches("^NA$")){

                result = true;

            }

        }

        return result;

    }



}
