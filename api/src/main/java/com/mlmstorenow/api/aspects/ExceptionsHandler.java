package com.mlmstorenow.api.aspects;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mlmstorenow.api.customexception.ErrorMessage;

@ControllerAdvice
public class ExceptionsHandler {

 @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> conflict(DataIntegrityViolationException e) {

        String message = NestedExceptionUtils.getMostSpecificCause(e).getMessage();
        ErrorMessage errorMessage = new ErrorMessage(message); 
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
