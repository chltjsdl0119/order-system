package com.example.ordersystem.global.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        String message,
        List<String> errors,
        LocalDateTime timestamp
) {
    public static ErrorResponse of(String message) {
        return new ErrorResponse(message, null, LocalDateTime.now());
    }

    public static ErrorResponse of(String message, List<String> errors) {
        return new ErrorResponse(message, errors, LocalDateTime.now());
    }
}
