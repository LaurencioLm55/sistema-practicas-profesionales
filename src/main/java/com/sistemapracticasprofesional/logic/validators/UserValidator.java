package com.sistemapracticasprofesional.logic.validators;

import com.sistemapracticasprofesional.logic.dto.UserDto;

public class UserValidator {
    private UserDto userDto; 
    private String textToValidate;

    public UserValidator (UserDto userDto) {
        this.userDto = userDto;

    }

    public boolean isUserValid(){
        if ( isUserNameValid() && isUserPasswordValid() ) {

            return true;

        }

        return false;
    }

    public boolean isUserNameValid(){

        if (userDto.getUserName() != null || userDto.getUserName().isBlank()){

            if(!userDto.getUserName().contains(" ")){
                
                if(userDto.getUserName().matches("[a-zA-Z0-9]")){

                    return true;

                }

            }

        }

        return false;

    }

    public boolean isUserPasswordValid() {
        if (userDto.getPassword() != null || userDto.getPassword().isBlank()){
            /* 
            if(userDto.getPassword().matches(text)){

                return true;

            }
                */

        }

        return false;
    }

}