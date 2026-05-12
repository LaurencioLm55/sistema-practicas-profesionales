package com.sistemapracticasprofesional.logic.validators;

import com.sistemapracticasprofesional.logic.dto.UserDto;

public class UserValidator {
    private static final String USER_NAME_PATTERN = "[a-zA-Z0-9]+";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{10,}$";

    private UserDto userDto; 

    public UserValidator (UserDto userDto) {
        this.userDto = userDto;
    }

    public boolean isUserValid(){
        
        if (isUserNameValid(this.userDto) && isUserPasswordValid(this.userDto)) {
            
            return true;

        }

        return false;
    }

    public boolean isUserNameValid( UserDto userDto ){
        
        if (userDto.getUserName() != null && !userDto.getUserName().isBlank()){

                if(userDto.getUserName().matches(USER_NAME_PATTERN)){
                   
                    return true;

                    
                }
        }

        return false;

    }

    public boolean isUserPasswordValid( UserDto userDto ) {

        if (userDto.getPassword() != null && !userDto.getPassword().isBlank()){

            if(userDto.getPassword().matches(PASSWORD_PATTERN)){

                return true;

            }
                

        }

        return false;
    }

}
