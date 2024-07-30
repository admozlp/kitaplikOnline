package com.ademozalp.library_service.exception;

public class BookNotFoundException extends RuntimeException {
    private ErrorMessage errorMessage;


    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public BookNotFoundException(ErrorMessage errorMessage, String message) {
        super(message);
        this.errorMessage = errorMessage;
    }

    public ErrorMessage getErrorMessage() {
        return this.errorMessage;
    }
}
