package com.microservice.factura.exceptions;

public class FacturaNotFoundException extends Exception{

    public FacturaNotFoundException(String message) {
        super(message);
    }
}
