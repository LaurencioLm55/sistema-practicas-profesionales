package com.sistemapracticasprofesional.presentation.util;

import com.sistemapracticasprofesional.logic.dto.UserDto;

public class UserSession {

    private static UserSession instance;

    private int idUser;
    private String name;
    private String role;

    private UserSession(){

    }

    public static UserSession getInstance (){

        if ( instance == null ){
            instance = new UserSession();
        }

        return instance;

    }

    public void initializeSession ( UserDto userDto, String role ){

        this.idUser = userDto.getIdUser();
        this.name = userDto.getUserName();
        this.role = role;

    }

    public void closeSession () {

        this.idUser = 0;
        this.name = null;
        this.role = null;

    }

    public boolean isActive () {

        return this.idUser > 0 && this.name != null && this.role != null;

    }

    public boolean hasRole ( String role ) {

        return this.role != null && this.role.equals(role);

    }

    public int getIdUser () {
        return this.idUser;
    }

    public String getUserName () {
        return this.name;
    }

    public String getRole () {
        return this.role;
    }



}
