package com.pruebaaccenture.sping.api.Handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ErrorHandles {
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> handleNoFoundException(NoHandlerFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).header("Content-Type", "application/json").body("Ruta no encontrada");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Content-Type", "application/json").body("Body no aceptado");
    }
}
