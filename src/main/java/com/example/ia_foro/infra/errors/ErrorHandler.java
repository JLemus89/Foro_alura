package com.example.ia_foro.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

public class ErrorHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity HandlerError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity HandlerError400(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors().stream().map(ErrorValidationDTO::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity duplicity(SQLIntegrityConstraintViolationException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity notFound(NoSuchElementException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValidationIntegrity.class)
    public ResponseEntity errorHandlerValidationIntegrity(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorHandlerValidationBusiness(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }


    private record ErrorValidationDTO(String field, String error){
        public ErrorValidationDTO(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
