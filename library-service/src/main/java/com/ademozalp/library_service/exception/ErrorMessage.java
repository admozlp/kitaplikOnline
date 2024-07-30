package com.ademozalp.library_service.exception;

public record ErrorMessage(
        String timeStamp,
        int status,
        String error,
        String message,
        String path) {
}
