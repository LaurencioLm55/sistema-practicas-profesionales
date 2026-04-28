package com.sistemapracticasprofesional.logic.dto;

public class UserDto {
    private int idUser;
    private String userName;
    private String password;
    
    public UserDto(){}
    
    public UserDto(int idUser, String userName, String password){
        this.idUser = idUser;
        this.userName = userName;
        this.password = password;
    }

    public int getIdUser(){
        return this.idUser;
    }

    public void setIdUser(int newIdUser){
        this.idUser = newIdUser;
    }
    
    public String getUserName(){
        return this.userName;
    }
    
    public void setUserName(String newUserName){
        this.userName = newUserName;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public void setPassword(String newPassword){
        this.password = newPassword;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idUser;
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserDto other = (UserDto) obj;
        if (idUser != other.idUser)
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        return true;
    }

    
    
}
