package com.ademozalp.library_service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {


    @ExceptionHandler(LibraryNotFoundException.class)
    public ResponseEntity<Object> handleLibrarNotFoundException(LibraryNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException ex){
        return new ResponseEntity<>(ex.getErrorMessage(), HttpStatus.resolve(ex.getErrorMessage().status()));
    }
}
