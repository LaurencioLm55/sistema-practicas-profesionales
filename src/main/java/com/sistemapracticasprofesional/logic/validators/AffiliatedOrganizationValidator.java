package com.sistemapracticasprofesional.logic.validators;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import com.sistemapracticasprofesional.logic.dto.AffiliatedOrganizationDto;
import com.sistemapracticasprofesional.presentation.controllersGui.UserLoginController;

public class AffiliatedOrganizationValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);

    private AffiliatedOrganizationDto affiliatedOrganizationDto;

    public AffiliatedOrganizationValidator(){

    }

    public AffiliatedOrganizationValidator(
        AffiliatedOrganizationDto affiliatedOrganizationDto){

        this.affiliatedOrganizationDto = affiliatedOrganizationDto;

    }

    public boolean isAffiliatedOrganizationValid () {

        return false;
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
