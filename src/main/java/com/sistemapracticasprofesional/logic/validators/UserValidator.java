package com.sistemapracticasprofesional.logic.validators;

import com.sistemapracticasprofesional.logic.dto.UserDto;

public class UserValidator {
    private UserDto userDto; 

    public UserValidator (UserDto userDto) {
        this.userDto = userDto;
    }

    public boolean isUserValid(){
        
        if ( isUserNameValid(this.userDto) == true && isUserPasswordValid(this.userDto) == true ) {
            
            return true;

        }

        return false;
    }

    public boolean isUserNameValid( UserDto userDto ){
        
        if (userDto.getUserName() != null && !userDto.getUserName().isBlank()){

                if(userDto.getUserName().matches("[a-zA-Z0-9]+")){
                   
                    return true;

                    
                }
        }

        return false;

    }

    public boolean isUserPasswordValid( UserDto userDto ) {

        if (userDto.getPassword() != null && !userDto.getPassword().isBlank()){

            if(userDto.getPassword().matches("[a-zA-Z0-9]+")){

                return true;

            }
                

        }

        return false;
    }

}
