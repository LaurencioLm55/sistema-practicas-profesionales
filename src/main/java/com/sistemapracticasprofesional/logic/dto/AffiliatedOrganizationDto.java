package com.sistemapracticasprofesional.logic.dto;

public class AffiliatedOrganizationDto {
    private String idOrganization;
    private String name;
    private String address;
    private String sector;
    private String city;
    private String state;
    private String phoneNumber;
    private String email;
    
    public AffiliatedOrganizationDto(){
        
    }
    
    public AffiliatedOrganizationDto(String idOrganization, String name, String address, String sector, String city, String state, String phoneNumber, String email){
       this.idOrganization = idOrganization;
       this.name = name;
       this.address = address;
       this.sector = sector;
       this.city = city;
       this.state = state;
       this.phoneNumber = phoneNumber;
       this.email = email;
    }
    
    public AffiliatedOrganizationDto(String name, String address, String sector, String city, String state, String phoneNumber, String email){
       this.name = name;
       this.address = address;
       this.sector = sector;
       this.city = city;
       this.state = state;
       this.phoneNumber = phoneNumber;
       this.email = email;
    }
    
    public String getIdOrganization(){
        return this.idOrganization;
    }
    
    public void setIdOrganization(String idOrganization){
        this.idOrganization = idOrganization;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getAddress(){
        return this.address;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    public String getSector(){
        return this.sector;
    }
    
    public void setSector(String sector){
        this.sector = sector;
    }
    
    public String getCity(){
        return this.city;
    }
    
    public void setCity(String city){
        this.city = city;
    }
    
    public String getState(){
        return this.state;
    }
    
    public void setState(String state){
        this.state = state;
    }
    
    public String getPhoneNumeber(){
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idOrganization == null) ? 0 : idOrganization.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((sector == null) ? 0 : sector.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
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
        AffiliatedOrganizationDto other = (AffiliatedOrganizationDto) obj;
        if (idOrganization != other.idOrganization)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (sector == null) {
            if (other.sector != null)
                return false;
        } else if (!sector.equals(other.sector))
            return false;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (phoneNumber == null) {
            if (other.phoneNumber != null)
                return false;
        } else if (!phoneNumber.equals(other.phoneNumber))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }

    
}
