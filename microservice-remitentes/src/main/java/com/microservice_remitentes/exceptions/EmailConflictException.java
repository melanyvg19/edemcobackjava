package com.microservice_remitentes.exceptions;

public class EmailConflictException extends Exception{

    public EmailConflictException(String message){
        super(message);
    }

}
