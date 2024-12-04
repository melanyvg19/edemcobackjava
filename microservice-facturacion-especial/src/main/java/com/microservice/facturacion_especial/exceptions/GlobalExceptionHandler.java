package com.microservice.facturacion_especial.exceptions;

import com.microservice.facturacion_especial.exceptions.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FacturacionEspecialException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ErrorMessage> handleFacturacionEspecialException(FacturacionEspecialException ex) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), ex.getMessage());

        return ResponseEntity.status(HttpStatus.OK).body(errorMessage);
    }

}
