package com.sistemapracticasprofesional.logic.exception;

public class PresentationException extends RuntimeException{

    public PresentationException (String message){

        super(message); 

    }

    public PresentationException (String message, Throwable cause){

        super(message, cause);
        
    }
    
}
