package com.sistemapracticasprofesional.presentation.util;

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

    public void initializeSession ( int idUser, String name, String role ){

        this.idUser = idUser;
        this.name = name;
        this.role = role;

    }

    public void closeSession () {
        
        instance = null;

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
