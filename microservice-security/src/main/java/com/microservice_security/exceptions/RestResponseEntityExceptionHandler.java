package com.microservice_security.exceptions;

import com.microservice_security.exceptions.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ExpiredRefreshTokenException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ErrorMessage> expiredRefreshTokenExceptionHandler(ExpiredRefreshTokenException exception){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.value(),exception.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(errorMessage);
    }

    @ExceptionHandler(BadUserCredentialsException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ErrorMessage> userNotRegisterException(BadUserCredentialsException exception){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value(),exception.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(errorMessage);
    }

}
