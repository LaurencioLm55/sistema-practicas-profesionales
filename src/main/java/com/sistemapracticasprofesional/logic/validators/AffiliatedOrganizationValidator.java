package com.sistemapracticasprofesional.logic.validators;

import com.sistemapracticasprofesional.logic.dto.AffiliatedOrganizationDto;

public class AffiliatedOrganizationValidator {

    private AffiliatedOrganizationDto affiliatedOrganizationDto;

    public AffiliatedOrganizationValidator(
        AffiliatedOrganizationDto affiliatedOrganizationDto){

        this.affiliatedOrganizationDto = affiliatedOrganizationDto;

    }

    public boolean isAffiliatedOrganizationValid () {

        if(isAddressAffiliatedOrganizationValid(affiliatedOrganizationDto.getAddress())){

            if (isEmailAffiliatedOrganizationValid(affiliatedOrganizationDto.getEmail())){

                if (isPhoneNumberAffiliatedOrganizationValid(affiliatedOrganizationDto.getPhoneNumeber())) {

                    if (isTextAffiliatedOrganizationValid(affiliatedOrganizationDto.getName()) && 
                        isTextAffiliatedOrganizationValid(affiliatedOrganizationDto.getCity()) &&
                        isTextAffiliatedOrganizationValid(affiliatedOrganizationDto.getState())){

                            return true;
                            
                        }
                }
            }
        }

        return false;
    }

    public boolean isTextAffiliatedOrganizationValid(String text){

        if ( text != null && !text.isBlank()){

            if ( text.matches("[a-zA-Z0-9]+")){
                
                return true;

            }

        }

        return false;
        
    }

    public boolean isAddressAffiliatedOrganizationValid(String address){
        
        if ( address != null && !address.isBlank()){

            if ( address.matches("[a-zA-Z0-9#]+")){
                
                return true;

            }

        }

        return false;

    }

    public boolean isPhoneNumberAffiliatedOrganizationValid( String phoneNumber ){

        if ( phoneNumber != null && !phoneNumber.isBlank()){

            if ( phoneNumber.matches("\\d{7,15}")) {
                
                return true;

            }

        }

        return false;

    }

    public boolean isEmailAffiliatedOrganizationValid( String email ){

        if ( email != null && !email.isBlank()){

            if ( email.matches("^[a-zA-Z0-9._%+\\\\-]+@[a-zA-Z0-9.\\\\-]+\\\\.[a-zA-Z]{2,}$")){

                return true;

            }

        }

        return false;

    }



}
