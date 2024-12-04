package com.microservice_remitentes.exceptions;

public class EmailNotFoundException extends Exception{

    public EmailNotFoundException(String message) {
        super(message);
    }

}
